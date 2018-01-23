package com.epoint.domain;

import java.util.Date;

public class StuMarkInfo {
	private String markId;
	private String stuId;
	private String stuName;
	private String courseName;
	private Double baseScore;
	private Double testScore;
	private Double finalScore;
	private Date addDate;
	private String note;
	
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public StuMarkInfo() {
	}
	
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Double getBaseScore() {
		return baseScore;
	}
	public void setBaseScore(Double baseScore) {
		this.baseScore = baseScore;
	}
	public Double getTestScore() {
		return testScore;
	}
	public void setTestScore(Double testScore) {
		this.testScore = testScore;
	}
	public Double getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(Double finalScore) {
		this.finalScore = finalScore;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "StuMarkInfo [markId=" + markId + ", stuId=" + stuId + ", courseName=" + courseName + ", baseScore="
				+ baseScore + ", testScore=" + testScore + ", finalScore=" + finalScore + ", addDate=" + addDate
				+ ", note=" + note + "]";
	}
	
}
