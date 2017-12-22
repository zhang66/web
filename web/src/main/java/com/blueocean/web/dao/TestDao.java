package com.blueocean.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.blueocean.web.pojo.Model;

@Mapper
public interface TestDao {

	List<Model> selectDeptInfo(Integer hosId);

	List<Map<String, Object>> selectInfo();

	@Select("select id,name ,time from test ")
	List<Map<String, Object>> getInfo(int id);

}
