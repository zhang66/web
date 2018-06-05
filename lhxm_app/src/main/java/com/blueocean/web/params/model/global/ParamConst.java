package com.blueocean.web.params.model.global;

/**
 * 响应参数
 * 
 * @author zhangyalin
 *
 */
public class ParamConst {

	public static interface YesOrNo {
		public final static String YES = "1";
		public final static String NO = "2";

		public final static Integer YES_INT = 1;
		public final static Integer YES_NO = 2;
	}
	
	
	public static interface TabStatus {
		int PLATTYPE_NULL_CODE = 2001;
		String PLATTYPE_NULL_MSG = "参数platType不能为空！";
	}

}
