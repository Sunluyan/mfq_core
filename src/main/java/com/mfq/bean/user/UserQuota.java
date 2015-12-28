package com.mfq.bean.user;

import java.math.BigDecimal;
import java.util.Date;

import com.mfq.constants.Grade;

public class UserQuota {

	
	/**
	 * 
	 */
    long uid; // 帐户ID
    String realname; // 真实姓名
    String contact; // 联系电话
    
    String school; // 学校
    Grade grade; // 学历－年级
    String classes; // 班级
    Date startschoolAt; // 毕业时间
    int schoolLocationId; // 学校所在地区ID
    String schoolLocation; // 学校地址
    String studentId; // 学号
    String speciality; // 专业
    String faculty;	//院系
    String schoolRemark;//学生备注
    String schoolLevel;	//学生层次 （即学历，如本科，大专...）
    int scholasticYears;
    
    String homesite; // 主页
    
    String idCard; // 身份证号
    String idCardReverse;//身份证背面照片
    String idCardFront;	//身份证正面照片
    BigDecimal balance; // 帐户余额
    BigDecimal present; // 赠送金额-要区分是微信赠送还是充值赠送，假如是活动充值赠送则等同现金，微信赠送部分需要判断订单金额才可使用
    BigDecimal quotaAll; // 信用总额度
    BigDecimal quotaLeft; // 剩余额度
    String company;	//公司名称
    String workRemark; // 上班族备注
    String workYears;	//工作年限
    String socialInsuranceId;	//社保号
    String salary;	//工资范围
    String position;  //职位
    String department; //部门
    
    int userType;	//用户类型 上班族2 或者 学生1
    int wishPlastic; // 是否意愿微整形
    int authStatus; // 认证状态 
    Date createdAt; // 创建额度时间
    Date updatedAt; // 更新额度时间
    
    
    
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getContact() {
		return contact;
	}
	public int getScholasticYears() {
		return scholasticYears;
	}
	public void setScholasticYears(int scholasticYears) {
		this.scholasticYears = scholasticYears;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public Date getStartschoolAt() {
		return startschoolAt;
	}
	public void setStartschoolAt(Date startschoolAt) {
		this.startschoolAt = startschoolAt;
	}
	public int getSchoolLocationId() {
		return schoolLocationId;
	}
	public void setSchoolLocationId(int schoolLocationId) {
		this.schoolLocationId = schoolLocationId;
	}
	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getSchoolRemark() {
		return schoolRemark;
	}
	public void setSchoolRemark(String schoolRemark) {
		this.schoolRemark = schoolRemark;
	}
	public String getSchoolLevel() {
		return schoolLevel;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setSchoolLevel(String schoolLevel) {
		this.schoolLevel = schoolLevel;
	}
	public String getHomesite() {
		return homesite;
	}
	public void setHomesite(String homesite) {
		this.homesite = homesite;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIdCardReverse() {
		return idCardReverse;
	}
	public void setIdCardReverse(String idCardReverse) {
		this.idCardReverse = idCardReverse;
	}
	public String getIdCardFront() {
		return idCardFront;
	}
	public void setIdCardFront(String idCardFront) {
		this.idCardFront = idCardFront;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getPresent() {
		return present;
	}
	public void setPresent(BigDecimal present) {
		this.present = present;
	}
	public BigDecimal getQuotaAll() {
		return quotaAll;
	}
	public void setQuotaAll(BigDecimal quotaAll) {
		this.quotaAll = quotaAll;
	}
	public BigDecimal getQuotaLeft() {
		return quotaLeft;
	}
	public void setQuotaLeft(BigDecimal quotaLeft) {
		this.quotaLeft = quotaLeft;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getWorkRemark() {
		return workRemark;
	}
	public void setWorkRemark(String workRemark) {
		this.workRemark = workRemark;
	}
	public String getWorkYears() {
		return workYears;
	}
	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}
	public String getSocialInsuranceId() {
		return socialInsuranceId;
	}
	public void setSocialInsuranceId(String socialInsuranceId) {
		this.socialInsuranceId = socialInsuranceId;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getWishPlastic() {
		return wishPlastic;
	}
	public void setWishPlastic(int wishPlastic) {
		this.wishPlastic = wishPlastic;
	}
	public int getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
	
	
    
    
    
    

}