package com.blueocean.z.learn.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送邮件方法二，需配置信息（如下）
 * #设置邮箱主机
spring.mail.host=smtp.qq.com
#设置用户名
spring.mail.username=123456
#设置密码
spring.mail.password=fdsfsfs
#设置是否需要认证，如果为true,那么用户名和密码就必须的，
#如果设置false，可以不设置用户名和密码，当然也得看你的对接的平台是否支持无密码进行访问的。
spring.mail.properties.mail.smtp.auth=true
# STARTTLS[1] 是对纯文本通信协议的扩展。它提供一种方式将纯文本连接升级为加密连接（TLS或SSL），而不是另外使用一个端口作加密通信。
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
 * 
 * @author zhangyalin
 *
 */
@RestController
@RequestMapping("send")
public class MailTool {

	@Autowired

	private JavaMailSender mailSender;

	/**
	 * 
	 * 修改application.properties的用户，才能发送。
	 * 
	 */
	@RequestMapping("mail")
	public void sendSimpleEmail() {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1151407949@qq.com");// 发送者.
		message.setTo("zyl@blueocean-health.com");// 接收者.
		message.setSubject("张思密达，发送邮件测试");// 邮件主题.
		message.setText("这是邮件内容");// 邮件内容.
		mailSender.send(message);// 发送邮件

	}

}