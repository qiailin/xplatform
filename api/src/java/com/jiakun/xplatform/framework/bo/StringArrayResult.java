package com.jiakun.xplatform.framework.bo;

import java.util.Arrays;

/**
 * 
 * @author xujiakun
 * 
 */
public class StringArrayResult extends BaseResult {

	private static final long serialVersionUID = -8687183805950110367L;

	private String[] result;

	/**
	 * @return the result
	 */
	public String[] getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String[] result) {
		if (result != null) {
			this.result = Arrays.copyOf(result, result.length);
		}
	}

}
