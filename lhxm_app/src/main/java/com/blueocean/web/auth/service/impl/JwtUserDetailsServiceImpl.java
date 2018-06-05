package com.blueocean.web.auth.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blueocean.common.factory.JwtUserFactory;
import com.blueocean.web.auth.dao.UserDao;
import com.blueocean.web.auth.model.po.UserPo;

/**
 * 用户详情实现类
* @todo  
* @author 张亚林  
* @date 2018年5月16日 上午10:09:04
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Resource
	private UserDao uMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPo user = uMapper.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return JwtUserFactory.create(user);
		}
	}

}
