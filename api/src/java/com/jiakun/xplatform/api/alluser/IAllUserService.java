package com.jiakun.xplatform.api.alluser;

import java.util.List;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.alluser.bo.ApplyUsers;
import com.jiakun.xplatform.api.alluser.bo.CmsVwSupplier;
import com.jiakun.xplatform.api.alluser.bo.UserChange;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.bo.StringResult;

/**
 * �ˆT��Ϣ�ӿ�<br>
 * ���ˆT����Ϣ ��I�ܘ�
 * 
 * @author xujiakun
 * 
 */
public interface IAllUserService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	public static final String ERROR_NULL_MESSAGE = "����ʧ�ܣ������ڣ�";

	public static final String ERROR_EXIST_MESSAGE = "����ʧ�ܣ� �Ѵ��ڣ�";

	public static final String ERROR_CONNET_MESSAGE = "����ʧ�ܣ�WebService�����쳣��";

	public static final String CHECK_TYPE_HR = "hr";

	public static final String CHECK_TYPE_IT = "it";

	public static final String CHECK_TYPE_NONE = "none";

	/**
	 * 
	 * @param passport
	 * @return
	 */
	public AllUsers getAllUserByPassport(String passport);

	/**
	 * �����֯��ѯ��Ա
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getAllUsersByOrgId(String orgId);

	/**
	 * ��ѯ��Ա
	 * 
	 * @param userIds
	 * @return
	 */
	public List<AllUsers> getAllUsersByIds(List<String> userIds);

	/**
	 * ������Ա
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult updateAllUser(AllUsers allUsers);

	/**
	 * �޸���Աinfo
	 * 
	 * @param allUsers
	 * @return
	 */

	public BooleanResult modifyAllUserInfo(AllUsers allUsers);

	/**
	 * ��ѯ��Ա����ϸ��Ϣ��ͨѶ¼��
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

	public int getUserChangeCount(UserChange bUser);

	/**
	 * ��ѯ��Աcount����Ա���
	 * 
	 * @param UserChange
	 * @return
	 */
	public List<UserChange> getUserChangeList(UserChange bUser);

	public BooleanResult deleteBImplementByEmpId(Long userId);

	/**
	 * д����Ա���
	 * 
	 * @param UserChange
	 * @return
	 */
	public StringResult insertUserChange(UserChange bUser);

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
	 * �����ϸ��Ϣ
	 * 
	 * @param applyUsers
	 * @return
	 */
	public AllUsers getCheckUserDetail(ApplyUsers applyUsers);

	/**
	 * �޸�����
	 * 
	 * @param AllUsers
	 * @return
	 */
	public BooleanResult modifyUserPsw(AllUsers User);

	/**
	 * дApplyUsers
	 * 
	 * @param applyUsers
	 * @return
	 */
	public StringResult insertApplyUser(ApplyUsers applyUsers);

	/**
	 * ���ҹ�Ӧ��������
	 * 
	 * @param supplier
	 * @return
	 */
	public String getsuppliername(CmsVwSupplier supplier);

	/**
	 * ����ȫ����Ӧ���б�
	 * 
	 * @return
	 */
	public List<CmsVwSupplier> getSupplierList(CmsVwSupplier supplier);

	public int getSupplierCount(CmsVwSupplier supplier);

	/**
	 * �����û��Ƿ����
	 * 
	 * @param ���loginId
	 * @return
	 */
	public BooleanResult checkUserAvilb(String loginId);

	public void upReleaseDv(Long myposid, String reason);

}
