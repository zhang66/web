package com.blueocean.common.config;

import java.lang.reflect.Field;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.blueocean.web.base.FileUploadPath;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	private FileUploadPath fileUploadPath = new FileUploadPath();

	/**
	 * 替换默认的jackson解析框架，使用fastjson
	 */
	/*
	 * @Override public void
	 * configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	 * FastJsonHttpMessageConverter converter = new
	 * FastJsonHttpMessageConverter(); FastJsonConfig fastJsonConfig = new
	 * FastJsonConfig();
	 * fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	 * converter.setFastJsonConfig(fastJsonConfig ); converters.add(converter);
	 * super.configureMessageConverters(converters); }
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 修改静态资源映射路径。。。。。。12222
		ResourceHandlerRegistration resourceHandler = registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/");

		Class<? extends FileUploadPath> clazz = fileUploadPath.getClass();
		Field[] paths = clazz.getDeclaredFields();
		for (Field field : paths) {
			try {
				field.setAccessible(true);
				resourceHandler.addResourceLocations("file:" + field.get(fileUploadPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * attention:简单跨域就是GET，HEAD和POST请求，但是POST请求的"Content-Type"只能是application/x-www-form-urlencoded,
	 * multipart/form-data 或 text/plain
	 * 反之，就是非简单跨域，此跨域有一个预检机制，说直白点，就是会发两次请求，一次OPTIONS请求，一次真正的请求
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 允许cookies跨域
		config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
		config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
		config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
		config.addAllowedMethod("*");
		return config;
	}
}
