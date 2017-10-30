package com.maven.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maven.web.pojo.Model;
import com.maven.web.service.TestService;

@RestController
@RequestMapping(value="mavenAction")
public class MavenController {
	
	@Resource
	private TestService  testService;
	
	@RequestMapping(value="listDept")
	public Object listDept(@RequestBody Model model){
		
		List<Model> list=testService.listDeptInfo(1);
		return list;
		
	}

}
