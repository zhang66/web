package com.blueocean.web.params.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.blueocean.web.params.model.dto.RegionDto;


@Mapper
public interface AreaDao {



	/**
	 * 省市区县
	 * 
	 * @param parentId
	 * @return
	 */
	List<RegionDto> selectRegionInfo();

}
