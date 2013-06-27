package com.jiakun.xplatform.sms.dao;

import com.jiakun.xplatform.api.sms.bo.Message;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IMessageDao {

	/**
	 * 
	 * @param message
	 * @return
	 */
	int createMessage(Message message);

}
