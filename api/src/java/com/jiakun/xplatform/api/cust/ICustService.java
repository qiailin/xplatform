package com.jiakun.xplatform.api.cust;

import com.jiakun.xplatform.api.cust.bo.Customer;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ICustService {

	/**
	 * cms.cms_api_pack.fn_watersta.
	 * 
	 * @param userId
	 * @return
	 */
	Customer getFnWatersta(String userId);

}
