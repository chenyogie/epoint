package com.epoint.domain;

public class Student {
	private String stuId;
	private String stuName;
	private Integer profession;
	private String addDate;
	private Integer sex;
	private String idNum;
	private Integer totalScore;
	private String note;
	
	public Student(){}
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Integer getProfession() {
		return profession;
	}
	public void setProfession(Integer profession) {
		this.profession = profession;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", profession=" + profession + ", addDate="
				+ addDate + ", sex=" + sex + ", idNum=" + idNum + ", totalScore=" + totalScore + ", note=" + note + "]";
	}
	public Student(String stuId, String stuName, Integer profession, String addDate, Integer sex, String idNum,
			Integer totalScore, String note) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.profession = profession;
		this.addDate = addDate;
		this.sex = sex;
		this.idNum = idNum;
		this.totalScore = totalScore;
		this.note = note;
	}
	
}
