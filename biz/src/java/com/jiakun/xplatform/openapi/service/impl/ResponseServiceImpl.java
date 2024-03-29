package com.jiakun.xplatform.openapi.service.impl;

import java.util.List;

import com.jiakun.xplatform.api.openapi.IResponseService;
import com.jiakun.xplatform.api.openapi.bo.ResponseStats;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.openapi.dao.IResponseDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ResponseServiceImpl implements IResponseService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ResponseServiceImpl.class);

	private IResponseDao responseDao;

	public int getResponseStatsCount(ResponseStats responseStats) {
		try {
			return responseDao.getResponseStatsCount(responseStats);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(responseStats), e);
		}

		return 0;
	}

	public List<ResponseStats> getResponseStatsList(ResponseStats responseStats) {
		try {
			return responseDao.getResponseStatsList(responseStats);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(responseStats), e);
		}

		return null;
	}

	public BooleanResult createResponseStats(List<ResponseStats> responseStatsList) {
		BooleanResult res = new BooleanResult();

		try {
			String result = responseDao.createResponseStats(responseStatsList);
			res.setResult(true);
			res.setCode(result);
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setCode(IResponseService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(responseStatsList), e);
		}

		return res;
	}

	public IResponseDao getResponseDao() {
		return responseDao;
	}

	public void setResponseDao(IResponseDao responseDao) {
		this.responseDao = responseDao;
	}

}
