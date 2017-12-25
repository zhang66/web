package com.blueocean.z.learn.mail;

import com.sun.net.ssl.internal.ssl.Provider;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
/**
 * 发送邮件方法一，无需配置信息
 * @author zhangyalin
 *
 */
public class MailSend {
	private static MimeMessage message;

	public static void main(String[] args) throws MessagingException {
		Security.addProvider(new Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// QQ邮箱服务器
		String smtpHost = "smtp.qq.com";
		// 邮箱用户名，即QQ账号
		final String username = "1151407949";
		// 邮箱授权码
		final String password = "xxxxx";
		// 要发送到的邮箱
		String to = "zyl@blueocean-health.com";
		// 自己的邮箱
		String from = "1151407949@qq.com";
		Transport transport;

		Properties props = new Properties();
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.username", username);
		props.put("mail.smtp.password", password);
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			// 身份认证
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		InternetAddress[] addresses = { new InternetAddress(to) };
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, addresses);
		message.setSubject("Hello");
		message.setSentDate(new Date());
		message.setText("hello, How are you");
		transport = session.getTransport("smtp");
		transport.connect(smtpHost, username, password);
		transport.send(message);
		System.out.println("email has been sent");
	}
}
