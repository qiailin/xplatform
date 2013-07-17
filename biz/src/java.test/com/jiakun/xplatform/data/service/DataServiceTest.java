package com.jiakun.xplatform.data.service;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.jiakun.xplatform.api.data.IDataService;
import com.jiakun.xplatform.api.data.bo.TabColumn;
import com.jiakun.xplatform.data.dao.IDataDao;
import com.jiakun.xplatform.data.service.impl.DataServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

/**
 * 
 * @author jiakunxu
 * 
 */
public class DataServiceTest {

	@Tested
	private IDataService dataService = new DataServiceImpl();

	@Injectable
	private IDataDao dataDao;

	@Test
	public void testGetTabColumnsByLogId() {
		new Expectations() {
			{
				dataDao.getTabColumnsByLogId(anyLong, anyString);
				result = new ArrayList<TabColumn>();
				times = 1;
			}
		};

		Assert.assertNotNull(dataService.getTabColumnsByLogId(1L, "1"));
		Assert.assertNull(dataService.getTabColumnsByLogId(null, "1"));
		Assert.assertNull(dataService.getTabColumnsByLogId(1L, null));

		new Expectations() {
			{
				dataDao.getTabColumnsByLogId(anyLong, anyString);
				result = new Exception();
				times = 1;
			}
		};

		Assert.assertNull(dataService.getTabColumnsByLogId(1L, "1"));

		new Verifications() {
			{
				dataDao.getTabColumnsByLogId(anyLong, anyString);
				times = 0;
			}
		};

		new Expectations() {
			{
				dataDao.getTabColumnsByConfigId(anyLong, anyString);
				result = new Exception();
				times = 1;
			}
		};

		Assert.assertNull(dataService.getTabColumnsByConfigId(1L, "1"));

		new Expectations() {
			{
				dataDao.getTabColumnsByLogId(anyLong, anyString);
				result = new Exception();
				times = 1;
			}
		};

		Assert.assertNull(dataService.getTabColumnsByLogId(1L, "1"));

	}
}
