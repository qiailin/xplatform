package com.jiakun.xplatform.api.openapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * ͳһ�ӿ�
 * 
 * @author xujiakun
 * 
 */
public interface IOpenapiService {

	public static final String XPLATFORM_USER_LOGIN = "xplatform.user.login";

	public static final String ERROR = "-1";

	public static final String ERROR_MSG_PARAMS = "The params can not be empty.";

	public static final String ERROR_MSG_METHOD = "The method can not be empty.";

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String rest(MultivaluedMap<String, String> params);

}
