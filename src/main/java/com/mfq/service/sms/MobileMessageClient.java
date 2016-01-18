package com.mfq.service.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import com.mfq.bean.user.User;
import com.mfq.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.mfq.bean.sms.SMSConfig;
import com.mfq.bean.sms.SMSLog;
import com.mfq.bean.sms.SMSSendStatus;
import com.mfq.bean.sms.SMSSendType;
import com.mfq.constants.SmsConstants;
import com.mfq.dao.SMSConfigMapper;
import com.mfq.dao.SMSMapper;
import com.mfq.service.sms.provider.MaiXunMessageProvider;
import com.mfq.service.sms.provider.MandaoMessageProvider;
import com.mfq.service.sms.provider.MessageProvider;
import com.mfq.service.sms.provider.MontnetsProvider;
import com.mfq.service.sms.provider.NullMessageProvider;
import com.mfq.service.sms.provider.TianHanMessageProvider;
import com.mfq.service.sms.provider.UcpaasMessageProvider;
import com.mfq.utils.Config;
import com.mfq.utils.DateUtil;

/**
 * 短信服务客户端.
 *
 * Notice: 一般使用SMSService对外提供服务, Client仅对内部业务服务.
 */
@Service
public class MobileMessageClient {

    private static Logger log = LoggerFactory
            .getLogger(MobileMessageClient.class);
    private static Logger smsAlertLog = LoggerFactory.getLogger("SMSALERT");
    private static Logger smsWarningLog = LoggerFactory.getLogger("SMSWARNING");
    
    @Resource
    SMSConfigMapper smsConfigMapper;
    @Resource
    SMSMapper smsMapper;
    @Resource
    UserService userService;

    enum MessageType {
        Vcode, Normal, Batch, Backup
    }

    // 目前不同短信类型对应的提供商
    private Map<MessageType, MessageProvider> _providerMapByType = new HashMap<MessageType, MessageProvider>();

    private String alarmMobiles = Config.getItem("sms_alarm");

    private final static int MAX_CACHE_NUM = 100;
    private final static int MAX_MOBILE_CACHE_NUM = 100;
    int HOUR_INTERVAL = 60 * 60 * 1000; // 间隔60分钟
    static int MAX_ALARM_NUM = 5;
    HashFunction hf = Hashing.md5();

    static String FiveMinuteLimit = "-发送受限，5分钟内有重复发送";

    // 短信验证码，最近15分钟内缓存100个手机号码，用于检测重复发送
    final static LoadingCache<String, AtomicInteger> cache = CacheBuilder
            .newBuilder().expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(MAX_CACHE_NUM)//
            .initialCapacity(MAX_CACHE_NUM)
            .build(new CacheLoader<String, AtomicInteger>() {
                public AtomicInteger load(String key) throws Exception {
                    return new AtomicInteger(0);
                }
            });

    // 短信验重，最近5分钟内缓存100个手机号码
    final static LoadingCache<String, String> msgCache = CacheBuilder
            .newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(MAX_CACHE_NUM)//
            .initialCapacity(MAX_MOBILE_CACHE_NUM)
            .build(new CacheLoader<String, String>() {
                public String load(String key) throws Exception {
                    return new String("");
                }
            });

    // Alarm报警
    final static LoadingCache<String, AtomicInteger> alarmCache = CacheBuilder
            .newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(MAX_ALARM_NUM)//
            .initialCapacity(MAX_ALARM_NUM)
            .build(new CacheLoader<String, AtomicInteger>() {
                public AtomicInteger load(String key) {
                    return new AtomicInteger(0);
                }
            });

    public MobileMessageClient() {
    }

    public String sendVcodeMessage(String content, String mobile) {
        MessageProvider p = getProvider(MessageType.Vcode);
        // 发送检测 报警规则: 一个小时之内如果有两个人2次, 或者一个人三次
        recordAndCheckSMSService(mobile);
        String result = "0";
        if (Config.isProduct()) {
            result = p.sendVcodeMessage(content, mobile);
        } else {
            log.warn("Environment {}, mobile={}, vcodeMsg={}",
                    Config.getItem("system.type"), mobile, content);
        }
        createSmsLog(p, content, mobile, null, result, SMSSendType.VCODE, 0);
        return result;
    }

