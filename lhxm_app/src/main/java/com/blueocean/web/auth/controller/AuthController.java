package com.blueocean.web.auth.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.data.Result;
import com.blueocean.web.auth.model.dto.UserQueryDto;
import com.blueocean.web.auth.service.UserService;

/**
 * 用户登录管理
 * 
 * @todo
 * @author 张亚林
 * @date 2018年5月16日 上午10:08:21
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Resource
	private UserService userService;

	/**
	 * 用户登录接口
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public Result login(@RequestBody UserQueryDto user) {
		return userService.login(user);

	}

}
