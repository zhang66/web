package com.blueocean.web.auth.service;

import com.blueocean.common.data.Result;
import com.blueocean.web.auth.model.dto.UserDeatilsDto;
import com.blueocean.web.auth.model.dto.UserQueryDto;

public interface UserService {

	Result login(UserQueryDto user);

	UserDeatilsDto getUserInfoDetailsService(Integer id);

}
