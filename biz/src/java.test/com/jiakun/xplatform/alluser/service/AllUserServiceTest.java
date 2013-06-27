package com.jiakun.xplatform.alluser.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiakun.xplatform.api.alluser.IAllUserService;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:bean/spring-alluser.xml",
		"classpath:bean/spring-baseDao.xml",
		"classpath:bean/spring-config.test.xml" })
public class AllUserServiceTest {

	@Resource
	private IAllUserService allUserService;

	@Test
	public void findTest() {
		AllUsers user = allUserService.getAllUserByPassport("");
		// junit ����
		Assert.assertNotNull(user);
	}
}
