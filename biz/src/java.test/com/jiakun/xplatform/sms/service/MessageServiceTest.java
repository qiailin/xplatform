package com.jiakun.xplatform.sms.service;

import org.junit.Assert;
import org.junit.Test;

import com.jiakun.xplatform.api.sms.IMessageService;
import com.jiakun.xplatform.api.sms.bo.Message;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.sms.dao.IMessageDao;
import com.jiakun.xplatform.sms.service.impl.MessageServiceImpl;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

/**
 * 
 * @author jiakunxu
 * 
 */
public class MessageServiceTest {

	@Tested
	private IMessageService messageService = new MessageServiceImpl();

	@Injectable
	private IMessageDao messageDao;

	@Test
	public void testSendMessage() {
		final Message message = new Message();

		new NonStrictExpectations() {
			{
				BooleanResult res = new BooleanResult();
				res.setResult(true);

				messageDao.createMessage(message);
				result = 0;
				times = 1;
			}
		};

		Assert.assertEquals(true, messageService.sendMessage(message).getResult());

		new NonStrictExpectations() {
			{
				BooleanResult res = new BooleanResult();
				res.setResult(true);

				messageDao.createMessage(message);
				result = new Exception();
				times = 1;
			}
		};

		Assert.assertEquals(false, messageService.sendMessage(message).getResult());

		new Verifications() {
			{
				messageDao.createMessage(message);
				times = 1;
			}
		};
	}
}
