package com.blueocean.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * 
 * @author 
 * @time 2017年8月21日 上午9:56:57
 * @todo 缓存配置
 * @remark
 */
@Configuration
@EnableCaching // 标注启动缓存
public class CacheConfiguration {

	private static Logger _log = LogManager.getLogger(CacheConfiguration.class);

	/**
	 * 注入缓存 管理器工具类
	 * 
	 * @param bean
	 * @return
	 */
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
		_log.info("CacheConfiguration.ehCacheCacheManager()");
		return new EhCacheCacheManager(bean.getObject());

	}
	
	/**
	 * 缓存管理工厂类
	 * @return
	 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		System.out.println("CacheConfiguration.ehCacheManagerFactoryBean()");
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}
}