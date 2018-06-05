package com.blueocean.web.auth.model.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import lombok.Data;

@Data
public class UserPo implements Serializable {

	private Integer id;
	private String username;
	private String password;
	private String realName;
	private String headPic;
	private String telphone;
	private short isActive;
}