package com.jiakun.xplatform.cust.dao;

import com.jiakun.xplatform.api.cust.bo.Customer;

public interface ICustDao {

	/**
	 * cms.cms_api_pack.fn_watersta
	 * 
	 * @param userId
	 * @return
	 */
	public String getFnWatersta(String userId);

	/**
	 * ���custId���Customer��Ϣ
	 * 
	 * @param custId
	 * @return
	 */
	public Customer getCustomerById(Long custId);

}
