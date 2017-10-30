package com.maven.web.service;

import java.util.List;

import com.maven.web.pojo.Model;

public interface TestService {

	List<Model> listDeptInfo(Integer hosId);
}
