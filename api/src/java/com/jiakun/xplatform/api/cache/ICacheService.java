package com.jiakun.xplatform.api.cache;

import java.util.Date;

/**
 * cacheService.
 * 
 * @author jiakunxu
 * 
 * @param <K>
 * @param <V>
 */
public interface ICacheService<K, V> {

	/**
	 * 保存数据.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	V add(K key, V value) throws Exception;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws Exception
	 */
	V add(K key, V value, Date expiry) throws Exception;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 数据超时的秒数
	 * @return
	 * @throws Exception
	 */
	V add(K key, V value, int exp) throws Exception;

	/**
	 * 保存数据.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	V set(K key, V value) throws Exception;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws Exception
	 */
	V set(K key, V value, Date expiry) throws Exception;

	/**
	 * 保存有有效期的数据.
	 * 
	 * @param key
	 * @param value
	 * @param 数据超时的秒数
	 * @return
	 * @throws Exception
	 */
	V set(K key, V value, int exp) throws Exception;

	/**
	 * 保存数据,前提是key必须存在于memcache中，否则保存不成功.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	V replace(K key, V value) throws Exception;

	/**
	 * 保存有有效期的数据，前提是key必须存在于memcache中，否则保存不成功.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws Exception
	 */
	V replace(K key, V value, Date expiry) throws Exception;

	/**
	 * 保存有有效期的数据，前提是key必须存在于memcache中，否则保存不成功.
	 * 
	 * @param key
	 * @param value
	 * @param 有效期
	 * @return
	 * @throws Exception
	 */
	V replace(K key, V value, int exp) throws Exception;

	/**
	 * 获取缓存数据.
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	V get(K key) throws Exception;

	/**
	 * 移出缓存数据.
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	V remove(K key) throws Exception;

}
