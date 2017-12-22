package com.blueocean.z.learn.upload;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.servlet.MultipartRequest;
import java.util.Date;

@Controller
@RequestMapping("TestServlet")
public class TestServlet extends HttpServlet {
	@RequestMapping("upload")
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi = null;
		// 用来限制用户上传文件大小的
		int maxPostSize = 1 * 100 * 1024 * 1024;
		String saveDirectory = "D:\\image";// 服务器上存储路径
		String dirFlag = System.getProperty("file.separator"); // 自动匹配操作系统文件路径
		// 第一个参数为传过来的请求HttpServletRequest，
		// 第二个参数为上传文件要存储在服务器端的目录名称
		// 第三个参数是用来限制用户上传文件大小
		// 第四个参数可以设定用何种编码方式来上传文件名称，可以解决中文问题
		multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8");
		// 传回所有文件输入类型的名称
		Enumeration efs = multi.getFileNames();
		while (efs.hasMoreElements()) {
			File file = multi.getFile(efs.nextElement().toString());
			if (file.exists()) {
				String filePath = file.getAbsolutePath();
				String fname = file.getName();// 附件原名
				System.out.println("上传文件原名============= " + fname);
				System.out.println("文件上传地址 =================== " + filePath);
				// 加密处理
				byte[] bytes1 = UrlBase64.encode((saveDirectory + dirFlag).getBytes());
				// 获取系统当前时间
				java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("yyyyMMddHHmmssSS");
				String currentTimeMillis = date.format(new Date(System.currentTimeMillis()));

				String t1_fname = fname.substring(0, fname.lastIndexOf("."));
				String t2_fname = fname.substring(fname.lastIndexOf("."));
				// 重新命名文件
				fname = t1_fname + "_" + currentTimeMillis + t2_fname;
				byte[] bytes2 = UrlBase64.encode(fname.getBytes());
				String encodeNewFilePath = new String(bytes1);
				String encodeNewFileName = new String(bytes2);
				System.out.println(
						"encodeNewFilePath -- >" + encodeNewFilePath + " encodeNewFileName---> " + encodeNewFileName);

				File newfile = new File(saveDirectory + dirFlag + fname);
				file.renameTo(newfile);
				System.out.println("上传成功！ ");
			}
		}
	}
}
