package com.blueocean.common.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * jwt user实体
 * 
 * @todo
 * @author 张亚林
 * @date 2018年5月16日 上午10:12:30
 */
public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final String username;
	private final String password;
	private final String realName;
	private final String headPic;
	private final String telphone;
	private final Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public JwtUser(int userId, String password, String userRealName, String userName, String telephone, String headPic,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = userId;
		this.realName = userRealName;
		this.username = userName;
		this.password = password;
		this.telphone = telephone;
		this.headPic = headPic;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public int getUserId() {
		return id;
	}

	public String getRealName() {
		return realName;
	}

	public String getTelphone() {
		return telphone;
	}

	public String getUserName() {
		return username;
	}

	public String getHeadPic() {
		return headPic;
	}

}
