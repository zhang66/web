package com.blueocean.common.consts;

/**
 * 通用常量
 */
public interface SysConstant {
	/**
	 * 是否 1、是  0、否
	 * @author suchengliang
	 *
	 */
	public static interface YesOrNo{
		int YES = 1;
		int NO = 0;
	}
	
	public static interface ParamType{
		int PAYSTATUS=1;
		int ISACTIVE=2;
		int OUTPUTINTERVALTIME=3;
		int OUTPUTMETHOD=8;
	}
	public static interface EnableStatusCode{
		int ENABLE=1;
		int DISABLE=2;
		int DELETE=3;
	}
}
