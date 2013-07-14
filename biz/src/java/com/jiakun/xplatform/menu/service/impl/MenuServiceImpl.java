package com.jiakun.xplatform.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jiakun.xplatform.api.menu.IMenuService;
import com.jiakun.xplatform.api.menu.bo.Menu;
import com.jiakun.xplatform.framework.bo.StringResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.menu.dao.IMenuDao;

/**
 * 
 * @author jiakunxu
 * 
 */
public class MenuServiceImpl implements IMenuService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MenuServiceImpl.class);

	private IMenuDao menuDao;

	private TransactionTemplate transactionTemplate;

	public List<Menu> getMenuTreeList(Menu menu) {
		try {
			return menuDao.getMenuTreeList(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return null;
	}

	public int getMenuCount(Menu menu) {
		try {
			return menuDao.getMenuCount(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return 0;
	}

	public List<Menu> getMenuList(Menu menu) {
		try {
			return menuDao.getMenuList(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return null;
	}

	public StringResult createMenu(Menu menu) {
		StringResult result = new StringResult();

		try {
			Long id = menuDao.createMenu(menu);
			result.setResult(id.toString());
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IMenuService.ERROR);
			result.setResult(IMenuService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public StringResult updateMenu(Menu menu) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = menuDao.updateMenu(menu);
			if (c == 1) {
				result.setCode(IMenuService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public Menu getMenuById(Long id) {
		try {
			return menuDao.getMenuById(id);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(id), e);
		}

		return null;
	}

	public StringResult deleteMenu(Menu menu) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = menuDao.deleteMenu(menu);
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public int getSelectedMenu4RoleCount(Menu menu) {
		try {
			return menuDao.getSelectedMenu4RoleCount(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return 0;
	}

	public List<Menu> getSelectedMenu4RoleList(Menu menu) {
		try {
			return menuDao.getSelectedMenu4RoleList(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return null;
	}

	public StringResult selectMenu4Role(Menu menu) {
		StringResult result = new StringResult();

		try {
			final String roleId = menu.getRoleId();
			StringBuilder ids = new StringBuilder();

			// 遍历选择的菜单 保证每一个菜单创建后是可用有效的（角色对应的菜单树是完整）
			for (String code : menu.getCodes()) {

				final Long menuId = Long.parseLong(code.trim());

				// 验证角色下该菜单是否存在
				boolean b = menuDao.checkSelectedMenu4Role(roleId, menuId);
				if (b) {
					continue;
				}

				Object o = transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						Long roleMenuId;
						// 1st 创建角色菜单
						try {
							roleMenuId = menuDao.selectMenu4Role(roleId, menuId);
						} catch (Exception e) {
							logger.error("roleId:" + roleId + "menuId:" + menuId, e);
							ts.setRollbackOnly();
							return null;
						}

						// 2nd 创建角色菜单的上一级菜单 pid == -1
						Long id = menuId;
						do {
							try {
								id = menuDao.getParentMenuId4Role(roleId, id);

								if (id == null) {
									break;
								}

								menuDao.selectMenu4Role(roleId, id);
							} catch (Exception e) {
								logger.error("roleId:" + roleId + "id:" + id, e);
								ts.setRollbackOnly();
								return null;
							}

						} while (true);

						// 返回角色菜单id
						return roleMenuId;
					}
				});

				if (o != null) {
					if (ids.length() != 0) {
						ids.append(",");
					}
					ids.append(o);
				}
			}

			result.setResult(ids.toString());
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IMenuService.ERROR);
			result.setResult(IMenuService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public StringResult deleteSelectedMenu4Role(Menu menu) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			List<Long> menuIds = conversion(menu.getRoleId(), menu.getIds());

			String[] l = new String[menuIds.size()];
			int i = 0;

			for (Long id : menuIds) {
				l[i++] = id.toString();
			}

			menu.setCodes(l);
			int c = menuDao.deleteSelectedMenu4Role(menu);
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	/**
	 * 
	 * @param roleId
	 * @param ids
	 * @return
	 */
	private List<Long> conversion(String roleId, List<Long> ids) {
		List<Long> menuIds = new ArrayList<Long>();
		List<Long> childIds = null;

		for (Long id : ids) {
			menuIds.add(id);

			childIds = menuDao.getChildMenuId4Role(roleId, id);

			if (childIds != null && childIds.size() != 0) {
				menuIds.addAll(conversion(roleId, childIds));
			}
		}

		return menuIds;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

}
