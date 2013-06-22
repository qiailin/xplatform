package com.jiakun.xplatform.menu.dao;

import java.util.List;

import com.jiakun.xplatform.api.menu.bo.Menu;

public interface IMenuDao {

	/**
	 * �˵���
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getMenuTreeList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int getMenuCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getMenuList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public Long createMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int updateMenu(Menu menu);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Menu getMenuById(Long id);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int deleteMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int deleteSelectedMenu4Role(Menu menu);

	/**
	 * ��֤��ɫ+�˵��Ƿ��Ѿ�����; true: ����
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public boolean checkSelectedMenu4Role(String roleId, Long menuId);

	/**
	 * ������ɫ�˵�
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public Long selectMenu4Role(String roleId, Long menuId);

	/**
	 * ��ݲ˵�menuId ��ȡ������ɫ�˵�menuId
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public Long getParentMenuId4Role(String roleId, Long menuId);

	/**
	 * ��ݲ˵�menuId ��ȡ�Ӽ���ɫ�˵�menuId
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public List<Long> getChildMenuId4Role(String roleId, Long menuId);

}
