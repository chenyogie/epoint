package com.epoint.domain;

import java.security.KeyStore.PrivateKeyEntry;

public class Pet {
	private String id;
	private String nickname;
	private int sex;
	private int age;
	private int type;
	private Double weight;
	private int isvaccinated;
	private String photo;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public int getIsvaccinated() {
		return isvaccinated;
	}

	public void setIsvaccinated(int isvaccinated) {
		this.isvaccinated = isvaccinated;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", nickname=" + nickname + ", sex=" + sex + ", age=" + age + ", type=" + type
				+ ", weight=" + weight + ", isvaccinated=" + isvaccinated + ", photo=" + photo + ", remark=" + remark
				+ "]";
	}
}
