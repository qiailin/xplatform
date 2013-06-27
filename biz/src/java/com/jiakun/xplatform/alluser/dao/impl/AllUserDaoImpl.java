package com.jiakun.xplatform.alluser.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiakun.xplatform.alluser.dao.IAllUserDao;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.alluser.bo.ApplyUsers;
import com.jiakun.xplatform.api.alluser.bo.CmsVwSupplier;
import com.jiakun.xplatform.framework.dao.impl.BaseDaoImpl;

public class AllUserDaoImpl extends BaseDaoImpl implements IAllUserDao {

	public AllUsers getAllUserByPassport(String passport) {
		return (AllUsers) getSqlMapClientTemplate().queryForObject("alluser.getAllUserByPassport", passport);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getAllUsersByOrgId(Long orgId) {
		return getSqlMapClientTemplate().queryForList("alluser.getAllUsersByOrgId", orgId);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getAllUsersByIds(List<String> userIds) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("userIds", userIds);

		return getSqlMapClientTemplate().queryForList("alluser.getAllUsersByIds", map);
	}

	public int updateAllUser(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.updateAllUser", allUsers);
	}

	public AllUsers getAllUser(String userId) {
		return (AllUsers) getSqlMapClientTemplate().queryForObject("alluser.getAllUser", userId);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> searchAllUsers(AllUsers alluser) {
		return getSqlMapClientTemplate().queryForList("alluser.searchAllUsers", alluser);
	}

	public int searchAllUsersCount(AllUsers alluser) {
		return (Integer) getSqlMapClientTemplate().queryForObject("alluser.searchAllUsersCount", alluser);
	}

	public int modifyAllUserInfo(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.modifyAllUser", allUsers);
	}

	public int deleteBImplementByEmpId(Long userId) {
		return getSqlMapClientTemplate().delete("alluser.deleteBImplementByEmpId", userId);
	}

	public int getCheckUserCount(ApplyUsers applyUsers) {
		return (Integer) getSqlMapClientTemplate().queryForObject("alluser.getCheckUserCount", applyUsers);
	}

	@SuppressWarnings("unchecked")
	public List<ApplyUsers> getCheckUserList(ApplyUsers applyUsers) {
		return getSqlMapClientTemplate().queryForList("alluser.getCheckUserList", applyUsers);
	}

	public void upReleaseDv(Long myposid, String reason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myposid", myposid);
		map.put("reason", reason);
		this.getSqlMapClientTemplate().queryForObject("alluser.p_release_dv", map);
	}

	public AllUsers getCheckUserDetail(ApplyUsers applyUsers) {
		return (AllUsers) getSqlMapClientTemplate().queryForObject("alluser.getCheckUserDetail", applyUsers);
	}

	public int updateApplyUsers(ApplyUsers applyUsers) {
		return getSqlMapClientTemplate().update("alluser.updateApplyUsers", applyUsers);
	}

	public int checkUserAvilb(String loginId) {
		return (Integer) getSqlMapClientTemplate().queryForObject("alluser.selectCountWithLoginId", loginId);
	}

	public Long insertApplyUser(ApplyUsers applyUsers) {
		return (Long) getSqlMapClientTemplate().insert("alluser.insertApplyUser", applyUsers);
	}

	public String getsuppliername(CmsVwSupplier supplier) {
		return (String) getSqlMapClientTemplate().queryForObject("alluser.getsuppliername", supplier);
	}

	@SuppressWarnings("unchecked")
	public List<CmsVwSupplier> getSupplierList(CmsVwSupplier supplier) {
		return getSqlMapClientTemplate().queryForList("alluser.getSupplierList", supplier);
	}

	public int getSupplierCount(CmsVwSupplier supplier) {
		return (Integer) getSqlMapClientTemplate().queryForObject("alluser.getSupplierCount", supplier);
	}

	public Long insertAllUser(AllUsers allUsers) {
		return (Long) getSqlMapClientTemplate().insert("alluser.insertAlluser", allUsers);

	}

}