    /**
     * 批量短信发送，禁止5分钟内创建同样内容的短信给同一批次收信人
     */
    public String sendBatchMessage(String content, String mobiles) {
    	log.info("===> {},{}",content, mobiles);
        MessageProvider p = getProvider(MessageType.Batch);
        log.info("MessageProvider  p_name is {}",p.getName());
        String balance;
        balance = p.getBalance();
        smsWarningLog.info("smscost left:" + balance);

        String result = FiveMinuteLimit;
        if (recordAndCheckSMSRepeat(mobiles, content)) {
            result = "0";
            if (Config.isProduct()) {
                result = p.sendBatchMessage(content, mobiles);
            } else {
                log.warn(
                        "Environment {}, Actually don't sendBatch, mobiles={}, content={}",
                        Config.getItem("system.type"), mobiles, content);
            }
        }
        log.info("send mobile batch byname " + p.getName() + ": " + mobiles
                + " message: " + content + "" + " result:" + result + " time: "
                + new Date());

        if (result == null || result.startsWith("-")) {
            errorWarning(
                    "find error when send mobile message, the result is: "
                            + result + " the mobile: " + mobiles
                            + " the content: " + content,
                    true, SmsConstants.ERROR_GENERAL);
        } else {
            result = p.getBalance();
            smsWarningLog.info("smscost left:" + result);
        }
        createSmsLog(p, content, mobiles, null, result, SMSSendType.BATCH, 0);
        return result;
    }

    /**
     * 普通短信发送，禁止5分钟内创建同样内容的短信给同一收信人
     */
    public String sendSingleMessage(String content, String mobile) {
        MessageProvider p = getProvider(MessageType.Normal);
        String result = FiveMinuteLimit;
        if (recordAndCheckSMSRepeat(mobile, content)) { // false时就是重复了
            result = "0";
            if (Config.isProduct()) {
                result = p.sendSingleMessage(content, mobile);
            } else {
                log.warn("Environment {}, mobile={}, content={}",
                        Config.getItem("system.type"), mobile, content);
            }
            createSmsLog(p, content, mobile, null, result, SMSSendType.NORMAL,
                    0);
        } else { // false时就是重复了
            createSmsLog(p, content, mobile, null, result, SMSSendType.NORMAL,
                    0, true);
        }
        return result;
    }

    public static void main(String[] args) {//// TODO: 16/1/16 发送消息
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        MobileMessageClient mobileMessageClient = ac.getBean(MobileMessageClient.class);
        mobileMessageClient.loadConfiguration(true);
        MessageProvider p = mobileMessageClient.getProvider(MessageType.Vcode);
        String result = p.sendSingleMessage("【美分期】500元优惠券已放入您的美分期APP账户中，使用时间有限，快快登录使用 （link）。<美分期专注于微整形的团购与分期，0息分期，先整形后付款>。", "18338751231");
        System.out.println(result);
    }

    /**
     * 发送单条短信，失败重发或定时发送专用
     */
    public String sendMessageBySystem(String content, String mobile, int sid,
            SMSSendType sendtype) {
        // 失败重发短信不受限制 if(!recordAndCheckSMSRepeat(mobiles,content)){
        MessageProvider p = getProvider(MessageType.Normal); // 严格来讲, 这是不对的,
                                                             // 要考虑原短信是vcode还是normal
        String result = "0";
        if (Config.isProduct()) {
            result = p.sendSingleMessage(content, mobile);
        }
        if (SMSSendType.RESEND == sendtype) {
            createSmsLog(p, content, mobile, null, result, SMSSendType.RESEND,
                    sid);
        } else {
            createSmsLog(p, content, mobile, null, result, SMSSendType.NORMAL,
                    sid);
        }
        return result;
    }

    /**
     * 定时短信发送，禁止5分钟内创建同样内容的短信给同一收信人，定时短信通过gather定时发送
     */
    public String sendSingleMessageBytime(String content, String mobile,
            Date sendtime) {
        if (sendtime == null) {
            return sendSingleMessage(content, mobile);
        }
        MessageProvider p = getProvider(MessageType.Normal);
        if (recordAndCheckSMSRepeat(mobile, content)) {
            return createTimedSms(p, content, mobile, sendtime, null, null)
                    + "";
        } else {
            return createTimedSms(p, content, mobile, sendtime,
                    SMSSendStatus.FAILED, "-发送受限，5分钟内有重复发送") + "";
        }
    }

