package com.blueocean;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 * @className com.Application.java
 * @time 2017年3月1日 下午6:03:20
 * @todo @SpringBootApplication申明让spring boot自动给程序进行必要的配置，等
 *       价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
 * @RestController返回json字符串的数据，直接可以编写RESTFul的接口； 启动应用就可以访问：http://127.0.0.1:8080/druid2/index.html输入账号和密码：admin2/123456
 *                                               就可 启动方式 spring-boot:run
 * @Configuration 和BEAN使用代替XML注解
 * @EnableAutoConfiguration 并对其及下属的包进行扫描
 * @ComponentScan 扫描类 @ServletComponentScan, 设定过滤器拦截器扫描
 */
@RestController
@EnableWebMvc
@EnableTransactionManagement
@SpringBootApplication // (scanBasePackages = { "com.blueocean" })
@EnableConfigurationProperties // 注入配置文件属性扫描
@ImportResource(locations = { "classpath:config/default-conf.xml" })
public class MavenApplication {

	public static void main(String[] args) {

		SpringApplication.run(MavenApplication.class, args);
	}

	/**
	 * 文件上传控制
	 * 
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {

		MultipartConfigFactory factory = new MultipartConfigFactory();

		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;

		factory.setMaxFileSize("20MB"); // KB,MB

		/// 设置总上传数据总大小

		factory.setMaxRequestSize("200MB");

		// Sets the directory location wherefiles will be stored.

		// factory.setLocation("路径地址");

		return factory.createMultipartConfig();

	}
}
