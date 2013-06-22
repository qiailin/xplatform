package com.jiakun.xplatform.api.cust;

import com.jiakun.xplatform.api.cust.bo.Customer;

/**
 * �͑���Ϣ�ӿ�<br>
 * ���bo������ą���
 * 
 * @author xujiakun
 * 
 */
public interface ICustService {

	/**
	 * cms.cms_api_pack.fn_watersta
	 * 
	 * @param userId
	 * @return
	 */
	public Customer getFnWatersta(String userId);

}
