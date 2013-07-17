package com.jiakun.xplatform.data.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.support.TransactionTemplate;

import com.jiakun.xplatform.api.data.IDataService;
import com.jiakun.xplatform.api.data.bo.TabColumn;
import com.jiakun.xplatform.data.dao.IDataDao;
import com.jiakun.xplatform.data.dao.IDataLogDao;
import com.jiakun.xplatform.data.service.impl.DataServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

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

		// new Verifications() {
		// {
		// dataService.getTabColumnsByLogId(anyLong, anyString);
		// times = 1;
		// }
		// };
	}
}
