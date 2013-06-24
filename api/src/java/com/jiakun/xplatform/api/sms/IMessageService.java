package com.jiakun.xplatform.api.sms;

import com.jiakun.xplatform.api.sms.bo.Message;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * ���Žӿ�
 * 
 * @author xujiakun
 * 
 */
public interface IMessageService {

	static final String SMS_TYPE_DEFAULT = "WBZ";

	static final Long SEND_FLAG_DEFAULT = 0l;

	static final String SMS_ID_PREFIX_DEFAULT = "NF";

	/**
	 * ���Ͷ���
	 * 
	 * @param message
	 * @return
	 */
	BooleanResult sendMessage(Message message);

}