    private void loadConfiguration(boolean force) {
        if (!_providerMapByType.isEmpty() && !force) {
            return; // 已经加载过了
        }
        try {
            List<SMSConfig> configs = smsConfigMapper.findAllSMSConfig();
            if (CollectionUtils.isEmpty(configs)) {
                return;
            }
            Map<MessageType, MessageProvider> providerMapByType = Maps
                    .newHashMap();
            for (SMSConfig config : configs) {
                try {
                    MessageType messageType = null;
                    if (config.isVcode()) {
                        messageType = MessageType.Vcode;
                    } else if (config.isNormal()) {
                        messageType = MessageType.Normal;
                    } else if (config.isBatch()) {
                        messageType = MessageType.Batch;
                    } else if (config.isBackup()) {
                        messageType = MessageType.Backup;
                    } else {
                        log.warn("no config for this provider, ignore config!");
                        continue;
                    }
                    MessageProvider provider;
                    if (config.getName().equalsIgnoreCase("maixun")) {
                        provider = new MaiXunMessageProvider();
                    } else if (config.getName().equalsIgnoreCase("mandao")) {
                        provider = new MandaoMessageProvider();
                    } else if (config.getName().equalsIgnoreCase("tianhan")) {
                        provider = new TianHanMessageProvider();
                    } else if (config.getName().equalsIgnoreCase("montnets")) {
                        provider = new MontnetsProvider();
                    } else if (config.getName().equalsIgnoreCase("ucpaas")) {
                        provider = new UcpaasMessageProvider();
                    } else { // 不认识或者故意的
                        provider = new NullMessageProvider();
                        errorWarning(
                                "wrong mobile message server name!:"
                                        + config.getName(),
                                false, SmsConstants.ERROR_CONFIG);
                        log.error("wrong mobile message server name!");
                    }
                    provider.loadConfiguration(config.getDetailMap());
                    provider.setName(config.getName());// set name for lookup
                    // add to map
                    providerMapByType.put(messageType, provider);
                } catch (Exception e) {
                    errorWarning(
                            "wrong mobile message server config!:" + config,
                            true, SmsConstants.ERROR_CONFIG);
                    log.error("wrong mobile message server config!");
                }
            }
            if (CollectionUtils.isEmpty(providerMapByType)) {
                errorWarning("error setting mobile message server!:", true,
                        SmsConstants.ERROR_CONFIG);
            } else {
                _providerMapByType = providerMapByType;
            }
        } catch (Exception e) {
            log.error("ExceptionOccurs In LoadConfiguration", e);
        }
        log.info("load mobile message servers configuration success");
    }

    private MessageProvider getProvider(MessageType messageType) {
        loadConfiguration(false);
        MessageProvider p = _providerMapByType.get(messageType);
        return p == null ? new NullMessageProvider() : p;
    }

    //////////////////////////////////////////////////////////////////////////// 发送短信代码区域
    /**
     * 5 分钟内 100个手机号或批量发送批次 检测当前检测短信内容是否在规定时间内发送给同一人
     */
    private boolean recordAndCheckSMSRepeat(String mobiles, String content) {
        HashCode chc = hf.newHasher().putString(content).hash();
        String contentHashed = chc.toString();
        HashCode mhc = hf.newHasher().putString(mobiles).hash();
        String mobileHashed = mhc.toString();
        boolean result = true;
        if (contentHashed.equals(msgCache.getUnchecked(mobileHashed))) {
            result = false;
            String errorlog = "same sms to same mobile in 5 minutes , mobile:"
                    + mobiles + " | content:" + content;
            log.warn(errorlog);
        }
        msgCache.put(mobileHashed, contentHashed);
        return result;
    }

