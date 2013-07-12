package com.jiakun.xplatform.menu.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.framework.webwork.annotations.JsonResult;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.menu.IMenuService;
import com.jiakun.xplatform.api.menu.bo.Menu;
import com.jiakun.xplatform.api.tree.bo.Tree4Ajax;
import com.jiakun.xplatform.framework.action.BaseAction;
import com.jiakun.xplatform.framework.exception.ServiceException;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;

/**
 * MenuTreeAjax.
 * 
 * @author xujiakun
 * 
 */
public class MenuTreeAjaxAction extends BaseAction {

	private static final long serialVersionUID = -785317530988895673L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(MenuTreeAjaxAction.class);

	private IMenuService menuService;

	private IMemcachedCacheService memcachedCacheService;

	private String node;

	private List<Tree4Ajax> treeList = new ArrayList<Tree4Ajax>();

	@SuppressWarnings("unchecked")
	@JsonResult(field = "treeList", include = { "id", "text", "href", "hrefTarget", "leaf" })
	public String getMenuTreeListByAjax() {

		List<Menu> menuList = null;

		if (StringUtil.isNotEmpty(node)) {
			Menu menu = new Menu();
			AllUsers alluser = this.getUser();

			try {
				menu.setUserId(alluser.getUserId());
				menu.setPid(Long.parseLong(node));
			} catch (NumberFormatException e) {
				logger.error(LogUtil.parserBean(alluser) + "node:" + node, e);
			}

			List<Menu> o = null;

			if ("1".equals(node)) {
				try {
					o =
						(List<Menu>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_MENU_TREE
							+ alluser.getUserId() + "_" + node);
				} catch (ServiceException e) {
					logger.error(e);
				}
			}

			menuList = (o == null || o.size() == 0) ? menuService.getMenuTreeList(menu) : o;

			menu = null;

			if ("1".equals(node) && (o == null || o.size() == 0)) {
				try {
					memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_MENU_TREE + alluser.getUserId() + "_"
						+ node, menuList, IMemcachedCacheService.CACHE_KEY_MENU_TREE_DEFAULT_EXP);
				} catch (Exception e) {
				}
			}
		}

		if (menuList == null || menuList.size() == 0) {
			return JSON;
		}

		for (Menu menu : menuList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(menu.getId()));
			tree.setText(menu.getName());
			if (StringUtil.isNotEmpty(menu.getUrl())) {
				tree.setHref(menu.getUrl());
			}
			tree.setHrefTarget(menu.getTarget());
			if ("NA".equals(tree.getHrefTarget())) {
				tree.setHrefTarget("mainLeft");
				tree.setLeaf(Boolean.FALSE);
			} else {
				tree.setLeaf(Boolean.TRUE);
			}
			// tree.setCls("folder")
			treeList.add(tree);
		}

		return JSON;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

}
