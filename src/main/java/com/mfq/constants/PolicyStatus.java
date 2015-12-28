package com.mfq.constants;

/**
 * banner flag
 * @author hui
 *
 */
public enum PolicyStatus {
	
//	DEFAULT(0, "default","无保单"),
//    APPLY(1, "apply","投保中。。,未支付"),
//    CREATED(2, "created","已生成保单"),
//    COMPLAINT_APPLY(3, "cp_apply", "投诉申请中。。。"),
//	IN_COMPLAINT(4,"in_cp","投诉中"),
//	COMPLAINT_REJECT(5,"cp_reject","投诉驳回"),
//	DUE(6,"due","已过期");
	
	WITHOUT(-1, "未投保"),
	AUDITING(0, "审核中"),
	BEFOREINSURE_REVOKE(1,"承保前撤单成功"),
	INSUREING(2,"理赔申请中"),
	FAILURE(3,"投保失败"),
	SUCCESS(4,"投保成功"),
	INSURE_EFFECT(5,"保单生效"),
	COMPENSATE_APPLY(6,"理赔申请成功"),
	COMPENSATE_REFUSE(7,"拒绝理赔"),
	COMPENSATE_WAITE(8,"等待理赔"),
	COMPENSATE_FINISH(9,"理赔完成"),
	POLICY_DUE(10,"保单过期"),
	BREAK_CONTRACT(11,"违约"),
	RELIEVE_INSURE_PERSON(12,"投保人解除保险解除"),
	RELIEVE_INSURE_COMPANY(13,"保险公司解除保险解除"),
	INSURE_TERMIN(14,"保险终止");
	
	
	
	
	
    int id;
    String flag;
    String desc;
    
    PolicyStatus(int id, String desc){
        this.id = id;
        this.desc = desc;
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
    
    public static PolicyStatus fromId(int id){
        for(PolicyStatus status : PolicyStatus.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }
}
