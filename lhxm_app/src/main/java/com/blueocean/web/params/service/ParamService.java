package com.blueocean.web.params.service;

import java.util.List;

import com.blueocean.web.params.model.dto.Param;
import com.blueocean.web.params.model.dto.ParamDto;

public interface ParamService {

	/**
	 * 根据参数类型获取参数字典列表
	 * 
	 * @param type
	 * @return
	 */
	public List<Param> getParams(ParamDto dto);

	/**
	 * 获取所有参数字典列表
	 * 
	 * @return
	 */
	public List<Param> getParams();
	
	/**
	 * 获取字典名称
	 * @param paramVal
	 * @param paramType
	 * @return
	 */
	public  String getParamName(@org.apache.ibatis.annotations.Param("paramVal") String paramVal, @org.apache.ibatis.annotations.Param("paramType") Integer paramType);
}
