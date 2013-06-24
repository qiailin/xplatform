package com.jiakun.xplatform.api.menu;

import java.util.List;

import com.jiakun.xplatform.api.menu.bo.Menu;
import com.jiakun.xplatform.framework.bo.StringResult;

/**
 * �ˆνӿ�<br>
 * �����ˆξS�o ��ɫ�ˆξS�o
 * 
 * @author xujiakun
 * 
 */
public interface IMenuService {

	static final String SUCCESS = "success";

	static final String ERROR = "error";

	static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	static final String ERROR_NULL_MESSAGE = "����ʧ�ܣ������Ѳ����ڣ�";

	static final String MENU_REDIRECT_URL = "/menu/menuAction!redirectMenu.htm?node=";

	/**
	 * �˵���
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
	 * �����ˆ�
	 * 
	 * @param menu
	 * @return
	 */
	StringResult createMenu(Menu menu);

	/**
	 * �޸Ĳˆ�
	 * 
	 * @param menu
	 * @return
	 */
	StringResult updateMenu(Menu menu);

	/**
	 * ���id��ѯ�˵���Ϣ
	 * 
	 * @param id
	 * @return
	 */
	Menu getMenuById(Long id);

	/**
	 * �h��ˆ�
	 * 
	 * @param menu
	 * @return
	 */
	StringResult deleteMenu(Menu menu);

	/**
	 * ��ѯ��ɫ�����˵�
	 * 
	 * @param menu
	 * @return
	 */
	int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * ��ѯ��ɫ�����˵�
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * ѡ���ɫ�˵�
	 * 
	 * @param menu
	 * @return
	 */
	StringResult selectMenu4Role(Menu menu);

	/**
	 * ɾ���ɫ�����˵�
	 * 
	 * @param menu
	 * @return
	 */
	StringResult deleteSelectedMenu4Role(Menu menu);

}
