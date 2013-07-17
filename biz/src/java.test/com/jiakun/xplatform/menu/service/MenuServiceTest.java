package com.jiakun.xplatform.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jiakun.xplatform.api.menu.IMenuService;
import com.jiakun.xplatform.api.menu.bo.Menu;
import com.jiakun.xplatform.menu.dao.IMenuDao;
import com.jiakun.xplatform.menu.service.impl.MenuServiceImpl;

import mockit.Injectable;
import mockit.NonStrictExpectations;
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

		new NonStrictExpectations() {
			{
				menuDao.getChildMenuId4Role(anyString, anyLong);
				result = null;

				this.invoke(menuService, "conversion", anyString, list);
				result = list;

				menuDao.deleteSelectedMenu4Role((Menu) any);
				result = 0;
			}
		};

		Menu menu = new Menu();
		menu.setRoleId(roleId);
		menu.setIds(list);

		Assert.assertEquals(null, menuDao.getChildMenuId4Role("1", 1L));
		Assert.assertEquals(0, menuDao.deleteSelectedMenu4Role(menu));
		Assert.assertEquals(IMenuService.SUCCESS, menuService.deleteSelectedMenu4Role(menu).getCode());

		new NonStrictExpectations() {
			{
				menuDao.getChildMenuId4Role(anyString, anyLong);
				result = null;

				this.invoke(menuService, "conversion", anyString, list);
				result = list;

				menuDao.deleteSelectedMenu4Role((Menu) any);
				result = new Exception();
			}
		};

		Assert.assertEquals(IMenuService.ERROR, menuService.deleteSelectedMenu4Role(menu).getCode());
	}

}
