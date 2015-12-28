package com.mfq.net.tongdun.pojo;

import java.io.Serializable;

public class HitRule implements Serializable{
	private static final long serialVersionUID = 6297666052880082771L;
    private String            uuid;                                   // 规则uuid
	private String            id;                                     // 规则编号
    private String            name;                                   // 规则名称
    private String            decision;                               // 该条规则决策结果
    private int               score;                                  // 规则分数
	
    
	
	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDecision() {
		return decision;
	}



	public void setDecision(String decision) {
		this.decision = decision;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
    public String toString() {
        return "rule_name:" + this.name + "\nscore:" + this.score + "\ndescision:" + this.decision + "\n";
    }
}
