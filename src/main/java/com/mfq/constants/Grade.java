package com.mfq.constants;

/**
 * 学生年级
 * 最小分期数8，则离毕业时间最短应为10个月
 */
public enum Grade {
    
	DEFAULT(0, "未设置年级", 0, 0),
    JUNIOR_COLLEGE_21(1, "专科（二年制）-专一", 6000, 20),
    JUNIOR_COLLEGE_22(2, "专科（二年制）-专二", 4000, 8),
    JUNIOR_COLLEGE_31(3, "专科（三年制）-专一", 6000, 24),
    JUNIOR_COLLEGE_32(4, "专科（三年制）-专二", 6000, 20),
    JUNIOR_COLLEGE_33(5, "专科（三年制）-专三", 4000, 8),
    BACHELOR_1(6, "本科（四年制）-大一", 10000, 24),
    BACHELOR_2(7, "本科（四年制）-大二", 10000, 24),
    BACHELOR_3(8, "本科（四年制）-大三", 10000, 20),
    BACHELOR_4(9, "本科（四年制）-大四", 6000, 8),
    MASTER_21(10, "研究生（二年制）-研一", 10000, 20),
    MASTER_22(11, "研究生（二年制）-研二", 6000, 8),
    MASTER_31(12, "研究生（三年制）-研一", 10000, 24),
    MASTER_32(13, "研究生（三年制）-研二", 10000, 20),
    MASTER_33(14, "研究生（三年制）-研三", 6000, 8);
    
    int id; //  ID
    String desc; // 年级说明
    int amount; // 额度
    int period; // 最大分期数
    
    Grade(int id, String desc, int amount, int period) {
        this.id = id;
        this.desc = desc;
        this.amount = amount;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public static Grade fromId(int id){
        for(Grade g : Grade.values()){
            if(g.getId() == id){
                return g;
            }
        }
        return null;
    }
}