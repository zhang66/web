package com.blueocean.z.learn.upload;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.blueocean.common.util.UUIDGen;
import com.blueocean.web.pojo.FileCrieria;

/**
 * 上传文件
 * 
 * @author zhangyalin
 *
 */
@Controller
@RequestMapping("fileUpload")
public class FileUploadController {

	// 访问路径为：http://127.0.0.1:8080/file

	@RequestMapping("/file")

	public String file() {

		return "/file";

	}

	/**
	 * 
	 * 文件上传具体实现方法;
	 * 
	 * @param file
	 * 
	 * @return
	 * 
	 */

	@RequestMapping(value = "/upload1", produces = { "application/json;charset=UTF-8" })

	@ResponseBody

	public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (!file.isEmpty()) {

			try {

				String filename = file.getOriginalFilename();// 获取文件完整路径

				System.out.print("filename：" + filename);

				String realPath = "D://image"; // request.getSession().getServletContext().getRealPath("/image");//
												// 服务器路径
				SaveFileFromInputStream(file.getInputStream(), realPath, filename);// 保存到服务器的路径

				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(file.getOriginalFilename())));

				out.write(file.getBytes());

				out.flush();
				// 解析上传文件
				list = readExcel(filename, filename);
				out.close();

			} catch (FileNotFoundException e) {

				e.printStackTrace();

				return "上传失败," + e.getMessage();

			} catch (IOException e) {

				e.printStackTrace();

				return "上传失败," + e.getMessage();

			}

			// return "上传成功";
			return JSON.toJSONString(list);

		} else {

			return "上传失败，因为文件是空的.";

		}

	}

	@RequestMapping(value = "/upload2", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// MultipartFile file = dto.getFile();
		if (!file.isEmpty()) {
			try {
				String filename = file.getOriginalFilename();// 获取文件完整路径
				System.out.print("filename：" + filename);
				// 解析上传文件
				InputStream fis = new FileInputStream(filename);
				Map<String, String> m = new HashMap<String, String>();
				m.put("姓名", "userName");
				m.put("学号", "userNo");
				m.put("班级", "calssName");
				m.put("入学时间", "date");
				m.put("手机号", "phone");
				m.put("缴费金额", "money");
				list = ReadExcelUtils.parseExcel(fis, filename, m);
				return JSON.toJSONString(list);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "";
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}

	}

	@RequestMapping(value = "/upload", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String handleFileUpload1(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		String url = "";// 返回文件地址
		if (!file.isEmpty()) {
			try {
				String filename = file.getOriginalFilename();// 获取文件完整路径
				System.out.print("filename：" + filename);
				//String realPath = "/usr/local/tomcat-mcm/webapps/mcm/img";
				String realPath = "D://image";
				//String realPath = request.getRealPath("/usr/local/mcm/file");//
				//String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
				// 服务器路径
				//System.out.print("realPath：" + path);
				// 文件重命名，防止覆盖
				String newName = UUIDGen.getNumID() + filename.substring(filename.lastIndexOf("."));

				// 保存到服务器的路径
				SaveFileFromInputStream(file.getInputStream(), realPath, newName);
				url = "http://192.168.1.66:10008/mcm/img/" + newName;
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			}
			return url;
		} else {

			return "上传失败，因为文件是空的.";
		}
	}

	/**
	 * 解析Excel数据
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> readExcel(String path, String fileName) throws Exception {
		InputStream fis = new FileInputStream(path);
		Map<String, String> m = new HashMap<String, String>();
		m.put("姓名", "userName");
		m.put("学号", "userNo");
		m.put("班级", "calssName");
		m.put("入学时间", "date");
		m.put("手机号", "phone");
		m.put("缴费金额", "money");
		List<Map<String, Object>> list = ReadExcelUtils.parseExcel(fis, fileName, m);
		return list;
	}

	/**
	 * 将MultipartFile转化为file并保存到服务器上的某地
	 */
	public void SaveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + savefile);
		System.out.println("------------" + path + "/" + savefile);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	/**
	 * 
	 * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 */

	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(HttpServletRequest request) {

		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

		MultipartFile file = null;

		BufferedOutputStream stream = null;

		for (int i = 0; i < files.size(); ++i) {

			file = files.get(i);

			if (!file.isEmpty()) {

				try {

					byte[] bytes = file.getBytes();

					stream =

							new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));

					stream.write(bytes);

					stream.close();

				} catch (Exception e) {

					stream = null;

					return "You failed to upload " + i + " =>" + e.getMessage();

				}

			} else {

				return "You failed to upload " + i + " becausethe file was empty.";

			}

		}

		return "upload successful";

	}
	public static void main (String[] args){
		
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
		// 服务器路径
		System.out.print("realPath：" + path);
		
	}

}