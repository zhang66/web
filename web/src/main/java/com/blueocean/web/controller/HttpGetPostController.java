package com.blueocean.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.web.pojo.Model;

@RestController
@RequestMapping("getAction")
public class HttpGetPostController {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * get请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public Object getInfo(String uid) {
		Model m = new Model();
		m.setValue(uid);
		return m;
	}

	/**
	 * post请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "postInfo", method = RequestMethod.POST)
	public Model postInfo(@RequestBody Model model) throws IOException, ParseException {
		Model m = new Model();
		m.setTime(format.parse("2017-09-19 16:15:10"));
		return m;
	}

	/**
	 * post请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "param", method = RequestMethod.POST)
	public Model param(@RequestParam("type") String type, @RequestParam("value") String value)
			throws IOException, ParseException {
		Model m = new Model();
		m.setTime(format.parse("2017-09-19 16:15:10"));
		m.setName(type);
		m.setValue(value);
		return m;
	}

}
