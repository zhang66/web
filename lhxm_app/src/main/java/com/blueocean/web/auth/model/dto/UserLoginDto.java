package com.blueocean.web.auth.model.dto;

import java.util.List;

import lombok.Data;

/**
 * 登录返回实体
 * 
 * @todo
 * @author 张亚林
 * @date 2018年5月16日 上午10:09:57
 */
@Data
public class UserLoginDto {

	private Integer id;

	private String username;

	private String realName;

	private String headPic;

	private String telphone;

	private String token;

	private String avatar;

}
