package com.mfq.bean;

import java.util.Date;

/**
 * 收藏夹
 * 
 * @author hui
 *
 */
public class Favorites {
	long uid; // 用户id
	long pid; // 产品ID
	Date updated; // 最后更新时间

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
