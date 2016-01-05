package com.mfq.utils;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.mfq.bean.user.UserQuota;

public class FQUtil {

    private static final Logger logger = LoggerFactory.getLogger(FQUtil.class);

    private final static int [] nums= {6,12,24};
    
    public static Map<Integer, BigDecimal> fenqiCompute(UserQuota quota,
            BigDecimal baseFQMoney) {
    	
        Map<Integer, BigDecimal> o = Maps.newTreeMap();
        int min = 3;
        int max = periodMax(quota, baseFQMoney);
        
        for (int i = min; i <= max; i++) {
            BigDecimal v = computePeriodPay(i, baseFQMoney);
            if(isExistInArray(i,nums))
            	o.put(i, v);
        }
        return o;
    }
    
    /**
     * 根据金额获取分期
     * @param baseFQMoney
     * @return
     */
    public static Map<String, Object> fenqiMaxCompute(BigDecimal baseFQMoney) {
        Map<String, Object> o = Maps.newTreeMap();
        int max = periodMax(baseFQMoney);
		if (max < 3) {
        	return null;
        }
        BigDecimal v = computePeriodPay(max, baseFQMoney);

        o.put("p_num", max);
        o.put("p_price", v);
        return o;
    }
    
    /**
     * 根据金额获取分期
     * @param baseFQMoney
     * @return
     */
    public static Map<Integer, BigDecimal> fenqiCompute(BigDecimal baseFQMoney) {
        Map<Integer, BigDecimal> o = Maps.newTreeMap();
        int min = 3;
        int max = periodMax(baseFQMoney);

        for (int i = min; i <= max; i++) {
            BigDecimal v = computePeriodPay(i, baseFQMoney);
            if(isExistInArray(i, nums))
            	o.put(i, v);
        }
        return o;
    }
    
    private static boolean isExistInArray(int p, int [] ps){
    	for(int i:ps){
    		if(p==i)
    			return true;
    	}
    	return false;
    }

    /**
     * 后台配置录入原始值：团购价、贷款月利率（1.33%） 原始录入价格——团购价 每月应还金额：a*[i*(1+i)^n]/[(1+i)^n-1]
     * （注：a：贷款本金 ，i：贷款月利率， n：贷款月数 ）
     * 
     * @param period
     * @param base
     * @return
     */
    public static BigDecimal computePeriodPay(int period, BigDecimal base) {
        double rate = 0.0125; // 月利率1.25%
        return base.multiply(new BigDecimal(rate * Math.pow(1 + rate, period)))
                .divide(new BigDecimal(Math.pow(1 + rate, period) - 1), 2,
                        BigDecimal.ROUND_HALF_UP);
    }
    
    public static BigDecimal getFinanceAmount(UserQuota quota, BigDecimal price){
    	if(quota.getQuotaLeft().compareTo(price) > 0){
    		return price;
    	}
    	return quota.getQuotaLeft();
    }

    public static boolean fenqiIsOk(UserQuota quota, BigDecimal baseFQMoney,
            int period, BigDecimal periodPay) {
        int max = 24;
        logger.info("period {}, max {}", period, max);
        if (period > max) {
            logger.warn("最大分期数不满足要求！");
            return false;
        }
        if (quota.getQuotaLeft().compareTo(baseFQMoney) < 0) {
            logger.warn("用户剩余分期额度:{}, 当前分期数额:{}", quota.getQuotaLeft(),
                    baseFQMoney);
            return false;
        }
        logger.info("fenqi quotaLeft={}, basefqMoney={}", quota.getQuotaLeft(), baseFQMoney);
        Map<Integer, BigDecimal> map = fenqiCompute(quota, baseFQMoney);
        if (map.get(period) != null ) {
            // 为什么用1？
            // 避免四舍五入导入的问题！
            if (map.get(period).subtract(periodPay).compareTo(new BigDecimal(1)) < 0) {
                return true;
            }else{
            	logger.info("看看period:{},periodPay:{}",map.get(period));
            }
            logger.info("fenqi_map:{}, max :{}, period :{} , periodPay: {}", map, max , map.get(period), periodPay);
        }else if(baseFQMoney.compareTo(BigDecimal.valueOf(0))==0 && period == 0){
        	return true;
        }
        return false;
    }

    /**
     * 最大分期数 刨除年级限制外，按照分期金额来看： 100-499元 5期 500-998元 9期 999-1498元 12期 1499-1998元
     * 15期 1999-不限 24期
     *
     * @return
     */
    private static int periodMax(UserQuota quota, BigDecimal baseMoney) {
        int max = 0;
//        if (quota == null || quota.getStartschoolAt() == null
//                || quota.getGrade() == null) {
//            return max;
//        }
//        // 比较当前时间 与 毕业时间 差值，是否在8个月之内
//        Date latestDate4Period = DateUtil.addMonth(quota.getStartschoolAt(), -10);
//        if (!DateUtil.isBefore(new Date(), latestDate4Period)) {
//            return max;
//        }
//        max = quota.getGrade().getPeriod();
        max = 24;
        logger.info("user's  max period is {}",max);
        // 按价格的最大值
        int pmax = 0;
        if (baseMoney.compareTo(new BigDecimal(1999)) >= 0) {
            pmax = 24;
        } else if (baseMoney.compareTo(new BigDecimal(1499)) >= 0) {
            pmax = 15;
        } else if (baseMoney.compareTo(new BigDecimal(999)) >= 0) {
            pmax = 12;
        } else if (baseMoney.compareTo(new BigDecimal(500)) >= 0) {
            pmax = 9;
        } else if (baseMoney.compareTo(new BigDecimal(100)) >= 0) {
            pmax = 5;
        }
        if (max > pmax) {
            max = pmax;
        }
        return max;
    }
    
    
    /**
     * 最大分期数 ，按照分期金额来看： 100-499元 5期 500-998元 9期 999-1498元 12期 1499-1998元
     * 15期 1999-不限 24期
     * 
     * @param
     * @return
     */
    public static int periodMax(BigDecimal baseMoney) {
        
        // 按价格的最大值
        int pmax = 0;
        if (baseMoney.compareTo(new BigDecimal(1999)) >= 0) {
            pmax = 24;
        } else if (baseMoney.compareTo(new BigDecimal(1499)) >= 0) {
            pmax = 15;
        } else if (baseMoney.compareTo(new BigDecimal(999)) >= 0) {
            pmax = 12;
        } else if (baseMoney.compareTo(new BigDecimal(500)) >= 0) {
            pmax = 9;
        } else if (baseMoney.compareTo(new BigDecimal(100)) >= 0) {
            pmax = 5;
        }
        logger.info("product's max period is {}",pmax);
        return pmax;
    }

    
    public static void main(String[] args) {
        int period = 12;
        BigDecimal base = new BigDecimal(10000);
        System.out.println(computePeriodPay(period, base));
        Map<String, Object> yy= fenqiMaxCompute(base);

    }
}