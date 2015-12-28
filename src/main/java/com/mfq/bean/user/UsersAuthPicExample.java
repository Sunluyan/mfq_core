package com.mfq.bean.user;

import java.util.ArrayList;
import java.util.List;

public class UsersAuthPicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsersAuthPicExample() {
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

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardFIsNull() {
            addCriterion("id_card_f is null");
            return (Criteria) this;
        }

        public Criteria andIdCardFIsNotNull() {
            addCriterion("id_card_f is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardFEqualTo(String value) {
            addCriterion("id_card_f =", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFNotEqualTo(String value) {
            addCriterion("id_card_f <>", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFGreaterThan(String value) {
            addCriterion("id_card_f >", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_f >=", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFLessThan(String value) {
            addCriterion("id_card_f <", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFLessThanOrEqualTo(String value) {
            addCriterion("id_card_f <=", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFLike(String value) {
            addCriterion("id_card_f like", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFNotLike(String value) {
            addCriterion("id_card_f not like", value, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFIn(List<String> values) {
            addCriterion("id_card_f in", values, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFNotIn(List<String> values) {
            addCriterion("id_card_f not in", values, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFBetween(String value1, String value2) {
            addCriterion("id_card_f between", value1, value2, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardFNotBetween(String value1, String value2) {
            addCriterion("id_card_f not between", value1, value2, "idCardF");
            return (Criteria) this;
        }

        public Criteria andIdCardRIsNull() {
            addCriterion("id_card_r is null");
            return (Criteria) this;
        }

        public Criteria andIdCardRIsNotNull() {
            addCriterion("id_card_r is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardREqualTo(String value) {
            addCriterion("id_card_r =", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRNotEqualTo(String value) {
            addCriterion("id_card_r <>", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRGreaterThan(String value) {
            addCriterion("id_card_r >", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_r >=", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRLessThan(String value) {
            addCriterion("id_card_r <", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRLessThanOrEqualTo(String value) {
            addCriterion("id_card_r <=", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRLike(String value) {
            addCriterion("id_card_r like", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRNotLike(String value) {
            addCriterion("id_card_r not like", value, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRIn(List<String> values) {
            addCriterion("id_card_r in", values, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRNotIn(List<String> values) {
            addCriterion("id_card_r not in", values, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRBetween(String value1, String value2) {
            addCriterion("id_card_r between", value1, value2, "idCardR");
            return (Criteria) this;
        }

        public Criteria andIdCardRNotBetween(String value1, String value2) {
            addCriterion("id_card_r not between", value1, value2, "idCardR");
            return (Criteria) this;
        }

        public Criteria andSocialIsNull() {
            addCriterion("social is null");
            return (Criteria) this;
        }

        public Criteria andSocialIsNotNull() {
            addCriterion("social is not null");
            return (Criteria) this;
        }

        public Criteria andSocialEqualTo(String value) {
            addCriterion("social =", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialNotEqualTo(String value) {
            addCriterion("social <>", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialGreaterThan(String value) {
            addCriterion("social >", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialGreaterThanOrEqualTo(String value) {
            addCriterion("social >=", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialLessThan(String value) {
            addCriterion("social <", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialLessThanOrEqualTo(String value) {
            addCriterion("social <=", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialLike(String value) {
            addCriterion("social like", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialNotLike(String value) {
            addCriterion("social not like", value, "social");
            return (Criteria) this;
        }

        public Criteria andSocialIn(List<String> values) {
            addCriterion("social in", values, "social");
            return (Criteria) this;
        }

        public Criteria andSocialNotIn(List<String> values) {
            addCriterion("social not in", values, "social");
            return (Criteria) this;
        }

        public Criteria andSocialBetween(String value1, String value2) {
            addCriterion("social between", value1, value2, "social");
            return (Criteria) this;
        }

        public Criteria andSocialNotBetween(String value1, String value2) {
            addCriterion("social not between", value1, value2, "social");
            return (Criteria) this;
        }

        public Criteria andSocialFIsNull() {
            addCriterion("social_f is null");
            return (Criteria) this;
        }

        public Criteria andSocialFIsNotNull() {
            addCriterion("social_f is not null");
            return (Criteria) this;
        }

        public Criteria andSocialFEqualTo(String value) {
            addCriterion("social_f =", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFNotEqualTo(String value) {
            addCriterion("social_f <>", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFGreaterThan(String value) {
            addCriterion("social_f >", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFGreaterThanOrEqualTo(String value) {
            addCriterion("social_f >=", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFLessThan(String value) {
            addCriterion("social_f <", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFLessThanOrEqualTo(String value) {
            addCriterion("social_f <=", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFLike(String value) {
            addCriterion("social_f like", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFNotLike(String value) {
            addCriterion("social_f not like", value, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFIn(List<String> values) {
            addCriterion("social_f in", values, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFNotIn(List<String> values) {
            addCriterion("social_f not in", values, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFBetween(String value1, String value2) {
            addCriterion("social_f between", value1, value2, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialFNotBetween(String value1, String value2) {
            addCriterion("social_f not between", value1, value2, "socialF");
            return (Criteria) this;
        }

        public Criteria andSocialRIsNull() {
            addCriterion("social_r is null");
            return (Criteria) this;
        }

        public Criteria andSocialRIsNotNull() {
            addCriterion("social_r is not null");
            return (Criteria) this;
        }

        public Criteria andSocialREqualTo(String value) {
            addCriterion("social_r =", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRNotEqualTo(String value) {
            addCriterion("social_r <>", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRGreaterThan(String value) {
            addCriterion("social_r >", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRGreaterThanOrEqualTo(String value) {
            addCriterion("social_r >=", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRLessThan(String value) {
            addCriterion("social_r <", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRLessThanOrEqualTo(String value) {
            addCriterion("social_r <=", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRLike(String value) {
            addCriterion("social_r like", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRNotLike(String value) {
            addCriterion("social_r not like", value, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRIn(List<String> values) {
            addCriterion("social_r in", values, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRNotIn(List<String> values) {
            addCriterion("social_r not in", values, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRBetween(String value1, String value2) {
            addCriterion("social_r between", value1, value2, "socialR");
            return (Criteria) this;
        }

        public Criteria andSocialRNotBetween(String value1, String value2) {
            addCriterion("social_r not between", value1, value2, "socialR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitIsNull() {
            addCriterion("work_permit is null");
            return (Criteria) this;
        }

        public Criteria andWorkPermitIsNotNull() {
            addCriterion("work_permit is not null");
            return (Criteria) this;
        }

        public Criteria andWorkPermitEqualTo(String value) {
            addCriterion("work_permit =", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitNotEqualTo(String value) {
            addCriterion("work_permit <>", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitGreaterThan(String value) {
            addCriterion("work_permit >", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitGreaterThanOrEqualTo(String value) {
            addCriterion("work_permit >=", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitLessThan(String value) {
            addCriterion("work_permit <", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitLessThanOrEqualTo(String value) {
            addCriterion("work_permit <=", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitLike(String value) {
            addCriterion("work_permit like", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitNotLike(String value) {
            addCriterion("work_permit not like", value, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitIn(List<String> values) {
            addCriterion("work_permit in", values, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitNotIn(List<String> values) {
            addCriterion("work_permit not in", values, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitBetween(String value1, String value2) {
            addCriterion("work_permit between", value1, value2, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitNotBetween(String value1, String value2) {
            addCriterion("work_permit not between", value1, value2, "workPermit");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFIsNull() {
            addCriterion("work_permit_f is null");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFIsNotNull() {
            addCriterion("work_permit_f is not null");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFEqualTo(String value) {
            addCriterion("work_permit_f =", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFNotEqualTo(String value) {
            addCriterion("work_permit_f <>", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFGreaterThan(String value) {
            addCriterion("work_permit_f >", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFGreaterThanOrEqualTo(String value) {
            addCriterion("work_permit_f >=", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFLessThan(String value) {
            addCriterion("work_permit_f <", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFLessThanOrEqualTo(String value) {
            addCriterion("work_permit_f <=", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFLike(String value) {
            addCriterion("work_permit_f like", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFNotLike(String value) {
            addCriterion("work_permit_f not like", value, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFIn(List<String> values) {
            addCriterion("work_permit_f in", values, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFNotIn(List<String> values) {
            addCriterion("work_permit_f not in", values, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFBetween(String value1, String value2) {
            addCriterion("work_permit_f between", value1, value2, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitFNotBetween(String value1, String value2) {
            addCriterion("work_permit_f not between", value1, value2, "workPermitF");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRIsNull() {
            addCriterion("work_permit_r is null");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRIsNotNull() {
            addCriterion("work_permit_r is not null");
            return (Criteria) this;
        }

        public Criteria andWorkPermitREqualTo(String value) {
            addCriterion("work_permit_r =", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRNotEqualTo(String value) {
            addCriterion("work_permit_r <>", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRGreaterThan(String value) {
            addCriterion("work_permit_r >", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRGreaterThanOrEqualTo(String value) {
            addCriterion("work_permit_r >=", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRLessThan(String value) {
            addCriterion("work_permit_r <", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRLessThanOrEqualTo(String value) {
            addCriterion("work_permit_r <=", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRLike(String value) {
            addCriterion("work_permit_r like", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRNotLike(String value) {
            addCriterion("work_permit_r not like", value, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRIn(List<String> values) {
            addCriterion("work_permit_r in", values, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRNotIn(List<String> values) {
            addCriterion("work_permit_r not in", values, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRBetween(String value1, String value2) {
            addCriterion("work_permit_r between", value1, value2, "workPermitR");
            return (Criteria) this;
        }

        public Criteria andWorkPermitRNotBetween(String value1, String value2) {
            addCriterion("work_permit_r not between", value1, value2, "workPermitR");
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