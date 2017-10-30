package com.maven.web.pojo;

import java.io.Serializable;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;



public class FileCrieria implements Serializable {

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Set<String> getArea() {
		return area;
	}

	public void setArea(Set<String> area) {
		this.area = area;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	private static final long serialVersionUID = 1L;

	private Integer channelId;
	// 区域
	private Set<String> area;

	private String token;

	private MultipartFile file;

}