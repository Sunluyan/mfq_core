package com.mfq.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PolicyInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PolicyInfoExample() {
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

        public Criteria andProductNoIsNull() {
            addCriterion("product_no is null");
            return (Criteria) this;
        }

        public Criteria andProductNoIsNotNull() {
            addCriterion("product_no is not null");
            return (Criteria) this;
        }

        public Criteria andProductNoEqualTo(String value) {
            addCriterion("product_no =", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotEqualTo(String value) {
            addCriterion("product_no <>", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThan(String value) {
            addCriterion("product_no >", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThanOrEqualTo(String value) {
            addCriterion("product_no >=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThan(String value) {
            addCriterion("product_no <", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThanOrEqualTo(String value) {
            addCriterion("product_no <=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLike(String value) {
            addCriterion("product_no like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotLike(String value) {
            addCriterion("product_no not like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoIn(List<String> values) {
            addCriterion("product_no in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotIn(List<String> values) {
            addCriterion("product_no not in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoBetween(String value1, String value2) {
            addCriterion("product_no between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotBetween(String value1, String value2) {
            addCriterion("product_no not between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeIsNull() {
            addCriterion("insurance_code is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeIsNotNull() {
            addCriterion("insurance_code is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeEqualTo(String value) {
            addCriterion("insurance_code =", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeNotEqualTo(String value) {
            addCriterion("insurance_code <>", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeGreaterThan(String value) {
            addCriterion("insurance_code >", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_code >=", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeLessThan(String value) {
            addCriterion("insurance_code <", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeLessThanOrEqualTo(String value) {
            addCriterion("insurance_code <=", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeLike(String value) {
            addCriterion("insurance_code like", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeNotLike(String value) {
            addCriterion("insurance_code not like", value, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeIn(List<String> values) {
            addCriterion("insurance_code in", values, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeNotIn(List<String> values) {
            addCriterion("insurance_code not in", values, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeBetween(String value1, String value2) {
            addCriterion("insurance_code between", value1, value2, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceCodeNotBetween(String value1, String value2) {
            addCriterion("insurance_code not between", value1, value2, "insuranceCode");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderIsNull() {
            addCriterion("policy_holder is null");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderIsNotNull() {
            addCriterion("policy_holder is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderEqualTo(String value) {
            addCriterion("policy_holder =", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderNotEqualTo(String value) {
            addCriterion("policy_holder <>", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderGreaterThan(String value) {
            addCriterion("policy_holder >", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderGreaterThanOrEqualTo(String value) {
            addCriterion("policy_holder >=", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderLessThan(String value) {
            addCriterion("policy_holder <", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderLessThanOrEqualTo(String value) {
            addCriterion("policy_holder <=", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderLike(String value) {
            addCriterion("policy_holder like", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderNotLike(String value) {
            addCriterion("policy_holder not like", value, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderIn(List<String> values) {
            addCriterion("policy_holder in", values, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderNotIn(List<String> values) {
            addCriterion("policy_holder not in", values, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderBetween(String value1, String value2) {
            addCriterion("policy_holder between", value1, value2, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyHolderNotBetween(String value1, String value2) {
            addCriterion("policy_holder not between", value1, value2, "policyHolder");
            return (Criteria) this;
        }

        public Criteria andPolicyNoIsNull() {
            addCriterion("policy_no is null");
            return (Criteria) this;
        }

        public Criteria andPolicyNoIsNotNull() {
            addCriterion("policy_no is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyNoEqualTo(String value) {
            addCriterion("policy_no =", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotEqualTo(String value) {
            addCriterion("policy_no <>", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoGreaterThan(String value) {
            addCriterion("policy_no >", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoGreaterThanOrEqualTo(String value) {
            addCriterion("policy_no >=", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoLessThan(String value) {
            addCriterion("policy_no <", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoLessThanOrEqualTo(String value) {
            addCriterion("policy_no <=", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoLike(String value) {
            addCriterion("policy_no like", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotLike(String value) {
            addCriterion("policy_no not like", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoIn(List<String> values) {
            addCriterion("policy_no in", values, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotIn(List<String> values) {
            addCriterion("policy_no not in", values, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoBetween(String value1, String value2) {
            addCriterion("policy_no between", value1, value2, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotBetween(String value1, String value2) {
            addCriterion("policy_no not between", value1, value2, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPremiumIsNull() {
            addCriterion("premium is null");
            return (Criteria) this;
        }

        public Criteria andPremiumIsNotNull() {
            addCriterion("premium is not null");
            return (Criteria) this;
        }

        public Criteria andPremiumEqualTo(Integer value) {
            addCriterion("premium =", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumNotEqualTo(Integer value) {
            addCriterion("premium <>", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumGreaterThan(Integer value) {
            addCriterion("premium >", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumGreaterThanOrEqualTo(Integer value) {
            addCriterion("premium >=", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumLessThan(Integer value) {
            addCriterion("premium <", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumLessThanOrEqualTo(Integer value) {
            addCriterion("premium <=", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumIn(List<Integer> values) {
            addCriterion("premium in", values, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumNotIn(List<Integer> values) {
            addCriterion("premium not in", values, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumBetween(Integer value1, Integer value2) {
            addCriterion("premium between", value1, value2, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumNotBetween(Integer value1, Integer value2) {
            addCriterion("premium not between", value1, value2, "premium");
            return (Criteria) this;
        }

        public Criteria andSumInsuredIsNull() {
            addCriterion("sum_insured is null");
            return (Criteria) this;
        }

        public Criteria andSumInsuredIsNotNull() {
            addCriterion("sum_insured is not null");
            return (Criteria) this;
        }

        public Criteria andSumInsuredEqualTo(Integer value) {
            addCriterion("sum_insured =", value, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredNotEqualTo(Integer value) {
            addCriterion("sum_insured <>", value, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredGreaterThan(Integer value) {
            addCriterion("sum_insured >", value, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredGreaterThanOrEqualTo(Integer value) {
            addCriterion("sum_insured >=", value, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredLessThan(Integer value) {
            addCriterion("sum_insured <", value, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredLessThanOrEqualTo(Integer value) {
            addCriterion("sum_insured <=", value, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredIn(List<Integer> values) {
            addCriterion("sum_insured in", values, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredNotIn(List<Integer> values) {
            addCriterion("sum_insured not in", values, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredBetween(Integer value1, Integer value2) {
            addCriterion("sum_insured between", value1, value2, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andSumInsuredNotBetween(Integer value1, Integer value2) {
            addCriterion("sum_insured not between", value1, value2, "sumInsured");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateIsNull() {
            addCriterion("policy_bdate is null");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateIsNotNull() {
            addCriterion("policy_bdate is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateEqualTo(Date value) {
            addCriterion("policy_bdate =", value, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateNotEqualTo(Date value) {
            addCriterion("policy_bdate <>", value, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateGreaterThan(Date value) {
            addCriterion("policy_bdate >", value, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateGreaterThanOrEqualTo(Date value) {
            addCriterion("policy_bdate >=", value, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateLessThan(Date value) {
            addCriterion("policy_bdate <", value, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateLessThanOrEqualTo(Date value) {
            addCriterion("policy_bdate <=", value, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateIn(List<Date> values) {
            addCriterion("policy_bdate in", values, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateNotIn(List<Date> values) {
            addCriterion("policy_bdate not in", values, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateBetween(Date value1, Date value2) {
            addCriterion("policy_bdate between", value1, value2, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyBdateNotBetween(Date value1, Date value2) {
            addCriterion("policy_bdate not between", value1, value2, "policyBdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateIsNull() {
            addCriterion("policy_edate is null");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateIsNotNull() {
            addCriterion("policy_edate is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateEqualTo(Date value) {
            addCriterion("policy_edate =", value, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateNotEqualTo(Date value) {
            addCriterion("policy_edate <>", value, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateGreaterThan(Date value) {
            addCriterion("policy_edate >", value, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateGreaterThanOrEqualTo(Date value) {
            addCriterion("policy_edate >=", value, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateLessThan(Date value) {
            addCriterion("policy_edate <", value, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateLessThanOrEqualTo(Date value) {
            addCriterion("policy_edate <=", value, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateIn(List<Date> values) {
            addCriterion("policy_edate in", values, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateNotIn(List<Date> values) {
            addCriterion("policy_edate not in", values, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateBetween(Date value1, Date value2) {
            addCriterion("policy_edate between", value1, value2, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyEdateNotBetween(Date value1, Date value2) {
            addCriterion("policy_edate not between", value1, value2, "policyEdate");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusIsNull() {
            addCriterion("policy_status is null");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusIsNotNull() {
            addCriterion("policy_status is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusEqualTo(String value) {
            addCriterion("policy_status =", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusNotEqualTo(String value) {
            addCriterion("policy_status <>", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusGreaterThan(String value) {
            addCriterion("policy_status >", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusGreaterThanOrEqualTo(String value) {
            addCriterion("policy_status >=", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusLessThan(String value) {
            addCriterion("policy_status <", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusLessThanOrEqualTo(String value) {
            addCriterion("policy_status <=", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusLike(String value) {
            addCriterion("policy_status like", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusNotLike(String value) {
            addCriterion("policy_status not like", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusIn(List<String> values) {
            addCriterion("policy_status in", values, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusNotIn(List<String> values) {
            addCriterion("policy_status not in", values, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusBetween(String value1, String value2) {
            addCriterion("policy_status between", value1, value2, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusNotBetween(String value1, String value2) {
            addCriterion("policy_status not between", value1, value2, "policyStatus");
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