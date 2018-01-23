package com.epoint.domain;

public class Song {

	private String id;
	private String name;
	private Integer type;
	private String lyrics;
	private String compose;
	private String singer;
	private Integer degree;
	private String langues;
	private Integer duration;
	private Integer hotlevel;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getCompose() {
		return compose;
	}

	public void setCompose(String compose) {
		this.compose = compose;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getLangues() {
		return langues;
	}

	public void setLangues(String langues) {
		this.langues = langues;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getHotlevel() {
		return hotlevel;
	}

	public void setHotlevel(Integer hotlevel) {
		this.hotlevel = hotlevel;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", type=" + type + ", lyrics=" + lyrics + ", compose=" + compose
				+ ", singer=" + singer + ", degree=" + degree + ", langues=" + langues + ", duration=" + duration
				+ ", hotlevel=" + hotlevel + "]";
	}
}
