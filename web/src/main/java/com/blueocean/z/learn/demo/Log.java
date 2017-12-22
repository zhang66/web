package com.blueocean.z.learn.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Log {

	private static Logger log = LoggerFactory.getLogger(Log.class);

	/**
	 * 分为OFF(是最高等级的，用于关闭所有日志记录)、 FATAL(指出每个严重的错误事件将会导致应用程序的退出)、
	 * ERROR(指出虽然发生错误事件，但仍然不影响系统的继续运行)、 WARN(表明会出现潜在错误的情形)、 INFO()、 DEBUG、
	 * ALL或者您定义的级别。Log4j建议只使用四个级别， 优先级从高到低分别是 ERROR、WARN、INFO、DEBUG。通过在这里定义的级别，
	 * 您可以控制到应用程序中相应级别的日志信息的开关。比如在这里定义了INFO级别，
	 * 则应用程序中所有DEBUG级别的日志信息将不被打印出来，也是说大于等于的级别的日志才输出。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// log.debug(String.valueOf(new Date()) + "--" + "debug信息");
		log.error(String.valueOf(new Date()) + "--" + "error信息");
		log.info(String.valueOf(new Date()) + "--" + "info信息");

	}

}
