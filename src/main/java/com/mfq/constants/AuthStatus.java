package com.mfq.constants;

public enum AuthStatus {
	/**
	 * 认证状态
0 未递交实名认证
1 已通过网络实名认证	-1 未通过网络实名认证
2 已通过人工实名认证	-2 未通过人工实名认证
3 已递交面签申请		-3 未通过面签
4 已通过面签
	 */
    UNREAL(0,"未递交实名认证"),
    NETREAL(1,"已通过网络实名认证"),
    NOTNETREAL(-1,"未通过网络实名认证"),
    REAL(2,"已通过人工实名认证"),
    NOTREAL(-2,"未通过人工实名认证"),
    TOINTERVIEW(3,"已递交面签申请"),
    PASSINTERVIEW(-3,"未通过面签"),
    ALREADYINTERVIEW(4,"已通过面签");
    
    
    int id;
    String desc;
    
    AuthStatus(int id, String desc){
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
	
    public static AuthStatus fromId(int id){
        for(AuthStatus status : AuthStatus.values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }
    
}
