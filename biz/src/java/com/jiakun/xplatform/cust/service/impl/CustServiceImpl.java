package com.jiakun.xplatform.cust.service.impl;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.cust.ICustService;
import com.jiakun.xplatform.api.cust.bo.Customer;
import com.jiakun.xplatform.cust.dao.ICustDao;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;

public class CustServiceImpl implements ICustService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(CustServiceImpl.class);

	private ICustDao custDao;

	public Customer getFnWatersta(String userId) {
		try {
			String custId = custDao.getFnWatersta(userId);

			if (StringUtil.isNotEmpty(custId)) {
				if (custId.split("\\,").length <= 1) {
					return custDao.getCustomerById(Long.parseLong(custId));
				} else {
					return custDao.getCustomerById(Long.parseLong(custId
							.split("\\,")[0]));
				}
			}
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public ICustDao getCustDao() {
		return custDao;
	}

	public void setCustDao(ICustDao custDao) {
		this.custDao = custDao;
	}

}
