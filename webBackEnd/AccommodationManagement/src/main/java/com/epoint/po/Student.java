package com.epoint.po;

/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/9
 * Time: 10:45
 */
public class Student {
	private String stuId;
	private String name;
	private int sex; //0：男；1：女
	private int age;
	private String stuNative; //数据库中为native
	private String phone;
	private String roomInfo;
	private String detail;


	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStuNative() {
		return stuNative;
	}

	public void setStuNative(String stuNative) {
		this.stuNative = stuNative;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Student(){}

	public Student(String stuId, String name, int sex, int age, String stuNative, String phone, String roomInfo, String detail) {
		this.stuId = stuId;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.stuNative = stuNative;
		this.phone = phone;
		this.roomInfo = roomInfo;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Student{" +
				"stuId='" + stuId + '\'' +
				", name='" + name + '\'' +
				", sex=" + sex +
				", age=" + age +
				", stuNative='" + stuNative + '\'' +
				", phone='" + phone + '\'' +
				", roomInfo='" + roomInfo + '\'' +
				", detail='" + detail + '\'' +
				'}';
	}
}
