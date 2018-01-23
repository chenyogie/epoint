package com.epoint.domain;

import java.util.Date;

public class Music {
	private String selectnumber;
	private String musicid;
	private String songname;
	private String singer;
	private Date comeindate;
	private Integer totalcost;
	private Integer order;
	private Integer isswitch;
	private String remark;

	public String getSelectnumber() {
		return selectnumber;
	}

	public void setSelectnumber(String selectnumber) {
		this.selectnumber = selectnumber;
	}

	public String getMusicid() {
		return musicid;
	}

	public void setMusicid(String musicid) {
		this.musicid = musicid;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public Date getComeindate() {
		return comeindate;
	}

	public void setComeindate(Date comeindate) {
		this.comeindate = comeindate;
	}

	public Integer getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(Integer totalcost) {
		this.totalcost = totalcost;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getIsswitch() {
		return isswitch;
	}

	public void setIsswitch(Integer isswitch) {
		this.isswitch = isswitch;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	@Override
	public String toString() {
		return "Music [selectnumber=" + selectnumber + ", musicid=" + musicid + ", singer=" + singer + ", comeindate="
				+ comeindate + ", totalcost=" + totalcost + ", order=" + order + ", isswitch=" + isswitch + ", remark="
				+ remark + "]";
	}

}
