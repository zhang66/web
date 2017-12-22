package com.blueocean.web.base;

import org.springframework.stereotype.Component;

import com.blueocean.web.base.support.OSinfo;

@Component
public class FileUploadPath {
	private final String coverRootPath;
	private final String mediaRootPath;
	private final String headImgPath;
	private final String webPath;
	{ // 对于Windows系统的路径
		if (OSinfo.isWindows()) {
			coverRootPath = "e:/fileupload/cover/";
			mediaRootPath = "e:/fileupload/media/";
			headImgPath = "e:/fileupload/head/";
			webPath = "E:/blue_ocean/cso/dist/";
		} else {// 对于其他系统的路径
			coverRootPath = "/data/fileupload/cover/";
			mediaRootPath = "/data/fileupload/media/";
			headImgPath = "/data/fileupload/head/";
			webPath = "/data/server_jar/cso/cso_platform/dist/";
		}
	}

	public String getCoverRootPath() {
		return coverRootPath;
	}

	public String getCoverMidPath() {
		return coverRootPath + "mid/";
	}

	public String getCoverSmallPath() {
		return  coverRootPath + "small/";
	}

	public String getMediaRootPath() {
		return mediaRootPath;
	}

	public String getHeadImgPath() {
		return headImgPath;
	}

	public String getHeadImgSmallPath() {
		return headImgPath + "small/";
	}
}
