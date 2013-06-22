package com.jiakun.xplatform.api.cache;

import java.net.InetSocketAddress;
import java.util.List;

import com.jiakun.xplatform.api.cache.bo.CacheStats;

/**
 * MemcachedCache�ӿ�
 * 
 * @author xujiakun
 * 
 */
public interface IMemcachedCacheService extends ICacheService<String, Object> {

	/**
	 * ȱʡcache����ʱ��
	 */
	public static final int DEFAULT_EXP = 24 * 60 * 60;

	/**
	 * oa������ҳ
	 */
	public static final String CACHE_KEY_NEWS = "key_news";

	public static final int CACHE_KEY_NEWS_DEFAULT_EXP = 0;

	/**
	 * �˵���
	 */
	public static final String CACHE_KEY_MENU_TREE = "key_menu_tree_";

	public static final int CACHE_KEY_MENU_TREE_DEFAULT_EXP = 12 * 60 * 60;

	/**
	 * open api
	 */
	public static final String CACHE_KEY_OPEN_API = "key_open_api";

	public static final int CACHE_KEY_OPEN_API_DEFAULT_EXP = 0;

	/**
	 * log monitor
	 */
	public static final String CACHE_KEY_LOG_MONITOR = "key_log_monitor";

	public static final int CACHE_KEY_LOG_MONITOR_DEFAULT_EXP = 0;

	/**
	 * bo parameter
	 */
	public static final String CACHE_KEY_BO_PARAMETER = "key_bo_parameter_";

	public static final int CACHE_KEY_BO_PARAMETER_DEFAULT_EXP = 0;

	/**
	 * sso token
	 */
	public static final int CACHE_KEY_SSO_TOKEN_DEFAULT_EXP = 60;

	/**
	 * session
	 */
	public static final int CACHE_KEY_SESSION_DEFAULT_EXP = 2 * 60 * 60;

	/**
	 * session
	 */
	public static final int CACHE_KEY_SESSION_EXP = 3 * 60;

	/**
	 * file id
	 */
	public static final String CACHE_KEY_FILE_ID = "key_file_id_";

	public static final int CACHE_KEY_FILE_ID_DEFAULT_EXP = 1 * 60 * 60;

	/**
	 * key���Ӧ����һ����������ʵ������inc������
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws Exception
	 */
	public long incr(String key, int inc) throws Exception;

	/**
	 * key���Ӧ����һ����������ʵ������inc������
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws Exception
	 */
	public long incr(String key, long inc) throws Exception;

	/**
	 * key���Ӧ����һ����������ʵ�ּ���decr������
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws Exception
	 */
	public long decr(String key, int decr) throws Exception;

	/**
	 * key���Ӧ����һ����������ʵ�ּ���decr������
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws Exception
	 */
	public long decr(String key, long decr) throws Exception;

	/**
	 * 
	 * @param address
	 * @throws Exception
	 */
	public void flushAll(InetSocketAddress address) throws Exception;

	/**
	 * ����cache״̬
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CacheStats> getStats() throws Exception;

}
