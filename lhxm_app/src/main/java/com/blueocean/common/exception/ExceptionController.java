package com.blueocean.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blueocean.common.data.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * @author scl
 * @date 2018年4月11日 下午3:30:25
 * @version 1.0
 * @description 统一异常处理类
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {
	@ExceptionHandler({ Exception.class })
	public Object catchException(Exception e) {
		log.error("查询出现异常:{}", e);
		e.printStackTrace();
		return Result.error(e.getMessage());
	}
}
