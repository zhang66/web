package com.blueocean.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.web.pojo.Model;
import com.blueocean.web.service.TestService;

@RestController
@RequestMapping(value = "action")
public class MavenController {

	@Resource
	private TestService testService;

	@RequestMapping(value = "listDept")
	public Object listDept(@RequestBody Model model) {

		List<Map<String, Object>> list = testService.listInfo();
		return list;

	}

	@RequestMapping(value = "getInfo")
	public Object getInfo(@RequestBody Model model) {

		List<Map<String, Object>> list = testService.getInfo();
		return list;

	}

}
