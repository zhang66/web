/**
 * 
 */
package com.blueocean.z.learn.upload;

import java.io.InputStream;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangyalin
 *
 */
@Controller
@RequestMapping("download")
public class DownloadFile {
	@Value("classpath:doc/template.xlsx")
	private Resource resource;
	
	
	/**
	 * 终端导入模板下载
	 */
	@RequestMapping(value = "template/download")
	public ResponseEntity<byte[]> downloadModel() {
		try {
			InputStream in = resource.getInputStream();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", URLEncoder.encode("终端导入模板.xlsx", "UTF-8"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			throw new RuntimeException("模板文件下载失败" + e + resource);
		}
	}
}
