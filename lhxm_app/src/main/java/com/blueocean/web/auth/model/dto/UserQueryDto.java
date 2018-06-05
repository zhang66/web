package com.blueocean.web.auth.model.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户入参
 * 
 * @todo
 * @author 张亚林
 * @date 2018年5月16日 上午10:09:31
 */
@Data
public class UserQueryDto implements Serializable {

	/**
	 * 用户信息
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String name;

	private String password;

}
