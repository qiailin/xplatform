package com.jiakun.xplatform.data.service.impl;

import java.util.List;

import com.jiakun.xplatform.api.data.IDataLogService;
import com.jiakun.xplatform.api.data.bo.DataLogTotal;
import com.jiakun.xplatform.api.dict.bo.CmsTbDict;
import com.jiakun.xplatform.data.dao.IDataLogDao;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;

/**
 * 
 * @author jiakunxu
 * 
 */
public class DataLogServiceImpl implements IDataLogService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(DataLogServiceImpl.class);

	private IDataLogDao dataLogDao;

	public int getDataLogTotalCount(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDataLogTotalCount(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return 0;
	}

	public List<DataLogTotal> getDataLogTotalList(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDataLogTotalList(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return null;
	}

	public int getDBTableCount(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDBTableCount(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return 0;
	}

	public List<CmsTbDict> getDBTableList(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDBTableList(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return null;
	}

	public IDataLogDao getDataLogDao() {
		return dataLogDao;
	}

	public void setDataLogDao(IDataLogDao dataLogDao) {
		this.dataLogDao = dataLogDao;
	}

}
