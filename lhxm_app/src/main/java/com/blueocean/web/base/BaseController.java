package com.blueocean.web.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.blueocean.common.util.JwtTokenUtil;
import com.blueocean.common.util.StringUtils;
import com.blueocean.common.vo.JwtUser;
import com.blueocean.web.auth.consts.RetConstant.Login;

/**
 * 用户基类
 * 
 * @todo
 * @author 张亚林
 * @date 2018年5月15日 下午2:34:07
 */
public class BaseController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public JwtUser getUser() {
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			throw new RuntimeException(Login.INVALID_LOGIN_CODE + "");
		}
		JwtUser user = jwtTokenUtil.getUserFromToken(token);
		return user;
	}

	public int getUserId() {
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			throw new RuntimeException(Login.INVALID_LOGIN_CODE + "");
		}
		int id = jwtTokenUtil.getUserIdFromToken(token);
		return id;
	}

}
