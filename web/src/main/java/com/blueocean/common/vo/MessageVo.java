package com.blueocean.common.vo;


/**
 * 
 * @author 王欣宇
 * @time 2017年12月12日 下午2:39:30
 * @todo 
 * @remark
 */

public class MessageVo {
	
	private String SpCode;
	
	private String LoginName;
	
	private String Password;
	
	private String MessageContent;
	
	private String UserNumber;
	
	private String SerialNumber;
	
	private String ScheduleTime;
	
	private String f;

	public MessageVo(String messageContent, String userNumber) {
		super();
		MessageContent = messageContent;
		UserNumber = userNumber;
	}

	
	

}
