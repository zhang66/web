package com.maven.web.pojo;

import java.util.Date;
import java.util.List;

import com.blueocean.common.util.DateJsonSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(value = { "handler" })
public class Model {

	private String value;
	private String name;
	private List<Model> children;
	private Date time;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Model> getChildren() {
		return children;
	}

	public void setChildren(List<Model> children) {
		this.children = children;
	}

	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
}
