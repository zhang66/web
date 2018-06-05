package com.blueocean.web.auth.model.dto;



import lombok.Data;

/**
 * 用户详情实体
* @todo  
* @author 张亚林  
* @date 2018年5月16日 上午10:10:18
 */
@Data
public class UserDeatilsDto {
	
	private Integer id;//用户id
	
	private String username;//登录用户名
	
	private String realName;//真实姓名
	
	private String headPic;//头像
	
	private String telphone;
	
	private String isActive;
	
	private String password;

}
