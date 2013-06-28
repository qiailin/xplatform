package com.jiakun.xplatform.openapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.login.ICAService;
import com.jiakun.xplatform.api.login.bo.ValidateResult;
import com.jiakun.xplatform.api.openapi.IOpenapiService;
import com.jiakun.xplatform.api.openapi.bo.Response;
import com.jiakun.xplatform.api.openapi.bo.ResponseStats;
import com.jiakun.xplatform.api.openapi.bo.UserResponse;
import com.jiakun.xplatform.framework.util.JsonUtil;

/**
 * 
 * @author jiakunxu
 * 
 */
public class OpenapiServiceImpl implements IOpenapiService {

	private IMemcachedCacheService memcachedCacheService;

	private ICAService caService;

	public String rest(MultivaluedMap<String, String> params) {
		Long startTime = System.currentTimeMillis();

		// û�в���
		if (params == null || params.isEmpty()) {
			Response response = new Response();
			response.setCode(IOpenapiService.ERROR);
			response.setMsg(IOpenapiService.ERROR_MSG_PARAMS);

			return response(memcachedCacheService, IOpenapiService.ERROR_MSG_PARAMS, startTime, response);
		}

		String method = null;
		List<String> methods = params.get("method");

		if (methods != null && methods.size() > 0) {
			method = methods.get(0);
		}

		// û�з���
		if (StringUtil.isEmpty(method)) {
			Response response = new Response();
			response.setCode(IOpenapiService.ERROR);
			response.setMsg(IOpenapiService.ERROR_MSG_METHOD);

			return response(memcachedCacheService, IOpenapiService.ERROR_MSG_METHOD, startTime, response);
		}

		// ��� method�жϽӿ�
		if (IOpenapiService.XPLATFORM_USER_LOGIN.equals(method)) {
			String passport = null;
			String password = null;

			List<String> passports = params.get("passport");
			List<String> passwords = params.get("password");

			if (passports != null && passports.size() > 0) {
				passport = passports.get(0);
			}

			if (passwords != null && passwords.size() > 0) {
				password = passwords.get(0);
			}

			ValidateResult result = caService.validateUser(passport, password);
			UserResponse response = new UserResponse();
			response.setCode(result.getResultCode());
			response.setMsg(result.getMessage());
			AllUsers user = result.getAllUser();
			if (user != null) {
				user.setUserState(null);
				user.setHqSign(null);
				user.setOrgId(null);
				user.setPassWd(null);
				user.setCustType(null);
			}
			response.setAllUser(user);

			return response(memcachedCacheService, method, startTime, response);

		}

		return null;
	}

	/**
	 * ͳһ����������json����¼�ӿ�����
	 * 
	 * @param methodName
	 * @param startTime
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String response(IMemcachedCacheService memcachedCacheService, String methodName, Long startTime, Object obj) {
		try {
			ResponseStats responseStats = new ResponseStats();
			responseStats.setMethodName(methodName);
			responseStats.setStartTime(startTime);
			responseStats.setEndTime(System.currentTimeMillis());

			List<ResponseStats> list =
				(List<ResponseStats>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_OPEN_API);

			// ��ʼ��cache
			if (list == null || list.size() == 0) {
				list = new ArrayList<ResponseStats>();
			}

			list.add(responseStats);

			memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_OPEN_API, list,
				IMemcachedCacheService.CACHE_KEY_OPEN_API_DEFAULT_EXP);

		} catch (Exception e) {
		}

		return JsonUtil.bean2Json(obj.getClass(), obj);
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

}
