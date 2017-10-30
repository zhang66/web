package com.maven.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maven.web.dao.TestDao;
import com.maven.web.pojo.Model;
import com.maven.web.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDao testDao;

	@Override
	public List<Model> listDeptInfo(Integer hosId) {
		List<Model> list=testDao.selectDeptInfo(hosId);
		return list;
	}

}
