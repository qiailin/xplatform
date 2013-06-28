package com.jiakun.xplatform.cust.dao.impl;

import com.jiakun.xplatform.api.cust.bo.Customer;
import com.jiakun.xplatform.cust.dao.ICustDao;
import com.jiakun.xplatform.framework.dao.impl.BaseDaoImpl;

/**
 * 
 * @author jiakunxu
 * 
 */
public class CustDaoImpl extends BaseDaoImpl implements ICustDao {

	public String getFnWatersta(String userId) {
		return (String) getSqlMapClientTemplate().queryForObject("cust.getFnWatersta", userId);
	}

	public Customer getCustomerById(Long custId) {
		return (Customer) getSqlMapClientTemplate().queryForObject("cust.getCustomerById", custId);
	}

}