    private void recordAndCheckSMSService(String mobile) {
        // 报警规则: 一个小时之内如果有两个人2次, 或者一个人三次
        int ONE_MAX = 3;
        int TWO_COUNT = 2;

        boolean maybeBroken = false;
        int currentCount = cache.getUnchecked(mobile).incrementAndGet();
        if (currentCount >= ONE_MAX) {
            cache.getUnchecked(mobile).set(0);
            maybeBroken = true;
        } else if (currentCount == 2) {
            int twicerCount = 0;
            for (AtomicInteger c : cache.asMap().values()) {
                if (c.get() >= 2) {
                    twicerCount++;
                }
            }
            maybeBroken = twicerCount >= TWO_COUNT;
        }
        if (maybeBroken) {
            StringBuilder buf = new StringBuilder(
                    "find much request when check mobile vcode message");
            for (Entry<String, AtomicInteger> e : cache.asMap().entrySet()) {
                if (e.getValue().get() >= 2) {
                    buf.append(" ").append(e.getKey()).append(":")
                            .append(e.getValue());
                    e.getValue().set(0);
                }
            }
            log.warn(buf.toString());
            errorWarning(buf.toString(), false, SmsConstants.ERROR_RULE_WARN);
        }
    }

    // 发送警报信息, 用于内部自我报警
    protected String sendAlarmMessage(String content, String mobile) {
        MessageProvider p = getProvider(MessageType.Backup);
        if (p == null) {
            String error = "sms: not find backup setting! please check and fix it!";
            smsAlertLog.error(error);
            return "-1";
        }
        String result = "0";
        if (Config.isProduct()) {
            result = p.sendSingleMessage(content, mobile);
        } else {
            log.warn("Environment {}, mobile={}, AlarmMsg={}",
                    Config.getItem("system.type"), mobile, content);
        }
        // 可能死循环? 如果两个通道都坏了
        createSmsLog(p, content, mobile, null, result, SMSSendType.NORMAL, 0,
                false, true);
        return result;
    }

    /**
     * 记录发送结果，加入发送日志
     */
    private void createSmsLog(MessageProvider p, String content, String mobile,
            Date datetime, String result, SMSSendType sendType, int sid) {
        createSmsLog(p, content, mobile, datetime, result, sendType, sid,
                false);
    }

    private void createSmsLog(MessageProvider p, String content, String mobile,
            Date datetime, String result, SMSSendType sendType, int sid,
            boolean noResend) {
        createSmsLog(p, content, mobile, datetime, result, sendType, sid,
                noResend, false);
    }

    /*
     * 多增加了忽略失败重发选项
     */
    private void createSmsLog(MessageProvider p, String content, String mobile,
            Date datetime, String result, SMSSendType sendType, int sid,
            boolean noResend, boolean avoidCycleWarning) {
        // 创建短信日志，创建失败重发延迟短信
        SMSLog sms = new SMSLog();
        sms.setSid(sid);
        sms.setMobile(mobile);
        sms.setContent(content);
        sms.setProvider(p.getName());
        // 不包含定时发送类型
        sms.setSendType(sendType);
        sms.setSentAt(new Date());
        sms.setSendStatus(SMSSendStatus.SUCCESS);

        log.info("send mobile message byname " + p.getName() + " :" + mobile
                + " message: " + content + " " + datetime + " result:" + result
                + " time: " + DateUtil.formatLong(new Date()));
        // 短信发送失败：result为空或者以-开头
        if (StringUtils.isBlank(result) || result.startsWith("-")) {
            sms.setSendStatus(SMSSendStatus.FAILED);

            boolean alarm = true;
            if (FiveMinuteLimit.equalsIgnoreCase(result)) { // 忽略此报警
                alarm = false;
                noResend = true; // 强制不重发
            }
            // 可以忽略的
            if (result.startsWith(SmsConstants.ERROR_RULE_IGNORE)) {
                alarm = false;
                noResend = true; // 强制不重发
            }
            // 避免循环报警
            if (avoidCycleWarning) {
                alarm = false;
            }
            errorWarning("find error when send mobile message, the result is: "
                    + result + " the mobile: " + mobile + " the content: "
                    + content, alarm, result);

            /*
             * 失败重发规则： 1、群发失败忽略 2、重发后失败忽略,仅重试5分钟后重试一次
             */
            if (SMSSendType.BATCH != sendType
                    && SMSSendType.RESEND != sendType) {
                if (!noResend) {
                    long resid = createResendSms(p, content, mobile);
                    sms.setResendSid(resid);
                }
            }
        }

        sms.setExtra(result);
        if (sid > 0) {
            smsMapper.updateSMSLogStatus(sms);
        } else {
            if (SMSSendType.BATCH == sendType) {
                for (String singleMobile : mobile.split(",")) {
                    sms.setMobile(singleMobile);
                    smsMapper.addSMSLog(sms);
                }
            } else {
                smsMapper.addSMSLog(sms);
            }
        }
    }

