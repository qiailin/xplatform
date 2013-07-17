package com.jiakun.xplatform.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jiakun.xplatform.api.menu.IMenuService;
import com.jiakun.xplatform.api.menu.bo.Menu;
import com.jiakun.xplatform.menu.dao.IMenuDao;
import com.jiakun.xplatform.menu.service.impl.MenuServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

/**
 * 
 * @author jiakunxu
 * 
 */
public class MenuServiceTest {

	@Tested
	private IMenuService menuService = new MenuServiceImpl();

	@Injectable
	private IMenuDao menuDao;

	@Test
	public void testDeleteSelectedMenu4Role() {
		final List<Long> list = new ArrayList<Long>();
		list.add(1L);
		final String roleId = "1";

		new Expectations() {
			{
				menuDao.getChildMenuId4Role(anyString, anyLong);
				returns(null);

				menuDao.deleteSelectedMenu4Role((Menu) any);
				returns(0);

				this.invoke(menuService, "conversion", anyString, list);
				returns(list);
			}
		};

		Menu menu = new Menu();
		menu.setRoleId(roleId);
		menu.setIds(list);

		// Assert.assertEquals(list, menuDao.getChildMenuId4Role("1", 1L));
		// Assert.assertEquals(0, menuDao.deleteSelectedMenu4Role(menu));
		Assert.assertNotNull(menuService.deleteSelectedMenu4Role(menu));

	}
}
