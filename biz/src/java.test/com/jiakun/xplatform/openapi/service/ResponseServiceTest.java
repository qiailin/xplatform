package com.jiakun.xplatform.openapi.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jiakun.xplatform.api.openapi.IResponseService;
import com.jiakun.xplatform.api.openapi.bo.ResponseStats;
import com.jiakun.xplatform.api.sms.IMessageService;
import com.jiakun.xplatform.api.sms.bo.Message;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.openapi.dao.IResponseDao;
import com.jiakun.xplatform.openapi.service.impl.ResponseServiceImpl;
import com.jiakun.xplatform.sms.dao.IMessageDao;
import com.jiakun.xplatform.sms.service.impl.MessageServiceImpl;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

/**
 * 
 * @author jiakunxu
 * 
 */
public class ResponseServiceTest {

	@Tested
	private IResponseService responseService = new ResponseServiceImpl();

	@Injectable
	private IResponseDao responseDao;

	@Test
	public void testGetResponseStatsCount() {
		final ResponseStats responseStats = new ResponseStats();

		new NonStrictExpectations() {
			{
				responseDao.getResponseStatsCount(responseStats);
				result = 1;
				times = 1;
			}
		};

		Assert.assertEquals(1, responseService.getResponseStatsCount(responseStats));

		new NonStrictExpectations() {
			{
				responseDao.getResponseStatsList(responseStats);
				result = null;
				times = 1;
			}
		};

		Assert.assertEquals(null, responseService.getResponseStatsList(responseStats));

		final List<ResponseStats> responseStatsList = new ArrayList<ResponseStats>();

		new NonStrictExpectations() {
			{
				responseDao.createResponseStats(responseStatsList);
				result = "2";
				times = 1;
			}
		};

		Assert.assertEquals("2", responseService.createResponseStats(responseStatsList).getCode());

	}
}
