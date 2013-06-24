package com.jiakun.xplatform.api.alluser;

import java.util.List;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.alluser.bo.ApplyUsers;
import com.jiakun.xplatform.api.alluser.bo.CmsVwSupplier;
import com.jiakun.xplatform.api.alluser.bo.UserChange;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.bo.StringResult;

/**
 * �����T����������������<br>
 * �����������T�������������� ������I��������
 * 
 * @author xujiakun
 * 
 */
public interface IAllUserService {

	static final String SUCCESS = "success";

	static final String ERROR = "error";

	static final String ERROR_MESSAGE = "����������������������";

	static final String ERROR_INPUT_MESSAGE = "����������������������������������������������";

	static final String ERROR_NULL_MESSAGE = "������������������������������������������";

	static final String ERROR_EXIST_MESSAGE = "���������������������� ����������������";

	static final String ERROR_CONNET_MESSAGE = "����������������������WebService������������������������";

	static final String CHECK_TYPE_HR = "hr";

	static final String CHECK_TYPE_IT = "it";

	static final String CHECK_TYPE_NONE = "none";

	/**
	 * 
	 * @param passport
	 * @return
	 */
	AllUsers getAllUserByPassport(String passport);

	/**
	 * ���������������������������������
	 * 
	 * @param orgId
	 * @return
	 */
	List<AllUsers> getAllUsersByOrgId(String orgId);

	/**
	 * ����������������
	 * 
	 * @param userIds
	 * @return
	 */
	List<AllUsers> getAllUsersByIds(List<String> userIds);

	/**
	 * ��������������������
	 * 
	 * @param allUsers
	 * @return
	 */
	BooleanResult updateAllUser(AllUsers allUsers);

	/**
	 * ����������������info
	 * 
	 * @param allUsers
	 * @return
	 */

	BooleanResult modifyAllUserInfo(AllUsers allUsers);

	/**
	 * ��������������������������������������������������������
	 * 
	 * @param userId
	 * @return
	 */
	AllUsers getAllUser(String userId);

	/**
	 * ��������������������������������������������
	 * 
	 * @param AllUsers
	 * @return
	 */
	List<AllUsers> searchAllUsers(AllUsers alluser);

	/**
	 * ����������������count����������������������������
	 * 
	 * @param AllUsers
	 * @return
	 */
	int searchAllUsersCount(AllUsers alluser);

	/**
	 * ���������������������������������������
	 * 
	 * @param UserChange
	 * @return
	 */

	int getUserChangeCount(UserChange bUser);

	/**
	 * ����������������count�����������������������
	 * 
	 * @param UserChange
	 * @return
	 */
	List<UserChange> getUserChangeList(UserChange bUser);

	BooleanResult deleteBImplementByEmpId(Long userId);

	/**
	 * �������������������������
	 * 
	 * @param UserChange
	 * @return
	 */
	StringResult insertUserChange(UserChange bUser);

	/**
	 * ���������������������������������
	 * 
	 * @param applyUsers
	 * @return
	 */
	int getCheckUserCount(ApplyUsers applyUsers);

	/**
	 * ���������������������������������
	 * 
	 * @param applyUsers
	 * @return
	 */
	List<ApplyUsers> getCheckUserList(ApplyUsers applyUsers);

	/**
	 * �������������������������
	 * 
	 * @param applyUsers
	 * @return
	 */
	AllUsers getCheckUserDetail(ApplyUsers applyUsers);

	/**
	 * ��������������������
	 * 
	 * @param AllUsers
	 * @return
	 */
	BooleanResult modifyUserPsw(AllUsers User);

	/**
	 * ��ApplyUsers
	 * 
	 * @param applyUsers
	 * @return
	 */
	StringResult insertApplyUser(ApplyUsers applyUsers);

	/**
	 * ����������������������������������������
	 * 
	 * @param supplier
	 * @return
	 */
	String getsuppliername(CmsVwSupplier supplier);

	/**
	 * ������������������������������������������
	 * 
	 * @return
	 */
	List<CmsVwSupplier> getSupplierList(CmsVwSupplier supplier);

	int getSupplierCount(CmsVwSupplier supplier);

	/**
	 * �������������������������������������
	 * 
	 * @param ���������loginId
	 * @return
	 */
	BooleanResult checkUserAvilb(String loginId);

	void upReleaseDv(Long myposid, String reason);

}
