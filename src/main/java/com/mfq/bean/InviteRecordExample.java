package com.mfq.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InviteRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InviteRecordExample() {
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

        public Criteria andInvitedTimeIsNull() {
            addCriterion("invited_time is null");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeIsNotNull() {
            addCriterion("invited_time is not null");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeEqualTo(Date value) {
            addCriterion("invited_time =", value, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeNotEqualTo(Date value) {
            addCriterion("invited_time <>", value, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeGreaterThan(Date value) {
            addCriterion("invited_time >", value, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("invited_time >=", value, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeLessThan(Date value) {
            addCriterion("invited_time <", value, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeLessThanOrEqualTo(Date value) {
            addCriterion("invited_time <=", value, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeIn(List<Date> values) {
            addCriterion("invited_time in", values, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeNotIn(List<Date> values) {
            addCriterion("invited_time not in", values, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeBetween(Date value1, Date value2) {
            addCriterion("invited_time between", value1, value2, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedTimeNotBetween(Date value1, Date value2) {
            addCriterion("invited_time not between", value1, value2, "invitedTime");
            return (Criteria) this;
        }

        public Criteria andInvitedUidIsNull() {
            addCriterion("invited_uid is null");
            return (Criteria) this;
        }

        public Criteria andInvitedUidIsNotNull() {
            addCriterion("invited_uid is not null");
            return (Criteria) this;
        }

        public Criteria andInvitedUidEqualTo(Long value) {
            addCriterion("invited_uid =", value, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidNotEqualTo(Long value) {
            addCriterion("invited_uid <>", value, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidGreaterThan(Long value) {
            addCriterion("invited_uid >", value, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidGreaterThanOrEqualTo(Long value) {
            addCriterion("invited_uid >=", value, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidLessThan(Long value) {
            addCriterion("invited_uid <", value, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidLessThanOrEqualTo(Long value) {
            addCriterion("invited_uid <=", value, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidIn(List<Long> values) {
            addCriterion("invited_uid in", values, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidNotIn(List<Long> values) {
            addCriterion("invited_uid not in", values, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidBetween(Long value1, Long value2) {
            addCriterion("invited_uid between", value1, value2, "invitedUid");
            return (Criteria) this;
        }

        public Criteria andInvitedUidNotBetween(Long value1, Long value2) {
            addCriterion("invited_uid not between", value1, value2, "invitedUid");
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