package com.blueocean.web.params.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.data.Result;
import com.blueocean.web.params.model.dto.Param;
import com.blueocean.web.params.model.dto.ParamDto;
import com.blueocean.web.params.model.global.ParamConst.YesOrNo;
import com.blueocean.web.params.service.ParamService;

/**
 * 
 * @todo 公用字典
 * @author 张亚林
 * @date 2018年5月11日 下午2:09:40
 */
@RequestMapping(value = "param")
@RestController
public class ParamController {

	@Resource
	private ParamService paramService;

	/**
	 * 获取指定类型的参数列表
	 * 
	 * @param paramType
	 * @return
	 */
	@RequestMapping(value = "params")
	public Result getParams() {
		ParamDto dto = new ParamDto(YesOrNo.YES);
		List<Param> list = paramService.getParams(dto);
		return Result.ok(list);
	}

}
