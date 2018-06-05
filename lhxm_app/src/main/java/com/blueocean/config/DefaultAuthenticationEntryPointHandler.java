package com.blueocean.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.blueocean.common.data.Result;
import com.blueocean.web.auth.consts.RetConstant.Login;

/**
 * 用户验证切点
* @todo  
* @author 张亚林  
* @date 2018年5月16日 上午10:06:08
 */
@Component
public class DefaultAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		Result ret = Result.error(Login.INVALID_LOGIN_CODE, Login.INVALID_LOGIN_MSG);
		response.getWriter().write(JSON.toJSONString(ret));
	}

}
