package com.maven.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/")
	public String handleError() {
		 return "onefile";
//		return "excelUpload";
	}
}
