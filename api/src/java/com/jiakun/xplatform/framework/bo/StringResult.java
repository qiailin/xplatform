package com.jiakun.xplatform.framework.bo;

import java.io.Serializable;

/**
 * 
 * @author xujiakun
 * 
 */
public class StringResult extends BaseResult implements Serializable {

	private static final long serialVersionUID = -8743217325411553037L;

	private String result;

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

}