    /**
     * 创建重发短信
     */
    private long createResendSms(MessageProvider p, String content,
            String mobile) {
        log.info("create resend mobile message byname " + p.getName() + " :"
                + mobile + " message: " + content + " " + "time: "
                + new Date());
        // 创建短信日志，创建失败重发延迟短信
        SMSLog sms = new SMSLog();
        sms.setMobile(mobile);
        sms.setContent(content);
        sms.setProvider(p.getName());
        sms.setTimedAt(DateUtils.addMinutes(new Date(), 5));
        sms.setSendStatus(SMSSendStatus.PENDING);
        sms.setSendType(SMSSendType.RESEND);
        sms.setSentAt(new Date());
        return smsMapper.addSMSLog(sms);
    }

    /**
     * 创建定时短信
     */
    private long createTimedSms(MessageProvider p, String content,
            String mobile, Date datetime, SMSSendStatus status, String result) {
        log.info("create timed mobile message byname " + p.getName() + " :"
                + mobile + " message: " + content + " " + "time: "
                + new Date());
        // 创建短信日志，创建失败重发延迟短信
        SMSLog sms = new SMSLog();
        sms.setMobile(mobile);
        sms.setContent(content);
        sms.setProvider(p.getName());
        sms.setTimedAt(datetime);
        sms.setSendStatus(status == null ? SMSSendStatus.PENDING : status);
        sms.setExtra(result == null ? "" : result);
        sms.setSendType(SMSSendType.TIMED);
        sms.setSentAt(new Date());
        return smsMapper.addSMSLog(sms);
    }

    private void errorWarning(String error, boolean alarm, String errcode) {
        if (Config.isProduct()) {
            if (FiveMinuteLimit.equalsIgnoreCase(errcode)) { // 忽略此报警
                smsWarningLog.error(error);
            } else {
                smsAlertLog.error(error);
            }
            if (alarm) {
                log.info("send alarm to admin: " + error);
                sendAlarm(error); // 手机报警
                // check limit for switch to backup
                int count;
                // check error code for special deal
                if (SmsConstants.ERROR_SERVER.equalsIgnoreCase(errcode)) {
                    count = alarmCache.getUnchecked("alarm")
                            .addAndGet(MAX_ALARM_NUM / 2);
                } else {
                    count = alarmCache.getUnchecked("alarm").incrementAndGet();
                }
                if (count > MAX_ALARM_NUM) {
                    switchToBackupChannel();
                }
            }
        } else {
            log.warn("Environment {}, Actually SMS_ERROR don't send message!",
                    Config.getItem("system.type"));
        }
    }

    protected void sendAlarm(String alarmMsg) {
        loadConfiguration(false);
        if (StringUtils.isNotBlank(alarmMobiles)) {
            String[] mobileArr = StringUtils.split(alarmMobiles, ",");
            List<String> mobiles = Lists.newArrayList(mobileArr);
            for (String mobile : mobiles) {
                sendAlarmMessage(mobile + ":" + alarmMsg, mobile);
            }
        } else {
            smsAlertLog.error("not find sms alarm mobile, please fix it!");
        }
    }

    // 切换通道: 目前只能切一次, 手动恢复吧
    protected void switchToBackupChannel() {
        MessageProvider mpNormal = _providerMapByType.get(MessageType.Normal);
        MessageProvider mpBackup = _providerMapByType.get(MessageType.Backup);

        if (!mpNormal.getName().equals(mpBackup.getName())) {
            _providerMapByType.put(MessageType.Normal, mpBackup);
            _providerMapByType.put(MessageType.Vcode, mpBackup);

            // reset
            alarmCache.invalidateAll();

            String error = "switch to backup sms channel: " + mpBackup.getName()
                    + ", please check and fix it!";
            smsAlertLog.error(error);
            sendAlarm(error); // 手机报警
        }
    }


}