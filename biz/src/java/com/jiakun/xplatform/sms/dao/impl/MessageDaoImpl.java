package com.jiakun.xplatform.sms.dao.impl;

import java.sql.SQLException;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.alibaba.common.lang.StringUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jiakun.xplatform.api.sms.bo.Message;
import com.jiakun.xplatform.framework.dao.impl.BaseDaoImpl;
import com.jiakun.xplatform.sms.dao.IMessageDao;

public class MessageDaoImpl extends BaseDaoImpl implements IMessageDao {

	public int createMessage(final Message message) {

		return (Integer) getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					public Object doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();

						String mobile = message.getMobile();

						if (StringUtil.isNotEmpty(mobile)) {
							String[] mobiles = mobile.split(";");
							for (String m : mobiles) {
								message.setMobile(m.trim());
								executor.insert("sms.createMessage", message);
							}
						}

						return executor.executeBatch();
					}
				});
	}

}
