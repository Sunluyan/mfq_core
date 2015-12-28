package com.mfq.bean;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 评论表
 * @author yongshan.xing
 *
 */
public class Comment {
	long id;
	int tId;	//唯一标识 ，评论产品所属类型tid
	long pId;	//唯一标识 id
	String orderNo;	//订单号
	String srcSite;	//如果是抓取，取值是来源网站，如果是UGC，取值是meifenqi
	int type;			//用于区分UGC 0,抓取 1和改写的2
	int rank;			//评价星级0-5
	long uid;			//如果是UGC，则有值
	String userName;	//评论发表用户
	Date createDate;	//发布日期
	String content;		//评论内容
	String absContent;	//如果是抓取的，取值是评论摘要，否则为空
	String address;		//接收地址
	String userIp;	//评论用户ip
	boolean checkFlag;	//审核通过标识1通过 0未通过
	String checkUser;	//审核人
	long rootCid;		//回复评论，默认为0
	Date lastUpdateTime;	//最后更新时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSrcSite() {
		return srcSite;
	}
	public void setSrcSite(String srcSite) {
		this.srcSite = srcSite;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAbsContent() {
		return absContent;
	}
	public void setAbsContent(String absContent) {
		this.absContent = absContent;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public boolean isCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public long getRootCid() {
		return rootCid;
	}
	public void setRootCid(long rootCid) {
		this.rootCid = rootCid;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
