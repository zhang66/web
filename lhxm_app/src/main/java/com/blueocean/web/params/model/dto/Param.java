package com.blueocean.web.params.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Param {
	private String name;
	private String value;
	private List<ParamItem> items;

	public Param() {
	}

	public Param(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<ParamItem> getItems() {
		return items;
	}

	public void setItems(List<ParamItem> items) {
		this.items = items;
	}

}
