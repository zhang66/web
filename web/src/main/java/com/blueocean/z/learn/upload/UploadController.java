package com.blueocean.z.learn.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.blueocean.common.util.ImageUtil;
import com.blueocean.common.util.RetVoUtil;
import com.blueocean.common.vo.RetVo;
import com.blueocean.web.base.FileUploadPath;

@RestController
@RequestMapping("upload")
public class UploadController {

	@Value("${web.uploadPath}")
	private String uploadPath;

	@Value("${web.imgUrl}")
	private String serviceAddress;

	@Autowired
	private FileUploadPath fileUploadPath;

	/**
	 * 
	 * 文件上传具体实现方法;
	 * 
	 * @param file
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "fileUpload")
	public RetVo fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type)
			throws Exception {
		RetVo ret = RetVoUtil.initRetInfoSucc();
		String url = "";// 返回文件地址
		if (!file.isEmpty()) {
			try {
				// uploadPath = "D://file/";
				// 重命名上传图片
				String originFileName = System.currentTimeMillis() + file.getOriginalFilename();
				// 上传路径
				String fileName = uploadPath + type + "/" + originFileName;
				// 上传图片
				File tarFile = new File(fileName);
				FileUtils.copyInputStreamToFile(file.getInputStream(), tarFile);
				url = serviceAddress + type + "/" + originFileName;
			} catch (FileNotFoundException e) {
				return null;
			} catch (IOException e) {
				return null;
			}
			ret.setData(url);
		}
		return ret;
	}

	/**
	 * 
	 * 头像上传;
	 * 
	 * @param file
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "imgUpload")
	public RetVo imgUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type)
			throws Exception {
		RetVo ret = RetVoUtil.initRetInfoSucc();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			InputStream inputStream = file.getInputStream();
			if (!ImageUtil.isImage(inputStream)) {
				ret.setCode("999");
				ret.setMsg("非法格式，请重新上传");
				return ret;
			}
			// 重命名上传图片
			String originFileName = type + System.currentTimeMillis() + file.getOriginalFilename();
			// 上传路径
			String fileName = fileUploadPath.getHeadImgPath() + originFileName;
			// 上传图片
			File tarFile = new File(fileName);
			FileUtils.copyInputStreamToFile(file.getInputStream(), tarFile);
			ImageUtil.resize(tarFile, new File(fileUploadPath.getHeadImgSmallPath() + originFileName), 22, 22);

			ret.setData(serviceAddress + originFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * 数据导入具体实现方法;
	 * 
	 * @param file
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "fileImport")
	public RetVo fileImport(@RequestParam("file") MultipartFile file, @RequestParam("type") String type)
			throws Exception {
		RetVo ret = RetVoUtil.initRetInfoSucc();
		if (!file.isEmpty()) {
			try {
				String tName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				if (!".xls".equals(tName) && !".xlsx".equals(tName)) {
					ret.setCode("999");
					ret.setMsg("非法格式，请重新上传");
					return ret;
				}
				List<Map<String, Object>> list = impotData(file, file.getOriginalFilename());
				if (list == null) {
					ret.setCode("9999");
					ret.setMsg("文件解析异常");
				}
				ret.setData(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public List<Map<String, Object>> impotData(MultipartFile file, String filename) {
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		map.put("终端名称", "hospitalName");
		map.put("终端等级", "levelName");
		map.put("所在省", "province");
		map.put("所在市", "city");
		map.put("所在区", "district");
		map.put("具体位置", "address");
		map.put("法人代表名称", "artificialPerson");
		map.put("医生数", "doctorNumber");
		map.put("年用药数", "annualMedicationNumber");
		map.put("终端电话", "tel");
		map.put("当前院长情况", "currentDeanStatus");
		map.put("服务人口", "serviceNumber");
		map.put("病床数量", "bedNumber");
		map.put("日门诊数", "dayOutpatientNumber");
		map.put("下属村卫生室数", "branchClinics");
		map.put("特色擅长情况", "feature");
		map.put("备注", "desp");
		try {
			list = UploadController.readExcel(file, filename, map);
		} catch (Exception ex) {
		}
		if (list == null || list.size() <= 0)
			return null;
		// 去重
		List<Map<String, Object>> newLst = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> m : list) {
			Map<String, Object> nMap = new HashMap<String, Object>();
			nMap.put("hospitalName", m.get("hospitalName"));
			nMap.put("levelName", m.get("levelName"));
			nMap.put("province", m.get("province"));
			nMap.put("city", m.get("city"));
			nMap.put("district", m.get("district"));
			newLst.add(nMap);
		}
		for (int i = 0; i < newLst.size(); i++) {
			for (int j = i + 1; j < newLst.size(); j++) {
				if (newLst.get(i).equals(newLst.get(j))) {
					return null;
					// String.format("第%s行和第%s行数据重复", i + 1, j + 1);
				}
			}
		}

		return list;
	}

	/**
	 * 解析Excel数据
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> readExcel(MultipartFile file, String fileName, Map<String, String> map)
			throws Exception {
		InputStream fis = file.getInputStream();
		List<Map<String, Object>> list = ReadExcelUtils.parseExcel(fis, fileName, map);
		return list;
	}

}
