package com.jiakun.xplatform.framework.context;

import java.util.HashMap;
import java.util.Map;

import com.jiakun.xplatform.api.alluser.bo.AllUsers;

/**
 * 
 * @author
 * 
 */
public final class SecurityContext {

	private static final String USER = "USER";

	private static final ThreadLocal<Object> CONTEXT = new ThreadLocal<Object>() {
		protected Object initialValue() {
			return new HashMap<Object, Object>();
		}
	};

	private SecurityContext() {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, AllUsers> getContext() {
		return (Map) CONTEXT.get();
	}

	public static void clear() {
		getContext().clear();
	}

	public static AllUsers getUser() {
		return (AllUsers) getContext().get(USER);
	}

	public static void setUser(AllUsers user) {
		getContext().put(USER, user);
	}

}
