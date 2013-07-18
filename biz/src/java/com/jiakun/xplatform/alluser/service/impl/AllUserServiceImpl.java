package com.jiakun.xplatform.alluser.service.impl;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.alluser.dao.IAllUserDao;
import com.jiakun.xplatform.api.alluser.IAllUserService;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.alluser.bo.ApplyUsers;
import com.jiakun.xplatform.api.alluser.bo.CmsVwSupplier;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.bo.StringResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;

/**
 * 
 * @author jiakunxu
 * 
 */
public class AllUserServiceImpl implements IAllUserService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(AllUserServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IAllUserDao allUserDao;

	public AllUsers getAllUserByPassport(String passport) {
		if (StringUtil.isEmpty(passport)) {
			return null;
		}

		try {
			return allUserDao.getAllUserByPassport(passport);
		} catch (Exception e) {
			logger.error(passport, e);
		}

		return null;
	}

	public List<AllUsers> getAllUsersByOrgId(String orgId) {
		if (StringUtil.isEmpty(orgId)) {
			return null;
		}

		try {
			return allUserDao.getAllUsersByOrgId(Long.valueOf(orgId));
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	public List<AllUsers> getAllUsersByIds(List<String> userIds) {
		try {
			return allUserDao.getAllUsersByIds(userIds);
		} catch (Exception e) {
			logger.error(userIds, e);
		}

		return null;
	}

	public BooleanResult updateAllUser(AllUsers allUsers) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = allUserDao.updateAllUser(allUsers);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
		}

		return result;
	}

	public AllUsers getAllUser(String userId) {
		try {
			return allUserDao.getAllUser(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public List<AllUsers> searchAllUsers(AllUsers alluser) {
		try {
			return allUserDao.searchAllUsers(alluser);
		} catch (Exception e) {
			logger.error(alluser, e);
		}

		return null;
	}

	public BooleanResult modifyAllUserInfo(AllUsers allUsers) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = allUserDao.modifyAllUserInfo(allUsers);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
			result.setCode("修改salesemp_info表失败！");
		}

		return result;
	}

	public int searchAllUsersCount(AllUsers alluser) {
		try {
			return allUserDao.searchAllUsersCount(alluser);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(alluser), e);
		}

		return 0;
	}

	public BooleanResult deleteBImplementByEmpId(Long userId) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = allUserDao.deleteBImplementByEmpId(userId);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(userId), e);
		}

		return result;
	}

	public int getCheckUserCount(ApplyUsers applyUsers) {
		try {
			return allUserDao.getCheckUserCount(applyUsers);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(applyUsers), e);
		}

		return 0;
	}

	public List<ApplyUsers> getCheckUserList(ApplyUsers applyUsers) {
		try {
			return allUserDao.getCheckUserList(applyUsers);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(applyUsers), e);
		}

		return null;
	}

	public AllUsers getCheckUserDetail(ApplyUsers applyUsers) {
		try {
			return allUserDao.getCheckUserDetail(applyUsers);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(applyUsers), e);
		}

		return null;
	}

	public BooleanResult modifyUserPsw(AllUsers user) {
		BooleanResult bResult = updateAllUser(user);
		// 还需补充修改AD密码的webservice调用
		return bResult;
	}

	public BooleanResult checkUserAvilb(String loginId) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = allUserDao.checkUserAvilb(loginId);
			if (c == 0) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(loginId), e);
		}

		return result;
	}

	public StringResult insertApplyUser(ApplyUsers applyUsers) {
		StringResult result = new StringResult();

		try {
			Long id = allUserDao.insertApplyUser(applyUsers);
			result.setResult(id.toString());
			result.setCode(IAllUserService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IAllUserService.ERROR);
			result.setResult(IAllUserService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(applyUsers), e);
		}

		return result;
	}

	public String getsuppliername(CmsVwSupplier supplier) {
		try {
			return allUserDao.getsuppliername(supplier);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(supplier), e);
		}

		return "";
	}

	public List<CmsVwSupplier> getSupplierList(CmsVwSupplier supplier) {
		try {
			return allUserDao.getSupplierList(supplier);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public int getSupplierCount(CmsVwSupplier supplier) {
		try {
			return allUserDao.getSupplierCount(supplier);
		} catch (Exception e) {
			logger.error(e);
		}

		return 0;
	}

	public void upReleaseDv(Long myposid, String reason) {
		try {
			allUserDao.upReleaseDv(myposid, reason);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IAllUserDao getAllUserDao() {
		return allUserDao;
	}

	public void setAllUserDao(IAllUserDao allUserDao) {
		this.allUserDao = allUserDao;
	}

}
