package com.mfq.service.sms.provider;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.utils.DateUtil;

public class MandaoMessageProvider extends MessageProvider {

    private static final Logger log = LoggerFactory
            .getLogger(MandaoMessageProvider.class);

    /*
     * webservice服务器定义
     */
    private String serviceURL = "http://sdk2.entinfo.cn/webservice.asmx";
    private String parallelServiceURL = "";
    private String sn = "";// 序列号
    private String password = ""; // 原始密码

    private String batchext = ""; // 专用ext
    private String batchPrefix = ""; // 必须加签名

    private String suffix = "【美分期】"; // 后缀

    public static String VCODE_EXT = "11";

    private String encodepwd = "";// 混淆后的密码

    private static String serviceEncode = "gbk";

    public MandaoMessageProvider() {
    }

    public void loadConfiguration(Map<String, Object> setting) {
        try {
            this.serviceURL = (String) setting.get("serviceURL");
            this.sn = (String) setting.get("sn");
            this.password = (String) setting.get("password");
            this.parallelServiceURL = (String) setting.get("parallelServiceURL");
            this.batchext = (String) setting.get("batchext");
            this.batchPrefix = (String) setting.get("batchPrefix");

            String asuffix = (String) setting.get("suffix");
            if (StringUtils.isNotBlank(asuffix)) {
                this.suffix = asuffix;
            }
            this.encodepwd = getMD5(sn + password); // 重置传输密码
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws UnsupportedEncodingException {
        // do nothing now
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setParallelServiceURL(String parallelServiceURL) {
        this.parallelServiceURL = parallelServiceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public void setBatchext(String batchext) {
        this.batchext = batchext;
    }

    public void setBatchPrefix(String batchPrefix) {
        this.batchPrefix = batchPrefix;
    }

    public boolean isSupportParallel() {
        return StringUtils.isNotBlank(parallelServiceURL);
    }

    @Override
    public String sendBatchMessage(String content, String mobiles) {
        return batchMt(mobiles, content);
    }

    @Override
    public String sendVcodeMessage(String content, String mobile) {
        return sendSingleMessage(mobile, content, VCODE_EXT, null, "");
    }

    @Override
    public String sendSingleMessage(String content, String mobile) {
        return sendSingleMessage(mobile, content, "", null, "");
    }

    @Override
    public String sendSingleMessageBytime(String content, String mobile,
            Date sendtime) {
        return sendSingleMessage(mobile, content, "", sendtime, "");
    }

    /*
     * 方法名称：getMD5 功 能：字符串MD5加密 参 数：待转换字符串 返 回 值：加密之后字符串
     */
    public String getMD5(String sourceStr) throws UnsupportedEncodingException {
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes(serviceEncode);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            // resultStr = new String(md5.digest());
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'A', 'B', 'C', 'D', 'E', 'F' };
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
            return resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getServiceResult(String soapAction, String xml,
            String patternRule) {
        return getServiceResult(soapAction, xml, patternRule, false);

    }

    private String getServiceResult(String soapAction, String xml,
            String patternRule, boolean parallel) {

        BufferedReader in = null;
        try {
            URL url;
            url = new URL(parallel ? parallelServiceURL : serviceURL);
            String result = "";

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes(serviceEncode));

            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length",
                    String.valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(
                    httpconn.getInputStream());
            in = new BufferedReader(isr);
            String inputLine;

            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile(patternRule);
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }

            return new String(result.getBytes(serviceEncode), "utf-8");
        } catch (Exception e) {
            log.error("Mandao 短信发送异常", e);
            // e.printStackTrace();
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
    }

    /*
     * 方法名称：register 功 能：注册 参 数：对应参数 省份，城市，行业，企业名称，联系人，电话，手机，电子邮箱，传真，地址，邮编 返 回
     * 值：注册结果（String）
     */
    public String register(String province, String city, String trade,
            String entname, String linkman, String phone, String mobile,
            String email, String fax, String address, String postcode) {
        String soapAction = "http://tempuri.org/Register";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<Register xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + password + "</pwd>";
        xml += "<province>" + province + "</province>";
        xml += "<city>" + city + "</city>";
        xml += "<trade>" + trade + "</trade>";
        xml += "<entname>" + entname + "</entname>";
        xml += "<linkman>" + linkman + "</linkman>";
        xml += "<phone>" + phone + "</phone>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<email>" + email + "</email>";
        xml += "<fax>" + fax + "</fax>";
        xml += "<address>" + address + "</address>";
        xml += "<postcode>" + postcode + "</postcode>";
        xml += "</Register>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";

        return getServiceResult(soapAction, xml,
                "<RegisterResult>(.*)</RegisterResult>");
    }

    /*
     * 方法名称：chargeFee 功 能：充值 参 数：充值卡号，充值密码 返 回 值：操作结果（String）
     */
    public String chargeFee(String cardno, String cardpwd) {
        String soapAction = "http://tempuri.org/ChargUp";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<ChargUp xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + password + "</pwd>";
        xml += "<cardno>" + cardno + "</cardno>";
        xml += "<cardpwd>" + cardpwd + "</cardpwd>";
        xml += "</ChargUp>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";

        return getServiceResult(soapAction, xml,
                "<ChargUpResult>(.*)</ChargUpResult>");
    }

    /*
     * 方法名称：getBalance 功 能：获取余额 参 数：无 返 回 值：余额（String）
     */
    public String getBalance() {
        String soapAction = "http://tempuri.org/balance";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<balance xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + encodepwd + "</pwd>";
        xml += "</balance>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        // System.out.println(xml); //留一个是为了证明ack有效，剩下的自求多福吧

        return getServiceResult(soapAction, xml,
                "<balanceResult>(.*)</balanceResult>");
    }

    public String batchMt(String mobile, String content) {
        if (StringUtils.isBlank(batchext) || StringUtils.isBlank(batchPrefix)) {
            return "-9999 bad batchext config";
        } else {
            return mt(mobile, content + batchPrefix, batchext, "", "");
        }
    }

    public String sendSingleMessage(String mobile, String content, String ext,
            Date datetime, String rrid) {
        String stime = "";
        if (datetime != null) {
            stime = DateUtil.formatLong(datetime);
        }
        if (isSupportParallel()) {
            return mdsmssend(mobile, content + suffix, ext, stime, rrid);
        } else {
            return mt(mobile, content + suffix, ext, stime, rrid);
        }
    }

    /*
     * 方法名称：mt 功 能：发送短信 参 数：mobile,content,ext,stime,rrid(手机号，内容，扩展码，定时时间，唯一标识)
     * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
     */
    public String mt(String mobile, String content, String ext, String stime,
            String rrid) {
        String soapAction = "http://tempuri.org/mt";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mt xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + encodepwd + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + StringEscapeUtils.escapeXml(content)
                + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + stime + "</stime>";
        xml += "<rrid>" + rrid + "</rrid>";
        xml += "</mt>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        return getServiceResult(soapAction, xml, "<mtResult>(.*)</mtResult>");
    }

    /*
     * 方法名称：mdsmssend 功 能：发送短信 参
     * 数：mobile,content,ext,stime,rrid(手机号，内容，扩展码，定时时间，唯一标识) 返 回
     * 值：唯一标识，如果不填写rrid将返回系统生成的
     */
    public String mdsmssend(String mobile, String content, String ext,
            String stime, String rrid) {
        String soapAction = "http://entinfo.cn/mdsmssend";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mdsmssend xmlns=\"http://entinfo.cn/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + encodepwd + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + StringEscapeUtils.escapeXml(content)
                + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + stime + "</stime>";
        xml += "<rrid>" + rrid + "</rrid>";
        xml += "<msgfmt>" + "" + "</msgfmt>";
        xml += "</mdsmssend>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        return getServiceResult(soapAction, xml,
                "<mdsmssendResult>(.*)</mdsmssendResult>", true); // 并发才有
    }

    /*
     * 方法名称：mo 功 能：接收短信 参 数：无 返 回 值：接收到的信息
     */
    public String mo() {
        String soapAction = "http://tempuri.org/mo";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mo xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + encodepwd + "</pwd>";
        xml += "</mo>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        return getServiceResult(soapAction, xml, "<moResult>(.*)</moResult>");
    }

    /*
     * 方法名称：gxmt 功 能：发送短信 参
     * 数：mobile,content,ext,stime,rrid(手机号，内容，扩展码，定时时间，唯一标识) 返 回
     * 值：唯一标识，如果不填写rrid将返回系统生成的
     */
    public String gxmt(String mobile, String content, String ext, String stime,
            String rrid) {
        String soapAction = "http://tempuri.org/gxmt";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<gxmt xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + encodepwd + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + StringEscapeUtils.escapeXml(content)
                + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + stime + "</stime>";
        xml += "<rrid>" + rrid + "</rrid>";
        xml += "</gxmt>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        return getServiceResult(soapAction, xml,
                "<gxmtResult>(.*)</gxmtResult>");
    }

    public String unRegister() {
        String soapAction = "http://tempuri.org/UnRegister";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<UnRegister xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + password + "</pwd>";
        xml += "<cardno>" + sn + "</cardno>";
        xml += "<cardpwd>" + encodepwd + "</cardpwd>";
        xml += "</UnRegister>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";

        return getServiceResult(soapAction, xml,
                "<UnRegisterResult>String</UnRegisterResult>");
    }

}
