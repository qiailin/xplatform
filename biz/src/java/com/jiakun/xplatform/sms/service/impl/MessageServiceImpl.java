package com.jiakun.xplatform.sms.service.impl;

import com.jiakun.xplatform.api.sms.IMessageService;
import com.jiakun.xplatform.api.sms.bo.Message;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.sms.dao.IMessageDao;

/**
 * 
 * @author jiakunxu
 * 
 */
public class MessageServiceImpl implements IMessageService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MessageServiceImpl.class);

	private IMessageDao messageDao;

	public BooleanResult sendMessage(Message message) {
		BooleanResult result = new BooleanResult();

		try {
			messageDao.createMessage(message);
			result.setResult(true);
		} catch (Exception e) {
			result.setResult(false);
			logger.error(LogUtil.parserBean(message), e);
		}

		return result;
	}

	public IMessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

}
