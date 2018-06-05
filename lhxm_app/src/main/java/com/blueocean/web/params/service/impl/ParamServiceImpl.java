package com.blueocean.web.params.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.blueocean.web.params.dao.ParamDao;
import com.blueocean.web.params.model.dto.Param;
import com.blueocean.web.params.model.dto.ParamDto;
import com.blueocean.web.params.service.ParamService;

@Service
public class ParamServiceImpl implements ParamService {

	@Resource
	private ParamDao paramDao;

	// 这里的单引号不能少，否则会报错，被识别是一个对象;
	public static final String CACHE_KEY = "'resource_param_Info'";

	/**
	 * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
	 */
	public static final String DEMO_CACHE_NAME = "ehcache_resource";

	@Override
	@Cacheable(value = DEMO_CACHE_NAME, key = CACHE_KEY)
	public List<Param> getParams(ParamDto dto) {
		return paramDao.getParamsByCriteria(dto);
	}

	@Override
	public List<Param> getParams() {
		return getParams(null);
	}
	
	@Override
	public String getParamName(String paramVal, Integer paramType) {
		return paramDao.getParamName(paramVal,paramType);
	}
}
