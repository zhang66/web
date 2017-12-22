package com.blueocean.web.service;

import java.util.List;
import java.util.Map;

import com.blueocean.web.pojo.Model;

public interface TestService {

	List<Model> listDeptInfo(Integer hosId);
	
	List<Map<String, Object>> listInfo();
	
	List<Map<String, Object>> getInfo();
}
