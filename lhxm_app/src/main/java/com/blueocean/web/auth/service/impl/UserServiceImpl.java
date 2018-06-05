package com.blueocean.web.auth.service.impl;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.blueocean.common.data.Result;
import com.blueocean.common.util.JwtTokenUtil;
import com.blueocean.common.vo.JwtUser;
import com.blueocean.web.auth.dao.UserDao;
import com.blueocean.web.auth.model.dto.UserDeatilsDto;
import com.blueocean.web.auth.model.dto.UserLoginDto;
import com.blueocean.web.auth.model.dto.UserQueryDto;
import com.blueocean.web.auth.service.UserService;



@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private AuthenticationManager authenticationManager;
	
	@Resource
	private UserDetailsService userDetailsService;
	
	@Resource
	private JwtTokenUtil jwtTokenUtil;
	
	@Resource
	private UserDao uMapper;
	
//	@Autowired
//	private StringRedisTemplate template;
	
	@Value("${token.expire}")
	private long expire;

	@Override
	public Result login(UserQueryDto user) {
		String username = user.getUsername();
		String password = user.getPassword();

		Result ret = Result.error();
		try {
			UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
			final Authentication authentication = authenticationManager.authenticate(upToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			ret.addMsg("您输入的用户名或密码不正确!!");
			return ret;
		}
		final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(userDetails);
		UserLoginDto u = new UserLoginDto();
		u.setId(userDetails.getUserId());
		u.setTelphone(userDetails.getTelphone());
		u.setUsername(userDetails.getUsername());
		u.setRealName(userDetails.getRealName());
		u.setHeadPic(userDetails.getHeadPic());
		u.setToken(token);
		ret = Result.ok(u);
//		template.opsForValue().set(userDetails.getUsername(), token, expire, TimeUnit.SECONDS);
		return ret;
	}

	@Override
	public UserDeatilsDto getUserInfoDetailsService(Integer id) {
		UserDeatilsDto user = uMapper.getUserInfo(id);
		return user;
	}


}
