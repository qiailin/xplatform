package com.jiakun.xplatform.framework.bo;

import java.io.Serializable;

/**
 * ���ؽ�����
 * 
 * @author xujiakun
 * 
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = -4398337558212309016L;

	private String code;

	/**
	 * ���ص����쳣�������Ϣ
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
