package com.maven.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.maven.web.pojo.Model;

@Mapper
public interface TestDao {
	
	List<Model> selectDeptInfo(Integer hosId);

}
