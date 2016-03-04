package com.mfq.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andImgSmallIsNull() {
            addCriterion("img_small is null");
            return (Criteria) this;
        }

        public Criteria andImgSmallIsNotNull() {
            addCriterion("img_small is not null");
            return (Criteria) this;
        }

        public Criteria andImgSmallEqualTo(String value) {
            addCriterion("img_small =", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallNotEqualTo(String value) {
            addCriterion("img_small <>", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallGreaterThan(String value) {
            addCriterion("img_small >", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallGreaterThanOrEqualTo(String value) {
            addCriterion("img_small >=", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallLessThan(String value) {
            addCriterion("img_small <", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallLessThanOrEqualTo(String value) {
            addCriterion("img_small <=", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallLike(String value) {
            addCriterion("img_small like", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallNotLike(String value) {
            addCriterion("img_small not like", value, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallIn(List<String> values) {
            addCriterion("img_small in", values, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallNotIn(List<String> values) {
            addCriterion("img_small not in", values, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallBetween(String value1, String value2) {
            addCriterion("img_small between", value1, value2, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andImgSmallNotBetween(String value1, String value2) {
            addCriterion("img_small not between", value1, value2, "imgSmall");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andActivityDescIsNull() {
            addCriterion("activity_desc is null");
            return (Criteria) this;
        }

        public Criteria andActivityDescIsNotNull() {
            addCriterion("activity_desc is not null");
            return (Criteria) this;
        }

        public Criteria andActivityDescEqualTo(String value) {
            addCriterion("activity_desc =", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescNotEqualTo(String value) {
            addCriterion("activity_desc <>", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescGreaterThan(String value) {
            addCriterion("activity_desc >", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescGreaterThanOrEqualTo(String value) {
            addCriterion("activity_desc >=", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescLessThan(String value) {
            addCriterion("activity_desc <", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescLessThanOrEqualTo(String value) {
            addCriterion("activity_desc <=", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescLike(String value) {
            addCriterion("activity_desc like", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescNotLike(String value) {
            addCriterion("activity_desc not like", value, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescIn(List<String> values) {
            addCriterion("activity_desc in", values, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescNotIn(List<String> values) {
            addCriterion("activity_desc not in", values, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescBetween(String value1, String value2) {
            addCriterion("activity_desc between", value1, value2, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andActivityDescNotBetween(String value1, String value2) {
            addCriterion("activity_desc not between", value1, value2, "activityDesc");
            return (Criteria) this;
        }

        public Criteria andLinkIsNull() {
            addCriterion("link is null");
            return (Criteria) this;
        }

        public Criteria andLinkIsNotNull() {
            addCriterion("link is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEqualTo(String value) {
            addCriterion("link =", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotEqualTo(String value) {
            addCriterion("link <>", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThan(String value) {
            addCriterion("link >", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThanOrEqualTo(String value) {
            addCriterion("link >=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThan(String value) {
            addCriterion("link <", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThanOrEqualTo(String value) {
            addCriterion("link <=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLike(String value) {
            addCriterion("link like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotLike(String value) {
            addCriterion("link not like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkIn(List<String> values) {
            addCriterion("link in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotIn(List<String> values) {
            addCriterion("link not in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkBetween(String value1, String value2) {
            addCriterion("link between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotBetween(String value1, String value2) {
            addCriterion("link not between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andBeginAtIsNull() {
            addCriterion("begin_at is null");
            return (Criteria) this;
        }

        public Criteria andBeginAtIsNotNull() {
            addCriterion("begin_at is not null");
            return (Criteria) this;
        }

        public Criteria andBeginAtEqualTo(Date value) {
            addCriterion("begin_at =", value, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtNotEqualTo(Date value) {
            addCriterion("begin_at <>", value, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtGreaterThan(Date value) {
            addCriterion("begin_at >", value, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_at >=", value, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtLessThan(Date value) {
            addCriterion("begin_at <", value, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtLessThanOrEqualTo(Date value) {
            addCriterion("begin_at <=", value, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtIn(List<Date> values) {
            addCriterion("begin_at in", values, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtNotIn(List<Date> values) {
            addCriterion("begin_at not in", values, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtBetween(Date value1, Date value2) {
            addCriterion("begin_at between", value1, value2, "beginAt");
            return (Criteria) this;
        }

        public Criteria andBeginAtNotBetween(Date value1, Date value2) {
            addCriterion("begin_at not between", value1, value2, "beginAt");
            return (Criteria) this;
        }

        public Criteria andEndAtIsNull() {
            addCriterion("end_at is null");
            return (Criteria) this;
        }

        public Criteria andEndAtIsNotNull() {
            addCriterion("end_at is not null");
            return (Criteria) this;
        }

        public Criteria andEndAtEqualTo(Date value) {
            addCriterion("end_at =", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotEqualTo(Date value) {
            addCriterion("end_at <>", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThan(Date value) {
            addCriterion("end_at >", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("end_at >=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThan(Date value) {
            addCriterion("end_at <", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtLessThanOrEqualTo(Date value) {
            addCriterion("end_at <=", value, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtIn(List<Date> values) {
            addCriterion("end_at in", values, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotIn(List<Date> values) {
            addCriterion("end_at not in", values, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtBetween(Date value1, Date value2) {
            addCriterion("end_at between", value1, value2, "endAt");
            return (Criteria) this;
        }

        public Criteria andEndAtNotBetween(Date value1, Date value2) {
            addCriterion("end_at not between", value1, value2, "endAt");
            return (Criteria) this;
        }

        public Criteria andActivityTimeIsNull() {
            addCriterion("activity_time is null");
            return (Criteria) this;
        }

        public Criteria andActivityTimeIsNotNull() {
            addCriterion("activity_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEqualTo(String value) {
            addCriterion("activity_time =", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeNotEqualTo(String value) {
            addCriterion("activity_time <>", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeGreaterThan(String value) {
            addCriterion("activity_time >", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeGreaterThanOrEqualTo(String value) {
            addCriterion("activity_time >=", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeLessThan(String value) {
            addCriterion("activity_time <", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeLessThanOrEqualTo(String value) {
            addCriterion("activity_time <=", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeLike(String value) {
            addCriterion("activity_time like", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeNotLike(String value) {
            addCriterion("activity_time not like", value, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeIn(List<String> values) {
            addCriterion("activity_time in", values, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeNotIn(List<String> values) {
            addCriterion("activity_time not in", values, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeBetween(String value1, String value2) {
            addCriterion("activity_time between", value1, value2, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityTimeNotBetween(String value1, String value2) {
            addCriterion("activity_time not between", value1, value2, "activityTime");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceIsNull() {
            addCriterion("activity_place is null");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceIsNotNull() {
            addCriterion("activity_place is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceEqualTo(String value) {
            addCriterion("activity_place =", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceNotEqualTo(String value) {
            addCriterion("activity_place <>", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceGreaterThan(String value) {
            addCriterion("activity_place >", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("activity_place >=", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceLessThan(String value) {
            addCriterion("activity_place <", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceLessThanOrEqualTo(String value) {
            addCriterion("activity_place <=", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceLike(String value) {
            addCriterion("activity_place like", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceNotLike(String value) {
            addCriterion("activity_place not like", value, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceIn(List<String> values) {
            addCriterion("activity_place in", values, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceNotIn(List<String> values) {
            addCriterion("activity_place not in", values, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceBetween(String value1, String value2) {
            addCriterion("activity_place between", value1, value2, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andActivityPlaceNotBetween(String value1, String value2) {
            addCriterion("activity_place not between", value1, value2, "activityPlace");
            return (Criteria) this;
        }

        public Criteria andImgBigIsNull() {
            addCriterion("img_big is null");
            return (Criteria) this;
        }

        public Criteria andImgBigIsNotNull() {
            addCriterion("img_big is not null");
            return (Criteria) this;
        }

        public Criteria andImgBigEqualTo(String value) {
            addCriterion("img_big =", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigNotEqualTo(String value) {
            addCriterion("img_big <>", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigGreaterThan(String value) {
            addCriterion("img_big >", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigGreaterThanOrEqualTo(String value) {
            addCriterion("img_big >=", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigLessThan(String value) {
            addCriterion("img_big <", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigLessThanOrEqualTo(String value) {
            addCriterion("img_big <=", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigLike(String value) {
            addCriterion("img_big like", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigNotLike(String value) {
            addCriterion("img_big not like", value, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigIn(List<String> values) {
            addCriterion("img_big in", values, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigNotIn(List<String> values) {
            addCriterion("img_big not in", values, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigBetween(String value1, String value2) {
            addCriterion("img_big between", value1, value2, "imgBig");
            return (Criteria) this;
        }

        public Criteria andImgBigNotBetween(String value1, String value2) {
            addCriterion("img_big not between", value1, value2, "imgBig");
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