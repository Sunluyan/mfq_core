package com.mfq.service.sms.provider;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.mfq.service.sms.tongyi.SendItem;

/**
 * 
 * @author xingyongshan
 *
 */
public class TongyiMessageProvider extends MessageProvider {

    /*
      * webservice服务器定义
      */
    private String serviceURL = "http://www.meilime.com/SMSG/services/SMS";
    private String sn = "POWERU-SMS";// 序列号

    private String suffix = "【美分期】"; //后缀

    private static String serviceEncode = "UTF-8";


    public TongyiMessageProvider() {
    }


    public void loadConfiguration(Map<String, Object> setting){
            this.serviceURL = (String) setting.get("serviceURL");
            this.sn = (String) setting.get("sn");

            String asuffix = (String) setting.get("suffix");
            if(StringUtils.isNotBlank(asuffix)){
                this.suffix = asuffix;
            }
    }

    public void init() {
        //do nothing now
    }


    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    @Override
    public String getBalance() {
        return "0"; //后付费的
    }

    @Override
    public String sendBatchMessage(String content, String mobiles) {
        throw new UnsupportedOperationException("not avaiable");
    }

    @Override
    public String sendVcodeMessage(String content, String mobile, boolean isReset) {
        return _sendSingleMessage(mobile, content, null);
    }

    @Override
    public String sendSingleMessage(String content, String mobile) {
        return _sendSingleMessage(mobile, content, null);
    }

    @Override
    public String sendSingleMessageBytime(String content, String mobile, Date sendtime) {
        return _sendSingleMessage(mobile, content, sendtime);
    }


    private String getServiceResult(String soapAction, String xml, String patternRule) {

        BufferedReader in = null;
        try {
            URL url;
            url = new URL(serviceURL);
            String result = "";

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes(serviceEncode));

            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpconn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn.getInputStream());
            in = new BufferedReader(isr);
            String inputLine;

            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile(patternRule);
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }

            if(!"success".equalsIgnoreCase(result)){
                return "-" + result;
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (Exception e) {
                    //do nothing
                }
            }
        }
    }


    public String _sendSingleMessage(String mobile, String content, Date stime){
        if(stime == null){
            return addSMSList(mobile, content + suffix );
        }
        else {
            throw new UnsupportedOperationException("not avaiable");
        }
    }

    public String addSMSList(String mobile, String content) {

        String sendCombined = setupSendCombinedXML(mobile, content);

        String soapAction = "http://webservice.sms.api.poweru.cn/AddSMSList";

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<AddSMSList xmlns=\"http://webservice.sms.api.poweru.cn/\">";
        xml += "<in0>" + sn + "</in0>";
        xml += "<in1>" + sendCombined + "</in1>";
        xml += "</AddSMSList>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        return getServiceResult(soapAction, xml, "<ns1:out>(.*)</ns1:out>");
    }

    private String setupSendCombinedXML(String mobile, String content){
        List<SendItem> list = new ArrayList<SendItem>();

        SendItem s = new SendItem();
        s.setBid( "sms001" );
        s.setRecvNum( mobile );
        s.setSmsContent( content );

        //以时间为编号
        String time = Long.toString(System.currentTimeMillis());
        if(time.length() > 14){
            time = time.substring(time.length() - 14);
        }

        s.setSmsID( "UE01" + time + "0" );
        s.setSendLevel( "1" );
        list.add(s);

        String xml = getSendXML(list);

        xml = xml.replace("&", "&amp;");
        xml = xml.replace("<", "&lt;");
        xml = xml.replace(">", "&gt;");
        xml = xml.replace("'", "&apos;");
        xml = xml.replace("\"", "&quot;");

        return xml;
    }


    private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";


    private String getSendXML(List<SendItem> sendList) {
        StringBuilder strXML = new StringBuilder();
        strXML.append(XML_HEAD);
        strXML.append("<SMS type=\"send\">");
        Iterator<SendItem> iter = sendList.iterator();
        while (iter.hasNext()) {
            SendItem bean = iter.next();
            strXML.append("<Message SmsID=\"" );
            strXML.append(bean.getSmsID());
            strXML.append("\" Bid=\"" );
            strXML.append(bean.getBid());
            strXML.append("\" RecvNum=\"" );
            strXML.append(bean.getRecvNum() );
            strXML.append("\" Content=\"" );
            String smsContent = bean.getSmsContent();
            smsContent = smsContent.replace("&", "&amp;");
            smsContent = smsContent.replace("<", "&lt;");
            smsContent = smsContent.replace(">", "&gt;");
            smsContent = smsContent.replace("'", "&apos;");
            smsContent = smsContent.replace("\"", "&quot;");
            strXML.append(smsContent);
            strXML.append("\" SendLevel=\"" );
            strXML.append(bean.getSendLevel() );
            strXML.append("\"/>");

        }
        strXML.append("</SMS>");
        return strXML.toString();
    }
}

