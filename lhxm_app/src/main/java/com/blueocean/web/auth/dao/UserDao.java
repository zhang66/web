package com.blueocean.web.auth.dao;



import com.blueocean.web.auth.model.dto.UserDeatilsDto;
import com.blueocean.web.auth.model.po.UserPo;

public interface UserDao  {
	

	UserPo findByUserName(String username);

	UserDeatilsDto getUserInfo(Integer id);
}