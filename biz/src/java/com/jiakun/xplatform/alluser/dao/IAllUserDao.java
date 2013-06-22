package com.jiakun.xplatform.alluser.dao;

import java.util.List;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.alluser.bo.ApplyUsers;
import com.jiakun.xplatform.api.alluser.bo.CmsVwSupplier;
import com.jiakun.xplatform.api.alluser.bo.UserChange;

public interface IAllUserDao {

	/**
	 * 
	 * @param passport
	 * @return
	 */
	public AllUsers getAllUserByPassport(String passport);

	/**
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getAllUsersByOrgId(Long orgId);

	/**
	 * 
	 * @param userIds
	 * @return
	 */
	public List<AllUsers> getAllUsersByIds(List<String> userIds);

	/**
	 * 
	 * @param allUsers
	 * @return
	 */
	public int updateAllUser(AllUsers allUsers);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public AllUsers getAllUser(String userId);

	/**
	 * ��ѯ��Ա����ҵ�ܹ���
	 * 
	 * @param AllUsers
	 * @return
	 */
	public List<AllUsers> searchAllUsers(AllUsers alluser);

	/**
	 * ��ѯ��Աcount����ҵ�ܹ���
	 * 
	 * @param AllUsers
	 * @return
	 */
	public int searchAllUsersCount(AllUsers alluser);

	/**
	 * ��ѯ��Ա����Ա���
	 * 
	 * @param UserChange
	 * @return
	 */
	public List<UserChange> getUserChangeList(UserChange bUser);

	public int modifyAllUserInfo(AllUsers allUsers);

	public int deleteBImplementByEmpId(Long userId);

	/**
	 * ��ѯ��Աcount����Ա���
	 * 
	 * @param UserChange
	 * @return
	 */
	public int getUserChangeCount(UserChange bUser);

	/**
	 * ��ȡ�����Ա��Ϣ
	 * 
	 * @param applyUsers
	 * @return
	 */
	public int getCheckUserCount(ApplyUsers applyUsers);

	/**
	 * ��ȡ�����Ա��Ϣ
	 * 
	 * @param applyUsers
	 * @return
	 */
	public List<ApplyUsers> getCheckUserList(ApplyUsers applyUsers);

	/**
	 * ���루��Ա���
	 * 
	 * @param UserChange
	 * @return
	 */
	public Long insertUserChange(UserChange bUser);

	/**
	 * 
	 * @param applyUsers
	 * @return
	 */
	public AllUsers getCheckUserDetail(ApplyUsers applyUsers);



	public void upReleaseDv(Long myposid,String reason);

	/**
	 * 
	 * @param applyUsers
	 * @return
	 */
	public int updateApplyUsers(ApplyUsers applyUsers);

	
	public int checkUserAvilb(String loginId);
	
	public Long insertApplyUser(ApplyUsers applyUsers);
	
	/**
	 *  дb_salesinfo
	 * @param allUsers
	 * @return
	 */
	public Long insertAllUser(AllUsers allUsers);

	public String getsuppliername(CmsVwSupplier supplier);

	public List<CmsVwSupplier> getSupplierList(CmsVwSupplier supplier);

	public int getSupplierCount(CmsVwSupplier supplier);
	
}
