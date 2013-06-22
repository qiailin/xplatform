package com.jiakun.xplatform.api.cust.bo;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * �ͻ�
 * 
 * @author xujiakun
 * 
 */
public class Customer extends SearchInfo {

	private static final long serialVersionUID = 5520483933592904856L;

	private Long custId;

	private String custName;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

}
