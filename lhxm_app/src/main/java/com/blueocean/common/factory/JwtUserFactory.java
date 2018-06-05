package com.blueocean.common.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.blueocean.common.vo.JwtUser;
import com.blueocean.web.auth.model.po.UserPo;

/**
 * jwt
 * 
 * @todo
 * @author 张亚林
 * @date 2018年5月16日 上午10:11:40
 */
public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(UserPo user) {
		return new JwtUser(user.getId(), user.getPassword(), user.getRealName(), user.getUsername(), user.getTelphone(),
				user.getHeadPic(), mapToGrantedAuthorities(new ArrayList<String>()));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		authorities.add("ROLE_USER");
		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
