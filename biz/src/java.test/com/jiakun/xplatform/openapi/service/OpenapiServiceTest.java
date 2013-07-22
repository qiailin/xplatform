package com.jiakun.xplatform.openapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.junit.Assert;
import org.junit.Test;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.login.ICAService;
import com.jiakun.xplatform.api.login.bo.ValidateResult;
import com.jiakun.xplatform.api.openapi.IOpenapiService;
import com.jiakun.xplatform.openapi.service.impl.OpenapiServiceImpl;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * 
 * @author jiakunxu
 * 
 */
public class OpenapiServiceTest {

	@Tested
	private IOpenapiService openapiService = new OpenapiServiceImpl();

	@Injectable
	private IMemcachedCacheService memcachedCacheService;

	@Injectable
	private ICAService caService;

	@Test
	public void testRest() {

		new NonStrictExpectations() {
			{
				memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_OPEN_API);
				result = null;

				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_OPEN_API, null,
					IMemcachedCacheService.CACHE_KEY_OPEN_API_DEFAULT_EXP);
				result = null;
			}
		};

		Assert.assertNotNull(openapiService.rest(new MetadataMap<String, String>()));

		new NonStrictExpectations() {
			{
				caService.validateUser(anyString, anyString);
				ValidateResult res = new ValidateResult();
				AllUsers user = new AllUsers();
				res.setAllUser(user);
				result = res;
			}
		};

		MultivaluedMap<String, String> map = new MetadataMap<String, String>();
		List<String> method = new ArrayList<String>();

		map.put("method", method);
		Assert.assertNotNull(openapiService.rest(map));

		method.add(IOpenapiService.XPLATFORM_USER_LOGIN);
		map.put("method", method);
		Assert.assertNotNull(openapiService.rest(map));

		List<String> passports = new ArrayList<String>();
		passports.add("passport");
		map.put("passport", passports);
		Assert.assertNotNull(openapiService.rest(map));

		List<String> passwords = new ArrayList<String>();
		passwords.add("123456");
		map.put("password", passwords);
		Assert.assertNotNull(openapiService.rest(map));

		method = new ArrayList<String>();
		method.add("abc");
		map.put("method", method);
		Assert.assertNull(openapiService.rest(map));

	}
}
