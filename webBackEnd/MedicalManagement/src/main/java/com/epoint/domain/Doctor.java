package com.epoint.domain;

import java.util.Date;

public class Doctor {
	private String id;
	private String name;
	private int sex;
	private Date birth;
	private String docNative;
	private int politics;
	private String departments;
	private String position;
	private String telephone;
	private int count;
	private int type;
	private String others;
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", sex=" + sex + ", birth=" + birth + ", docNative=" + docNative
				+ ", politics=" + politics + ", departments=" + departments + ", position=" + position + ", telepnone="
				+ telephone + ", count=" + count + ", type=" + type + ", others=" + others + "]";
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getDocNative() {
		return docNative;
	}
	public void setDocNative(String docNative) {
		this.docNative = docNative;
	}
	public int getPolitics() {
		return politics;
	}
	public void setPolitics(int politics) {
		this.politics = politics;
	}
	public String getDepartments() {
		return departments;
	}
	public void setDepartments(String departments) {
		this.departments = departments;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
}
