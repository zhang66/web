$(function() {
	$("#sa").click(function() {
		get();
	});// 触发button的click事件
	$("#upload").click(function() {
		pushImg();
	});// 触发button的click事件
});
function get() {
	var url = "http://192.168.1.66:10005/channel/listAllChannel";
	$.ajax({
		type : "post",
		url : url,
		data : {
			"page" : 2,
			"pageSize" : 10,
			"channelType" : "",
			"name" : ""
		}, // 此处data可以为 a=1&b=2类型的字符串 或 json数据。
		crossDomain : true,
		dataType : "json",
		success : function(data, textStatus, jqXHR) {
			$("#sd").val(data.value);
			/*
			 * if ("true" == data.flag) { alert("合法！"); return true; } else {
			 * alert("不合法！错误信息如下：" + data.errorMsg); return false; }
			 */
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求失败！");
		}
	});
};
function pushImg() {
	debugger;
	var url = "/uploadAction/uploadPic"; // 访问控制器是upload，后面必须加'/'否则会报错"org.apache.catalina.connector.RequestFacade
	// cannot be cast to
	// org.springframework.web.multipart.Mult...",但是如果是多级的URL【例如XX/XXX/00/upload/0】又没问题了.
	var param = $("#errorParameter").val();

	var files = $("#imageFile").get(0).files[0]; // 获取file控件中的内容

	var fd = new FormData();
	fd.append("userID", "1");
	fd.append("errDeviceType", "001");
	fd.append("errDeviceID", "11761b4a-57bf-11e5-aee9-005056ad65af");
	fd.append("errType", "001");
	fd.append("errContent", "XXXXXX");
	fd.append("errPic", files);
	$.ajax({
		type : "POST",
		contentType : false, // 必须false才会避开jQuery对 formdata 的默认处理 ,
		// XMLHttpRequest会对 formdata 进行正确的处理
		processData : false, // 必须false才会自动加上正确的Content-Type
		url : url,
		data : fd,
		success : function(msg) {

			alert(jsonString);
		},
		error : function(msg) {
			alert("error");
		}
	});
}

function previewImage(file, prvid) {
	/*
	 * file：file控件 prvid: 图片预览容器
	 */
	var tip = "Expect jpg or png or gif!"; // 设定提示信息
	var filters = {
		"jpeg" : "/9j/4",
		"gif" : "R0lGOD",
		"png" : "iVBORw"
	}
	var prvbox = document.getElementById(prvid);
	prvbox.innerHTML = "";
	if (window.FileReader) { // html5方案
		for (var i = 0, f; f = file.files[i]; i++) {
			var fr = new FileReader();
			fr.onload = function(e) {
				var src = e.target.result;
				if (!validateImg(src)) {
					alert(tip)
				} else {
					showPrvImg(src);
				}
			}
			fr.readAsDataURL(f);
		}
	} else { // 降级处理

		if (!/\.jpg$|\.png$|\.gif$/i.test(file.value)) {
			alert(tip);
		} else {
			showPrvImg(file.value);
		}
	}

	function validateImg(data) {
		var pos = data.indexOf(",") + 1;
		for ( var e in filters) {
			if (data.indexOf(filters[e]) === pos) {
				return e;
			}
		}
		return null;
	}

	function showPrvImg(src) {
		var img = document.createElement("img");
		img.src = src;
		prvbox.appendChild(img);
	}
}