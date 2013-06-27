package com.jiakun.xplatform.api.menu;

import java.util.List;

import com.jiakun.xplatform.api.menu.bo.Menu;
import com.jiakun.xplatform.framework.bo.StringResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IMenuService {

	String SUCCESS = "success";

	String ERROR = "error";

	String ERROR_MESSAGE = "����ʧ�ܣ�";

	String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	String ERROR_NULL_MESSAGE = "����ʧ�ܣ������Ѳ����ڣ�";

	String MENU_REDIRECT_URL = "/menu/menuAction!redirectMenu.htm?node=";

	/**
	 * getMenuTreeList.
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getMenuTreeList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int getMenuCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getMenuList(Menu menu);

	/**
	 * createMenu.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult createMenu(Menu menu);

	/**
	 * updateMenu.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult updateMenu(Menu menu);

	/**
	 * getMenuById.
	 * 
	 * @param id
	 * @return
	 */
	Menu getMenuById(Long id);

	/**
	 * deleteMenu.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult deleteMenu(Menu menu);

	/**
	 * getSelectedMenu4RoleCount.
	 * 
	 * @param menu
	 * @return
	 */
	int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * getSelectedMenu4RoleList.
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * selectMenu4Role.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult selectMenu4Role(Menu menu);

	/**
	 * deleteSelectedMenu4Role.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult deleteSelectedMenu4Role(Menu menu);

}
