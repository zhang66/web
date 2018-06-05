package com.blueocean.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.blueocean.common.data.Result;
import com.blueocean.common.util.JwtTokenUtil;
import com.blueocean.common.util.StringUtils;
import com.blueocean.common.vo.PropertiesVo;
import com.blueocean.web.auth.consts.RetConstant.Login;

/**
 * 
 * @author 张亚林
 * @date 2018年5月16日 上午10:06:46
 * @todo JWT拦截器
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PropertiesVo proVo;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Value("${token.expire}")
	private long expire;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader(this.tokenHeader);
		// 询问请求略过token验证
		if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
			filterChain.doFilter(request, response);
			return;
		}
		// url过滤
		if (isExclusion(request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;
		}

		// 验证token
		if (authHeader != null && authHeader != "") {
			final String authToken = authHeader;

			String username = jwtTokenUtil.getUsernameFromToken(authToken);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);

				}
			}
		} else {
			response.getWriter().write(JSON.toJSONString(Result.error(Login.MISS_TOKEN_CODE, Login.MISS_TOKEN_MSG)));
			return;

		}
		filterChain.doFilter(request, response);
	}

	/**
	 * 过滤url筛选
	 * 
	 * @param reqUrl
	 * @return
	 */
	public boolean isExclusion(String reqUrl) {
		reqUrl = reqUrl.substring(0, StringUtils.getNIndex(reqUrl, "/", 3));
		boolean is = false;
		List<String> urlList = proVo.getUrls();
		if (urlList == null || urlList.size() == 0) {
			return is;
		}
		String contPath = proVo.getContextPath();// 上下文路径
		for (String url : urlList) {
			if (reqUrl.equals(contPath + url)) {
				is = true;
				break;
			}
		}
		return is;
	}

}
