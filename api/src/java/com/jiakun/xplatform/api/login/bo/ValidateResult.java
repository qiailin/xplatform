package com.jiakun.xplatform.api.login.bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;

/**
 * ��֤��½�󷵻���Ϣ
 * 
 * @author xujiakun
 * 
 */
@XmlRootElement
public class ValidateResult implements Serializable {

	private static final long serialVersionUID = 807590090225810140L;

	// ���ؽ��
	private String resultCode;

	// ��Ϣ
	private String message;

	// ��½�û���Ϣ
	private AllUsers allUser;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AllUsers getAllUser() {
		return allUser;
	}

	public void setAllUser(AllUsers allUser) {
		this.allUser = allUser;
	}

}
