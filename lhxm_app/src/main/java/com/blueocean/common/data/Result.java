package com.blueocean.common.data;

import java.util.HashMap;

import com.blueocean.common.consts.ResultInfo;

/** 统一的返回封装对象 */
public class Result extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;
	private static final String KEY_CODE="code";
	private static final String KEY_MSG="msg";
	private static final String KEY_DATA="data";
	
	public static Result ok(){
		return new Result(ResultInfo.Code.SUCCESS).addMsg(ResultInfo.Msg.RET_SUCCESS_MSG);
	}
	public static Result ok(Object data){
		return new Result(ResultInfo.Code.SUCCESS).addData(data);
	}
	public static Result error(){
		return new Result(ResultInfo.Code.SYS_ERROR).addMsg(ResultInfo.Msg.RET_FAILED_MSG);
	}
	public static Result error(Object msg){
		return new Result(ResultInfo.Code.SYS_ERROR).addMsg(msg);
	}
	public static Result error(Integer code,Object msg){
		return new Result(code).addMsg(msg);
	}
	
	/*========================================================================================*/
	public Result addMsg(Object msg){
		put(KEY_MSG, msg);
		return this;
	}
	public Result setCode(Integer code){
		put(KEY_CODE, code);
		return this;
	}
	public Result addData(Object data){
		put(KEY_DATA, data);
		return this;
	}
	public Result addAttribute(String key,Object value){
		put(key, value);
		return this;
	}
	public Result setPage(Page page){
		addAttribute("pageInfo", new PageInfo(page.getCount())).addData(page.getData());
		return this;
	}
	class PageInfo{
		private long total;
		
		public PageInfo(long total) {
			this.total = total;
		}

		public long getTotal() {
			return total;
		}
		
	}
	
	/* ==================================== 私有构造方法 ======================================== */
	private Result(int code) {
		put(KEY_CODE, code);
		put(KEY_MSG, "");
		put(KEY_DATA, null);
	}
	private Result() {
	}
}
