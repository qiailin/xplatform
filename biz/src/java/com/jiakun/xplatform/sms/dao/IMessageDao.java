package com.jiakun.xplatform.sms.dao;

import com.jiakun.xplatform.api.sms.bo.Message;

public interface IMessageDao {

	/**
	 * 
	 * @param message
	 * @return
	 */
	public int createMessage(Message message);

}