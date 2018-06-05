package com.blueocean.web.params.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.blueocean.common.util.TreeUtil;
import com.blueocean.web.params.dao.AreaDao;
import com.blueocean.web.params.model.dto.RegionDto;
import com.blueocean.web.params.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Resource
	private AreaDao areaDao;

	// 这里的单引号不能少，否则会报错，被识别是一个对象;
	public static final String CACHE_KEY = "'resource_region_Info'";

	/**
	 * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
	 */
	public static final String DEMO_CACHE_NAME = "ehcache_resource";

	@Cacheable(value = DEMO_CACHE_NAME, key = CACHE_KEY)
	@Override
	public Object selectRegionInfo() {
		List<RegionDto> list = areaDao.selectRegionInfo();
		Object object = TreeUtil.packageTree(list);

		return object;
	}

}
