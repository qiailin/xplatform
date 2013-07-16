package com.jiakun.xplatform.data.service;

import java.util.ArrayList;

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
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

/**
 * 
 * @author jiakunxu
 * 
 */
public class DataServiceTest {

	@Injectable
	IDataService dataService = new DataServiceImpl();

	@Mocked
	private IDataDao dataDao;

	@Test
	public void testGetTabColumnsByLogId() {
		new Expectations() {
			{
				dataDao.getTabColumnsByLogId(anyLong, anyString);
				result = new ArrayList<TabColumn>();
			}
		};

		dataService.getTabColumnsByLogId(1L, "1");

	}

}
