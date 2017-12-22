package com.blueocean.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.blueocean.web.dao.TestDao;
import com.blueocean.web.pojo.Model;
import com.blueocean.web.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;

	@Override
	public List<Model> listDeptInfo(Integer hosId) {
		List<Model> list = testDao.selectDeptInfo(hosId);
		return list;
	}

	/**
	 * 加缓存
	 */
	@Cacheable(value = "ehcache_resource", key = "'listDeptInfo'")
	@Override
	public List<Map<String, Object>> listInfo() {
		List<Map<String, Object>> list = testDao.selectInfo();
		System.out.println("查库---------------");
		return list;
	}

	@Override
	public List<Map<String, Object>> getInfo() {
		List<Map<String, Object>> list = testDao.getInfo(1);
		return list;
	}

}
