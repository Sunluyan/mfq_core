package com.mfq.bean.user;

public class Nurse {
    private Integer nurseNumber;

    private String name;

    private String phone;

    private Integer gender;

    private String address;

    private String idCard;
    

	public Integer getNurseNumber() {
		return nurseNumber;
	}

	public void setNurseNumber(Integer nurseNumber) {
		this.nurseNumber = nurseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	

	public Nurse(Integer nurseNumber, String name, String phone,
			Integer gender, String address, String idCard) {
		super();
		this.nurseNumber = nurseNumber;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return "Nurse [nurseNumber=" + nurseNumber + ", name=" + name
				+ ", phone=" + phone + ", gender=" + gender + ", address="
				+ address + ", idCard=" + idCard + "]";
	}

	public Nurse() {
		super();
	}
	
	
	
    
}