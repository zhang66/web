package com.blueocean.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.blueocean.common.data.Result;
import com.blueocean.web.auth.consts.RetConstant.Login;

/**
 * @todo 无权访问时拦截
 * @author 张亚林
 * @date 2018年5月16日 上午10:06:46
 */
@Component
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Result ret = Result.error(Login.INVALID_LOGIN_CODE, Login.INVALID_LOGIN_MSG);
		response.getWriter().write(JSON.toJSONString(ret));
	}
}
