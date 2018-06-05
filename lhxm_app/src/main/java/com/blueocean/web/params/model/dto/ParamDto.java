package com.blueocean.web.params.model.dto;

public class ParamDto {
	private String[] type;
	private String display;
	public ParamDto(String display) {
		//super();
		this.display = display;
	}
	
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
}
