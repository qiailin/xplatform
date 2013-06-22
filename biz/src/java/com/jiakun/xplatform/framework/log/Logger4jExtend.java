package com.jiakun.xplatform.framework.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.monitor.bo.LogMonitor;
import com.jiakun.xplatform.cache.service.impl.MemcachedCacheServiceImpl;
import com.jiakun.xplatform.framework.util.DateUtil;
import com.jiakun.xplatform.framework.util.PropertiesUtil;

/**
 * Logger4jExtend
 * 
 * @author xujiakun
 * 
 */
public class Logger4jExtend {

	private static MemcachedCacheServiceImpl memcachedCacheService;

	private Logger logger;

	public Logger4jExtend() {
		super();
	}

	public Logger4jExtend(String clazzName) {
		super();
		logger = LogManager.getLogger(clazzName);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void info(Object message, Throwable e) {
		logger.info(message, e);
	}

	public void warn(Object message) {
		logger.warn(message);
	}

	public void warn(Object message, Throwable e) {
		logger.warn(message, e);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void debug(Object message, Throwable e) {
		logger.debug(message, e);
	}

	public void error(Object message) {
		logger.error(message);

		try {
			LoggingEvent event = new LoggingEvent(
					Logger4jExtend.class.getName(), logger, Level.ERROR,
					message, null);

			recordLogMonitor(message, event.getLocationInformation());

		} catch (Exception e) {
			logger.error("��־��¼ʧ��", e);
		}
	}

	public void error(Object message, Throwable e) {
		logger.error(message, e);

		try {
			LoggingEvent event = new LoggingEvent(
					Logger4jExtend.class.getName(), logger, Level.ERROR,
					message, e);

			recordLogMonitor(message, event.getLocationInformation(),
					event.getThrowableStrRep());

		} catch (Exception ee) {
			logger.error("��־��¼ʧ��", ee);
		}
	}

	private void recordLogMonitor(Object message, LocationInfo locationInfo) {
		recordLogMonitor(message, locationInfo, null);
	}

	private void recordLogMonitor(Object message, LocationInfo locationInfo,
			String[] throwableStrRep) {
		LogMonitor logMonitor = new LogMonitor();
		logMonitor.setClassName(locationInfo.getClassName());
		logMonitor.setMethodName(locationInfo.getMethodName());
		logMonitor.setLineNumber(locationInfo.getLineNumber());

		// ������ֶ���Ҫ��ȡ
		if (message.toString().length() >= 3500) {
			logMonitor.setMessage(message.toString().substring(0, 3500));
		} else {
			logMonitor.setMessage(message.toString());
		}

		StringBuffer sb = new StringBuffer();

		if (throwableStrRep != null) {
			for (String t : throwableStrRep) {
				sb.append(t);
			}
		}

		// ������ֶ���Ҫ��ȡ
		if (sb.length() >= 3500) {
			logMonitor.setE(sb.substring(0, 3500));
		} else {
			logMonitor.setE(sb.toString());
		}

		logMonitor.setLogDate(DateUtil.getNowDatetimeStr());

		try {
			@SuppressWarnings("unchecked")
			List<LogMonitor> list = (List<LogMonitor>) memcachedCacheService
					.get(IMemcachedCacheService.CACHE_KEY_LOG_MONITOR);

			// ��ʼ��cache
			if (list == null || list.size() == 0) {
				list = new ArrayList<LogMonitor>();
			}

			list.add(logMonitor);

			memcachedCacheService.set(
					IMemcachedCacheService.CACHE_KEY_LOG_MONITOR, list,
					IMemcachedCacheService.CACHE_KEY_LOG_MONITOR_DEFAULT_EXP);

		} catch (Exception e) {
			logger.error(e);
		}
	}

	static {
		try {
			Properties proobj = PropertiesUtil
					.loadProperties("../env.properties");

			MemcachedClientBuilder builder = new XMemcachedClientBuilder(
					AddrUtil.getAddresses((String) proobj
							.get("xmemcached.memcached.servers")));

			builder.setConnectionPoolSize(2);
			builder.setCommandFactory(new BinaryCommandFactory());
			builder.setSessionLocator(new KetamaMemcachedSessionLocator());
			builder.setTranscoder(new SerializingTranscoder());
			// builder.setBufferAllocator(new SimpleBufferAllocator());

			MemcachedClient client = builder.build();

			memcachedCacheService = new MemcachedCacheServiceImpl();
			memcachedCacheService.setMemcachedClient(client);

		} catch (Exception e) {

		}
	}

}
