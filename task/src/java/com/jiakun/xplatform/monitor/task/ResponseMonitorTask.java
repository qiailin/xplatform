package com.jiakun.xplatform.monitor.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.openapi.IResponseService;
import com.jiakun.xplatform.api.openapi.bo.ResponseStats;

/**
 * 
 * @author xujiakun
 * 
 */
public class ResponseMonitorTask {

	private Logger logger = Logger.getLogger(ResponseMonitorTask.class);

	private IMemcachedCacheService memcachedCacheService;

	private IResponseService responseService;

	@SuppressWarnings("unchecked")
	public void responseMonitor() {
		List<ResponseStats> list = null;
		try {
			list = (List<ResponseStats>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_OPEN_API);
			memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_OPEN_API);
		} catch (Exception e) {
			logger.error(e);
		}

		if (list != null && list.size() != 0) {
			responseService.createResponseStats(list);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IResponseService getResponseService() {
		return responseService;
	}

	public void setResponseService(IResponseService responseService) {
		this.responseService = responseService;
	}

}
