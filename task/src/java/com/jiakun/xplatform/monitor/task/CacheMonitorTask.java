package com.jiakun.xplatform.monitor.task;

import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.monitor.ICacheMonitorService;
import com.jiakun.xplatform.framework.exception.ServiceException;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;

/**
 * 
 * @author xujiakun
 * 
 */
public class CacheMonitorTask {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CacheMonitorTask.class);

	private IMemcachedCacheService memcachedCacheService;

	private ICacheMonitorService cacheMonitorService;

	public void cacheMonitor() {
		try {
			cacheMonitorService.createCacheMonitor(memcachedCacheService.getStats());
		} catch (ServiceException e) {
			logger.error(e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ICacheMonitorService getCacheMonitorService() {
		return cacheMonitorService;
	}

	public void setCacheMonitorService(ICacheMonitorService cacheMonitorService) {
		this.cacheMonitorService = cacheMonitorService;
	}

}
