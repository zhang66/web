package com.blueocean.web.auth.consts;

/**
 * 用户登录状态信息
* @todo  
* @author 张亚林  
* @date 2018年5月11日 下午6:33:35
 */
public class RetConstant {
	
	public interface Login {

		int PERMISSION_DENIED_CODE = 1001;
		String PERMISSION_DENIED_MSG = "权限不足!";

		int INVALID_LOGIN_CODE = 999;
		String INVALID_LOGIN_MSG = "登录失效!";
		
		int MISS_TOKEN_CODE = 1001;
		String MISS_TOKEN_MSG = "缺少token!";

		int USER_ALREADY_EXISTS_CODE = 1002;
		String USER_ALREADY_EXISTS_MSG = "用户名已存在!";

		int USER_NOT_EXISTS_CODE = 1003; 
		String USER_NOT_EXISTS_MSG = "该用户不存在!";

		int RETMSG_PARAMS_NOVALID_CODE = 1004;
		String RETMSG_PARAMS_NOVALID_MSG = "入参错误!";

		int PASSWORD_NOVALID_CODE = 1005;
		String PASSWORD_NOVALID_MSG = "账号或密码错误!";

	}

}
