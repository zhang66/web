package com.blueocean.web.params.service;

import java.util.List;

import com.blueocean.web.params.model.dto.RegionDto;

public interface ResourceService {

	/**
	 * 省市区县
	 * 
	 * @param parentId
	 * @return
	 */
	Object selectRegionInfo();

}
