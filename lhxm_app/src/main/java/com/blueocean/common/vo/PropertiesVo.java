/**
 * 
 */
/**
 * @author zhangyalin
 *
 */
package com.blueocean.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * prefix="com.kfit.blog"： 在application.properties配置的属性前缀，
 * 在类中的属性就不用使用{@value}进行注入了。
 * 
 * @author zhangyalin
 * @version v.0.1
 * 
 */

// prefix设置key的前缀;
@ConfigurationProperties(prefix = "request.permit")
@Component
@Primary // 不加读不出属性值
public class PropertiesVo {

	private List<String> urls = new ArrayList<String>();
	private String contextPath;// 上下文路径

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String[] getPermitUrls() {
		String[] paths = new String[urls.size()];
		int i = 0;
		for (String url : this.urls) {
			paths[i] = url + "/**";
			i++;
		}
		return paths;
	}

}