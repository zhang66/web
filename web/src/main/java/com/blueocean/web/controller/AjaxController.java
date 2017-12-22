package com.blueocean.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gexin.fastjson.JSONObject;

@RestController
@RequestMapping(value = "testAction")
public class AjaxController {

	@RequestMapping("getInfo")
	public Object getInfo() {
		JSONObject object=new JSONObject();
		object.put("value", "hshhshhshs");
	return object;
	}
}
