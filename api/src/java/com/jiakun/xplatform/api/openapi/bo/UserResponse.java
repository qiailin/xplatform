package com.jiakun.xplatform.api.openapi.bo;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;

/**
 * 
 * @author
 * 
 */
public class UserResponse extends Response {

	private static final long serialVersionUID = 948804661476557568L;

	private AllUsers allUser;

	public AllUsers getAllUser() {
		return allUser;
	}

	public void setAllUser(AllUsers allUser) {
		this.allUser = allUser;
	}

}
