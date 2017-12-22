package com.blueocean.common.util;

import com.blueocean.common.constant.RetInfoConstant;
import com.blueocean.common.vo.RetVo;
/**
 * 
 * @author 
 * @time 2017年10月16日 下午6:27:45
 * @todo 
 * @remark
 */
public class RetVoUtil {
	
	public static RetVo initRetInfoErr() {
		return initRetInfo(RetInfoConstant.RETCODE_UNKOWN_ERROR, RetInfoConstant.RETMSG_EXCEPTION_MSG);
	}

	public static RetVo initRetInfoSucc() {
		return initRetInfo(RetInfoConstant.RETCODE_SUCCESS, RetInfoConstant.RETMSG_SUCCESS_MSG);
	}

	
	
	
	/**
	 * 
	 * @param errorCode
	 * @param errorMsg
	 * @param result
	 * @return
	 */
	public static RetVo initRetInfo(String errorCode, String errorMsg, Object result) {
		RetVo RetVo = new RetVo();
		RetVo.setCode(errorCode);
		RetVo.setMsg(errorMsg);
		RetVo.setData(result);
		return RetVo;
	}

	public static RetVo initRetInfo(String errorCode, String errorMsg) {
		RetVo RetVo = new RetVo();
		RetVo.setCode(errorCode);
		RetVo.setMsg(errorMsg);
		return RetVo;
	}

}
