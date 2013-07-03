package com.jiakun.xplatform.alluser.dao;

import java.util.List;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.alluser.bo.ApplyUsers;
import com.jiakun.xplatform.api.alluser.bo.CmsVwSupplier;

/**
 * 
 * @author jiakunxu
 * 
 */
public interface IAllUserDao {

	/**
	 * 
	 * @param passport
	 * @return
	 */
	AllUsers getAllUserByPassport(String passport);

	/**
	 * 
	 * @param orgId
	 * @return
	 */
	List<AllUsers> getAllUsersByOrgId(Long orgId);

	/**
	 * 
	 * @param userIds
	 * @return
	 */
	List<AllUsers> getAllUsersByIds(List<String> userIds);

	/**
	 * 
	 * @param allUsers
	 * @return
	 */
	int updateAllUser(AllUsers allUsers);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	AllUsers getAllUser(String userId);

	/**
	 * 
	 * @param AllUsers
	 * @return
	 */
	List<AllUsers> searchAllUsers(AllUsers alluser);

	/**
	 * 
	 * @param AllUsers
	 * @return
	 */
	int searchAllUsersCount(AllUsers alluser);

	int modifyAllUserInfo(AllUsers allUsers);

	int deleteBImplementByEmpId(Long userId);

	/**
	 * 
	 * @param applyUsers
	 * @return
	 */
	int getCheckUserCount(ApplyUsers applyUsers);

	/**
	 * 
	 * @param applyUsers
	 * @return
	 */
	List<ApplyUsers> getCheckUserList(ApplyUsers applyUsers);

	/**
	 * 
	 * @param applyUsers
	 * @return
	 */
	AllUsers getCheckUserDetail(ApplyUsers applyUsers);

	void upReleaseDv(Long myposid, String reason);

	/**
	 * 
	 * @param applyUsers
	 * @return
	 */
	int updateApplyUsers(ApplyUsers applyUsers);

	int checkUserAvilb(String loginId);

	Long insertApplyUser(ApplyUsers applyUsers);

	/**
	 * insertAllUser.
	 * 
	 * @param allUsers
	 * @return
	 */
	Long insertAllUser(AllUsers allUsers);

	String getsuppliername(CmsVwSupplier supplier);

	List<CmsVwSupplier> getSupplierList(CmsVwSupplier supplier);

	int getSupplierCount(CmsVwSupplier supplier);

}
