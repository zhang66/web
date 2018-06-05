package com.blueocean.web.auth.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.data.Result;
import com.blueocean.web.auth.model.dto.UserDeatilsDto;
import com.blueocean.web.auth.model.dto.UserQueryDto;
import com.blueocean.web.auth.service.UserService;
import com.blueocean.web.base.BaseController;

/**
 * 用户信息管理
 * 
 * @todo
 * @author 张亚林
 * @date 2018年5月16日 上午10:08:21
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	@RequestMapping("/getUserInfo")
	public Result getUserInfo() {
		UserDeatilsDto us = userService.getUserInfoDetailsService(getUserId());
		return Result.ok(us);

	}

}
