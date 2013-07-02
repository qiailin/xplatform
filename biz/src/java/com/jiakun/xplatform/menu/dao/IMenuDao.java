package com.jiakun.xplatform.menu.dao;

import java.util.List;

import com.jiakun.xplatform.api.menu.bo.Menu;

/**
 * 
 * @author jiakunxu
 * 
 */
public interface IMenuDao {

	/**
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
	 * 
	 * @param menu
	 * @return
	 */
	Long createMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int updateMenu(Menu menu);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Menu getMenuById(Long id);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int deleteMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int deleteSelectedMenu4Role(Menu menu);

	/**
	 * ��֤��ɫ+�˵��Ƿ��Ѿ�����; true: ����
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	boolean checkSelectedMenu4Role(String roleId, Long menuId);

	/**
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	Long selectMenu4Role(String roleId, Long menuId);

	/**
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	Long getParentMenuId4Role(String roleId, Long menuId);

	/**
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	List<Long> getChildMenuId4Role(String roleId, Long menuId);

}
