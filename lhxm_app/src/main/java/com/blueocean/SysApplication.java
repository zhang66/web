package com.blueocean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
@SpringBootApplication
@ImportResource(locations={"classpath:config/spring-tx.xml"})
@MapperScan(basePackages={"com.blueocean.web.**.dao"})
public class SysApplication{

	/** 本地测试使用的程序入口 */
	public static void main(String[] args) {
		SpringApplication.run(SysApplication.class, args);
	}

}
