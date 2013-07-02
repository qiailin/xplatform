package com.jiakun.xplatform.cust.dao;

import com.jiakun.xplatform.api.cust.bo.Customer;

/**
 * 
 * @author jiakunxu
 * 
 */
public interface ICustDao {

	/**
	 * cms.cms_api_pack.fn_watersta.
	 * 
	 * @param userId
	 * @return
	 */
	String getFnWatersta(String userId);

	/**
	 * getCustomerById.
	 * 
	 * @param custId
	 * @return
	 */
	Customer getCustomerById(Long custId);

}
