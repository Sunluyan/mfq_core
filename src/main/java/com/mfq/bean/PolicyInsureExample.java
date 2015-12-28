package com.mfq.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PolicyInsureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PolicyInsureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeIsNull() {
            addCriterion("service_stime is null");
            return (Criteria) this;
        }

        public Criteria andServiceStimeIsNotNull() {
            addCriterion("service_stime is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStimeEqualTo(Date value) {
            addCriterionForJDBCDate("service_stime =", value, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("service_stime <>", value, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeGreaterThan(Date value) {
            addCriterionForJDBCDate("service_stime >", value, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("service_stime >=", value, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeLessThan(Date value) {
            addCriterionForJDBCDate("service_stime <", value, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("service_stime <=", value, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeIn(List<Date> values) {
            addCriterionForJDBCDate("service_stime in", values, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("service_stime not in", values, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("service_stime between", value1, value2, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andServiceStimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("service_stime not between", value1, value2, "serviceStime");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIsNull() {
            addCriterion("hospital_name is null");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIsNotNull() {
            addCriterion("hospital_name is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalNameEqualTo(String value) {
            addCriterion("hospital_name =", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotEqualTo(String value) {
            addCriterion("hospital_name <>", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameGreaterThan(String value) {
            addCriterion("hospital_name >", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameGreaterThanOrEqualTo(String value) {
            addCriterion("hospital_name >=", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLessThan(String value) {
            addCriterion("hospital_name <", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLessThanOrEqualTo(String value) {
            addCriterion("hospital_name <=", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLike(String value) {
            addCriterion("hospital_name like", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotLike(String value) {
            addCriterion("hospital_name not like", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIn(List<String> values) {
            addCriterion("hospital_name in", values, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotIn(List<String> values) {
            addCriterion("hospital_name not in", values, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameBetween(String value1, String value2) {
            addCriterion("hospital_name between", value1, value2, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotBetween(String value1, String value2) {
            addCriterion("hospital_name not between", value1, value2, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andOrderSumIsNull() {
            addCriterion("order_sum is null");
            return (Criteria) this;
        }

        public Criteria andOrderSumIsNotNull() {
            addCriterion("order_sum is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSumEqualTo(Long value) {
            addCriterion("order_sum =", value, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumNotEqualTo(Long value) {
            addCriterion("order_sum <>", value, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumGreaterThan(Long value) {
            addCriterion("order_sum >", value, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumGreaterThanOrEqualTo(Long value) {
            addCriterion("order_sum >=", value, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumLessThan(Long value) {
            addCriterion("order_sum <", value, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumLessThanOrEqualTo(Long value) {
            addCriterion("order_sum <=", value, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumIn(List<Long> values) {
            addCriterion("order_sum in", values, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumNotIn(List<Long> values) {
            addCriterion("order_sum not in", values, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumBetween(Long value1, Long value2) {
            addCriterion("order_sum between", value1, value2, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderSumNotBetween(Long value1, Long value2) {
            addCriterion("order_sum not between", value1, value2, "orderSum");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeIsNull() {
            addCriterion("order_pay_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeIsNotNull() {
            addCriterion("order_pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeEqualTo(String value) {
            addCriterion("order_pay_type =", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeNotEqualTo(String value) {
            addCriterion("order_pay_type <>", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeGreaterThan(String value) {
            addCriterion("order_pay_type >", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("order_pay_type >=", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeLessThan(String value) {
            addCriterion("order_pay_type <", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeLessThanOrEqualTo(String value) {
            addCriterion("order_pay_type <=", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeLike(String value) {
            addCriterion("order_pay_type like", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeNotLike(String value) {
            addCriterion("order_pay_type not like", value, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeIn(List<String> values) {
            addCriterion("order_pay_type in", values, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeNotIn(List<String> values) {
            addCriterion("order_pay_type not in", values, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeBetween(String value1, String value2) {
            addCriterion("order_pay_type between", value1, value2, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andOrderPayTypeNotBetween(String value1, String value2) {
            addCriterion("order_pay_type not between", value1, value2, "orderPayType");
            return (Criteria) this;
        }

        public Criteria andNurseIdIsNull() {
            addCriterion("nurse_id is null");
            return (Criteria) this;
        }

        public Criteria andNurseIdIsNotNull() {
            addCriterion("nurse_id is not null");
            return (Criteria) this;
        }

        public Criteria andNurseIdEqualTo(Long value) {
            addCriterion("nurse_id =", value, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdNotEqualTo(Long value) {
            addCriterion("nurse_id <>", value, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdGreaterThan(Long value) {
            addCriterion("nurse_id >", value, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("nurse_id >=", value, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdLessThan(Long value) {
            addCriterion("nurse_id <", value, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdLessThanOrEqualTo(Long value) {
            addCriterion("nurse_id <=", value, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdIn(List<Long> values) {
            addCriterion("nurse_id in", values, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdNotIn(List<Long> values) {
            addCriterion("nurse_id not in", values, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdBetween(Long value1, Long value2) {
            addCriterion("nurse_id between", value1, value2, "nurseId");
            return (Criteria) this;
        }

        public Criteria andNurseIdNotBetween(Long value1, Long value2) {
            addCriterion("nurse_id not between", value1, value2, "nurseId");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidIsNull() {
            addCriterion("policy_pid is null");
            return (Criteria) this;
        }

        public Criteria andPolicyPidIsNotNull() {
            addCriterion("policy_pid is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyPidEqualTo(String value) {
            addCriterion("policy_pid =", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidNotEqualTo(String value) {
            addCriterion("policy_pid <>", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidGreaterThan(String value) {
            addCriterion("policy_pid >", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidGreaterThanOrEqualTo(String value) {
            addCriterion("policy_pid >=", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidLessThan(String value) {
            addCriterion("policy_pid <", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidLessThanOrEqualTo(String value) {
            addCriterion("policy_pid <=", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidLike(String value) {
            addCriterion("policy_pid like", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidNotLike(String value) {
            addCriterion("policy_pid not like", value, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidIn(List<String> values) {
            addCriterion("policy_pid in", values, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidNotIn(List<String> values) {
            addCriterion("policy_pid not in", values, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidBetween(String value1, String value2) {
            addCriterion("policy_pid between", value1, value2, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyPidNotBetween(String value1, String value2) {
            addCriterion("policy_pid not between", value1, value2, "policyPid");
            return (Criteria) this;
        }

        public Criteria andPolicyIdIsNull() {
            addCriterion("policy_id is null");
            return (Criteria) this;
        }

        public Criteria andPolicyIdIsNotNull() {
            addCriterion("policy_id is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyIdEqualTo(Long value) {
            addCriterion("policy_id =", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdNotEqualTo(Long value) {
            addCriterion("policy_id <>", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdGreaterThan(Long value) {
            addCriterion("policy_id >", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("policy_id >=", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdLessThan(Long value) {
            addCriterion("policy_id <", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdLessThanOrEqualTo(Long value) {
            addCriterion("policy_id <=", value, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdIn(List<Long> values) {
            addCriterion("policy_id in", values, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdNotIn(List<Long> values) {
            addCriterion("policy_id not in", values, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdBetween(Long value1, Long value2) {
            addCriterion("policy_id between", value1, value2, "policyId");
            return (Criteria) this;
        }

        public Criteria andPolicyIdNotBetween(Long value1, Long value2) {
            addCriterion("policy_id not between", value1, value2, "policyId");
            return (Criteria) this;
        }

        public Criteria andRequestStrIsNull() {
            addCriterion("request_str is null");
            return (Criteria) this;
        }

        public Criteria andRequestStrIsNotNull() {
            addCriterion("request_str is not null");
            return (Criteria) this;
        }

        public Criteria andRequestStrEqualTo(String value) {
            addCriterion("request_str =", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotEqualTo(String value) {
            addCriterion("request_str <>", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrGreaterThan(String value) {
            addCriterion("request_str >", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrGreaterThanOrEqualTo(String value) {
            addCriterion("request_str >=", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrLessThan(String value) {
            addCriterion("request_str <", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrLessThanOrEqualTo(String value) {
            addCriterion("request_str <=", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrLike(String value) {
            addCriterion("request_str like", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotLike(String value) {
            addCriterion("request_str not like", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrIn(List<String> values) {
            addCriterion("request_str in", values, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotIn(List<String> values) {
            addCriterion("request_str not in", values, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrBetween(String value1, String value2) {
            addCriterion("request_str between", value1, value2, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotBetween(String value1, String value2) {
            addCriterion("request_str not between", value1, value2, "requestStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrIsNull() {
            addCriterion("reponse_str is null");
            return (Criteria) this;
        }

        public Criteria andReponseStrIsNotNull() {
            addCriterion("reponse_str is not null");
            return (Criteria) this;
        }

        public Criteria andReponseStrEqualTo(String value) {
            addCriterion("reponse_str =", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrNotEqualTo(String value) {
            addCriterion("reponse_str <>", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrGreaterThan(String value) {
            addCriterion("reponse_str >", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrGreaterThanOrEqualTo(String value) {
            addCriterion("reponse_str >=", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrLessThan(String value) {
            addCriterion("reponse_str <", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrLessThanOrEqualTo(String value) {
            addCriterion("reponse_str <=", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrLike(String value) {
            addCriterion("reponse_str like", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrNotLike(String value) {
            addCriterion("reponse_str not like", value, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrIn(List<String> values) {
            addCriterion("reponse_str in", values, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrNotIn(List<String> values) {
            addCriterion("reponse_str not in", values, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrBetween(String value1, String value2) {
            addCriterion("reponse_str between", value1, value2, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andReponseStrNotBetween(String value1, String value2) {
            addCriterion("reponse_str not between", value1, value2, "reponseStr");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNull() {
            addCriterion("request_time is null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("request_time is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("request_time =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("request_time <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("request_time >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("request_time >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("request_time <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("request_time <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("request_time in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("request_time not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("request_time between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("request_time not between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeIsNull() {
            addCriterion("reponse_time is null");
            return (Criteria) this;
        }

        public Criteria andReponseTimeIsNotNull() {
            addCriterion("reponse_time is not null");
            return (Criteria) this;
        }

        public Criteria andReponseTimeEqualTo(Date value) {
            addCriterion("reponse_time =", value, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeNotEqualTo(Date value) {
            addCriterion("reponse_time <>", value, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeGreaterThan(Date value) {
            addCriterion("reponse_time >", value, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reponse_time >=", value, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeLessThan(Date value) {
            addCriterion("reponse_time <", value, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeLessThanOrEqualTo(Date value) {
            addCriterion("reponse_time <=", value, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeIn(List<Date> values) {
            addCriterion("reponse_time in", values, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeNotIn(List<Date> values) {
            addCriterion("reponse_time not in", values, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeBetween(Date value1, Date value2) {
            addCriterion("reponse_time between", value1, value2, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andReponseTimeNotBetween(Date value1, Date value2) {
            addCriterion("reponse_time not between", value1, value2, "reponseTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}