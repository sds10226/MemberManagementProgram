package org.comstudy21.mms.mdto;

import java.util.Vector;

public class MemberDto {
	private int mno;
	private String name, age, sex, phone, address, email, sns, birth, joinDate, picture;
	public MemberDto() {
		this(0, "", "", "", "", "", "", "", "", "","");
	}

	public MemberDto(int mno, String name, String age, String sex, String phone, String address, String email, String sns,
			String birth, String joinDate, String picture) {
		this.mno = mno;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.sns = sns;
		this.birth = birth;
		this.joinDate = joinDate;
		this.picture = picture;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSns() {
		return sns;
	}

	public void setSns(String sns) {
		this.sns = sns;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", " + (name != null ? "name=" + name + ", " : "")
				+ (age != null ? "age=" + age + ", " : "") + (sex != null ? "sex=" + sex + ", " : "")
				+ (phone != null ? "phone=" + phone + ", " : "") + (address != null ? "address=" + address + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (sns != null ? "sns=" + sns + ", " : "")
				+ (birth != null ? "birth=" + birth + ", " : "")
				+ (joinDate != null ? "joinDate=" + joinDate + ", " : "")
				+ (picture != null ? "picture=" + picture : "") + "]";
	}

	public Vector<String> getList() {
		Vector<String> list = new Vector<>();
		list.add(mno+"");
		list.add(name);
		list.add(age);
		list.add(sex);
		list.add(phone);
		list.add(address);
		list.add(email);
		list.add(sns);
		list.add(birth);		
		list.add(joinDate);	
		list.add(picture);	
		
		return list;
	}
}
