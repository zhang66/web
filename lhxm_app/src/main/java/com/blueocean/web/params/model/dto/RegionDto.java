package com.blueocean.web.params.model.dto;

import com.blueocean.common.data.TreeNode;

public class RegionDto extends TreeNode {

	private String code;// 编码
	private String enName;// 拼音
	private String enShortName;// 拼音缩写

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEnShortName() {
		return enShortName;
	}

	public void setEnShortName(String enShortName) {
		this.enShortName = enShortName;
	}

}
