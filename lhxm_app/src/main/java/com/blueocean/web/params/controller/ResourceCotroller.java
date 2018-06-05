package com.blueocean.web.params.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.data.Result;
import com.blueocean.web.params.service.ResourceService;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @todo 地区信息字典
 * @author 张亚林
 * @date 2018年5月11日 下午2:09:48
 */
@RequestMapping(value = "resource")
@RestController
@Log4j
public class ResourceCotroller {

	@Resource
	private ResourceService resourceService;

	/**
	 * @author zhangyalin 城市列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "region")
	public Result listRegion() throws Exception {
		Object data = resourceService.selectRegionInfo();
		return Result.ok(data);
	}

}
