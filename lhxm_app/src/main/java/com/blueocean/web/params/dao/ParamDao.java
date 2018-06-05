package com.blueocean.web.params.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blueocean.web.params.model.dto.Param;
import com.blueocean.web.params.model.dto.ParamDto;
import com.blueocean.web.params.model.dto.ParamList;


@Mapper
public interface ParamDao {
	public List<Param> getForList(String type);
	/**
	 * 通过模块类型获取参数列表
	 * @param types
	 * @return
	 */
	public List<Param> getParamsByCriteria(ParamDto dto);

	/**
	 * 查找所有可用标签
	 * @return
	 */
	public List<ParamList> findAll();
	
	/**
	 * 获取字典名称
	 * @param paramVal
	 * @param paramType
	 * @return
	 */
	public  String getParamName(@org.apache.ibatis.annotations.Param("paramVal") String paramVal, @org.apache.ibatis.annotations.Param("paramType") Integer paramType);
}
