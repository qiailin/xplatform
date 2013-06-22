package com.jiakun.xplatform.api.menu.bo;

import java.util.List;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * �˵�ʵ��
 * 
 * @author xujiakun
 * 
 */
public class Menu extends SearchInfo {

	private static final long serialVersionUID = -3155216126617302268L;

	/**
	 * �˵�id
	 */
	private Long id;

	/**
	 * �˵����
	 */
	private String name;

	/**
	 * �����˵�id
	 */
	private Long pid;

	/**
	 * �����˵����
	 */
	private String pname;

	private String url;

	private String target;

	private int orderBy;

	private String userId;

	/**
	 * �Ƿ�Ϊ�����̲˵�
	 */
	private String isKuunrMenu;

	/**
	 * �Ƿ�Ϊ���´��˵�
	 */
	private String isOfficeMenu;

	/**
	 * �Ƿ�Ϊˮվ�˵�
	 */
	private String isClientMenu;

	/**
	 * �Ƿ�Ϊ�ͻ��˵�
	 */
	private String isCustMenu;

	/**
	 * ��project��ת��ַ
	 */
	private String redirectUrl;

	/**
	 * ��ɫid
	 */
	private String roleId;

	/**
	 * ��ɫ�˵�id
	 */
	private Long roleMenuId;

	private List<Long> ids;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsKuunrMenu() {
		return isKuunrMenu;
	}

	public void setIsKuunrMenu(String isKuunrMenu) {
		this.isKuunrMenu = isKuunrMenu;
	}

	public String getIsOfficeMenu() {
		return isOfficeMenu;
	}

	public void setIsOfficeMenu(String isOfficeMenu) {
		this.isOfficeMenu = isOfficeMenu;
	}

	public String getIsClientMenu() {
		return isClientMenu;
	}

	public void setIsClientMenu(String isClientMenu) {
		this.isClientMenu = isClientMenu;
	}

	public String getIsCustMenu() {
		return isCustMenu;
	}

	public void setIsCustMenu(String isCustMenu) {
		this.isCustMenu = isCustMenu;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
