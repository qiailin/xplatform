package com.jiakun.xplatform.api.openapi.bo;

import java.io.Serializable;

/**
 * 
 * @author xujiakun
 * 
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 8966135033860212831L;

	private String code;

	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
