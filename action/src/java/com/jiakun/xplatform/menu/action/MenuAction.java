package com.jiakun.xplatform.menu.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.login.ICAService;
import com.jiakun.xplatform.api.menu.IMenuService;
import com.jiakun.xplatform.api.menu.bo.Menu;
import com.jiakun.xplatform.framework.action.BaseAction;
import com.jiakun.xplatform.framework.annotation.Decode;
import com.jiakun.xplatform.framework.bo.StringResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.framework.webwork.annotations.JsonResult;

/**
 * Menu
 * 
 * @author xujiakun
 * 
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 7380054609278309516L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(MenuAction.class);

	private IMenuService menuService;

	private List<Menu> menuList = new ArrayList<Menu>();

	private int total = 0;

	private String id;

	private String pid;

	private Menu menu;

	/**
	 * �Ƿ����ڿ�project��ת��ַ
	 */
	private String isRedirect;

	/**
	 * �ϼ��˵����
	 */
	@Decode
	private String pname;

	/**
	 * �˵����
	 */
	@Decode
	private String name;

	/**
	 * ��ɫid
	 */
	@Decode
	private String roleId;

	private String menuIds;

	/**
	 * �˵�id
	 */
	private String node;

	/**
	 * �˵���ת��ַ
	 */
	private String redirectUrl;

	private ICAService caService;

	/**
	 * ��ѯ�˵�
	 * 
	 * @return
	 */
	public String searchMenu() {
		return "searchMenu";
	}

	/**
	 * �˵�����
	 * 
	 * @return
	 */
	@JsonResult(field = "menuList", include = { "id", "pid", "name", "pname", "url", "target", "redirectUrl" }, total = "total")
	public String getMenuJsonList() {
		Menu m = new Menu();
		m = getSearchInfo(m);

		try {
			if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
				m.setId(Long.parseLong(id.trim()));
			}
			if (StringUtil.isNotEmpty(pid) && StringUtil.isNotEmpty(pid.trim())) {
				m.setPid(Long.parseLong(pid.trim()));
			}
			if (StringUtil.isNotEmpty(this.getName()) && StringUtil.isNotEmpty(this.getName().trim())) {
				m.setName(this.getName().trim());
			}
			if (StringUtil.isNotEmpty(pname) && StringUtil.isNotEmpty(pname.trim())) {
				m.setPname(pname.trim());
			}

		} catch (Exception e) {
			logger.error("id:" + id + "pid:" + pid + "name:" + this.getName() + "pname:" + pname, e);
		}

		total = menuService.getMenuCount(m);
		if (total != 0) {
			menuList = menuService.getMenuList(m);
		}

		return JSON;
	}

	/**
	 * validate
	 * 
	 * @param menu
	 * @return
	 */
	private boolean validate(Menu menu) {

		if (menu == null) {
			return false;
		}

		if (menu.getPid() == null || StringUtil.isEmpty(menu.getName()) || StringUtil.isEmpty(menu.getName().trim())
			|| StringUtil.isEmpty(menu.getTarget()) || StringUtil.isEmpty(menu.getTarget().trim())) {
			return false;
		}

		return true;
	}

	public String createMenuPrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * �����˵�
	 * 
	 * @return
	 */
	public String createMenu() {

		if (!validate(menu)) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		if ("y".equals(isRedirect)) {
			menu.setRedirectUrl(menu.getUrl());
			menu.setUrl("/" + env.getProperty("appName") + IMenuService.MENU_REDIRECT_URL);
		}

		StringResult result = menuService.createMenu(menu);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * �޸�/��ѯ�˵���Ϣ
	 * 
	 * @return
	 */
	public String updateMenuPrepare() {

		if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
			try {
				menu = menuService.getMenuById(Long.parseLong(id));
				return UPDATE_PREPARE;
			} catch (Exception e) {
				logger.error(id, e);
			}
		}

		menu = new Menu();
		return UPDATE_PREPARE;
	}

	public String updateMenu() {

		if (!validate(menu)) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		if ("y".equals(isRedirect)) {
			menu.setRedirectUrl(menu.getUrl());
			menu.setUrl("/" + env.getProperty("appName") + IMenuService.MENU_REDIRECT_URL + menu.getId());
		}

		StringResult result = menuService.updateMenu(menu);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		return RESULT_MESSAGE;
	}

	public String deleteMenu() {

		String[] l = new String[menuList.size()];
		int i = 0;
		Menu menu = new Menu();

		try {
			for (Menu m : menuList) {
				if (m.getId() != null) {
					l[i++] = m.getId().toString();
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menuList), e);
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// ����Ч�Ĳ˵�id
		if (i == 0) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		menu.setCodes(l);
		StringResult result = menuService.deleteMenu(menu);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ�ɾ��" + result.getResult() + "���˵���");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ��ɫ�˵�
	 * 
	 * @return
	 */
	public String searchSelectedMenu4Role() {

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		return "searchSelectedMenu4Role";
	}

	/**
	 * ��ɫ�˵�����
	 * 
	 * @return
	 */
	@JsonResult(field = "menuList", include = { "roleMenuId", "id", "pid", "name", "url", "target", "redirectUrl" }, total = "total")
	public String getSelectedMenu4RoleJsonList() {
		Menu m = new Menu();
		m = getSearchInfo(m);

		try {
			if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
				m.setId(Long.parseLong(id.trim()));
			}
			if (StringUtil.isNotEmpty(this.getName()) && StringUtil.isNotEmpty(this.getName().trim())) {
				m.setName(this.getName().trim());
			}
			if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
				m.setRoleId(roleId.trim());
			}

		} catch (Exception e) {
			logger.error("id:" + id + "name:" + this.getName() + "roleId" + roleId, e);
		}

		total = menuService.getSelectedMenu4RoleCount(m);
		if (total != 0) {
			menuList = menuService.getSelectedMenu4RoleList(m);
		}

		return JSON;
	}

	/**
	 * ������ɫ�˵�
	 * 
	 * @return
	 */
	public String selectMenu4Role() {
		Menu m = new Menu();

		if (StringUtil.isNotEmpty(menuIds) && StringUtil.isNotEmpty(menuIds.trim())) {
			m.setCodes(menuIds.split(","));
		}

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			m.setRoleId(roleId.trim());
		}

		StringResult result = menuService.selectMenu4Role(m);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		if (IMenuService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("�ɹ�ά���˵���ţ�" + result.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ɾ���ɫ�˵�
	 * 
	 * @return
	 */
	public String deleteSelectedMenu4Role() {

		Menu menu = new Menu();
		List<Long> ids = new ArrayList<Long>();

		try {
			for (Menu m : menuList) {
				if (m.getId() != null) {
					ids.add(m.getId());
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menuList), e);
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// ����Ч�Ĳ˵�id
		if (ids.size() == 0) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		menu.setIds(ids);
		menu.setRoleId(roleId);
		StringResult result = menuService.deleteSelectedMenu4Role(menu);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ�ɾ��" + result.getResult() + "���˵���");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ��ϵͳ�˵���ת
	 * 
	 * @return
	 */
	public String redirectMenu() {
		if (StringUtil.isNotEmpty(node) && StringUtil.isNotEmpty(node.trim())) {
			try {
				Menu menu = menuService.getMenuById(Long.parseLong(node));
				redirectUrl = menu.getRedirectUrl();

				sso();

			} catch (Exception e) {
				logger.error(node, e);
			}
		}

		if (StringUtil.isEmpty(redirectUrl)) {
			redirectUrl = "/";
		}

		if (StringUtil.isNotEmpty(redirectUrl) && StringUtil.contains(redirectUrl, "?")) {
			return "menuRedirect2";
		} else {
			return "menuRedirect1";
		}
	}

	/**
	 * ���token
	 * 
	 * @param redirectUrl
	 */
	private void sso() {
		AllUsers alluser = this.getUser();

		try {
			token = caService.generateToken(alluser);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getIsRedirect() {
		return isRedirect;
	}

	public void setIsRedirect(String isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

}
