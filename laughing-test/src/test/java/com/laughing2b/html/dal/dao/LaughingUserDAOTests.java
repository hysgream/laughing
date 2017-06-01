package com.laughing2b.html.dal.dao;

import java.util.Arrays;
import java.util.Map;
import com.laughing2b.html.dal.LaughingUser;
import javax.annotation.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import junit.framework.Assert;

/**
* this file is auto generate.
*/
@ContextConfiguration(locations = "classpath:spring/spring-mvc-servlet.xml")
public class LaughingUserDAOTests extends AbstractJUnit4SpringContextTests{
	@Resource
	private LaughingUserDAO laughingUserDAO;

    public void testCreate() {
        LaughingUser laughingUser = new LaughingUser();
		laughingUser.setExtb("a");
		laughingUser.setYearMember("a");
		laughingUser.setCreateTime(new java.util.Date());
		laughingUser.setExta("a");
		laughingUser.setIcon("a");
		laughingUser.setPhone("a");
		laughingUser.setDes("a");
		laughingUser.setExtc("a");
		laughingUser.setUpdateTime(new java.util.Date());
		laughingUser.setStatus("a");
		laughingUser.setNickname("a");
		laughingUser.setEffectiveEndTime(new java.util.Date());
		laughingUser.setPassword("a");
		laughingUser.setEffectiveStartTime(new java.util.Date());
		laughingUser.setAssets(2);
		laughingUser.setMonthMember("a");
		laughingUser.setLoginIp("a");
		laughingUser.setEmail("a");
		laughingUser.setLifeMember("a");
		Integer id = laughingUserDAO.create(laughingUser);
		Assert.assertTrue(id > 0);
	}

    public void testQueryById() {
        LaughingUser laughingUser = new LaughingUser();
		laughingUser.setExtb("a");
		laughingUser.setYearMember("a");
		laughingUser.setCreateTime(new java.util.Date());
		laughingUser.setExta("a");
		laughingUser.setIcon("a");
		laughingUser.setPhone("a");
		laughingUser.setDes("a");
		laughingUser.setExtc("a");
		laughingUser.setUpdateTime(new java.util.Date());
		laughingUser.setStatus("a");
		laughingUser.setNickname("a");
		laughingUser.setEffectiveEndTime(new java.util.Date());
		laughingUser.setPassword("a");
		laughingUser.setEffectiveStartTime(new java.util.Date());
		laughingUser.setAssets(2);
		laughingUser.setMonthMember("a");
		laughingUser.setLoginIp("a");
		laughingUser.setEmail("a");
		laughingUser.setLifeMember("a");
        Integer id = laughingUserDAO.create(laughingUser);
        Assert.assertTrue(id > 0);
        LaughingUser laughingUser_2 = laughingUserDAO.queryById(id);
        Assert.assertNotNull(laughingUser_2);
        Map<Integer, LaughingUser> laughingUser_3 = laughingUserDAO.queryByIds(Arrays.asList(id));
        Assert.assertNotNull(laughingUser_3);
        Assert.assertTrue(!laughingUser_3.isEmpty());
    }

    public void testDelete() {
        LaughingUser laughingUser = new LaughingUser();
		laughingUser.setExtb("a");
		laughingUser.setYearMember("a");
		laughingUser.setCreateTime(new java.util.Date());
		laughingUser.setExta("a");
		laughingUser.setIcon("a");
		laughingUser.setPhone("a");
		laughingUser.setDes("a");
		laughingUser.setExtc("a");
		laughingUser.setUpdateTime(new java.util.Date());
		laughingUser.setStatus("a");
		laughingUser.setNickname("a");
		laughingUser.setEffectiveEndTime(new java.util.Date());
		laughingUser.setPassword("a");
		laughingUser.setEffectiveStartTime(new java.util.Date());
		laughingUser.setAssets(2);
		laughingUser.setMonthMember("a");
		laughingUser.setLoginIp("a");
		laughingUser.setEmail("a");
		laughingUser.setLifeMember("a");
        Integer id = laughingUserDAO.create(laughingUser);
        Assert.assertTrue(id > 0);
        LaughingUser laughingUser_2 = laughingUserDAO.queryById(id);
        Assert.assertNotNull(laughingUser_2);
        laughingUserDAO.delete(id);
        laughingUser_2 = laughingUserDAO.queryById(id);
        Assert.assertNull(laughingUser_2);
    }

    public void testUpdate() {
        LaughingUser laughingUser = new LaughingUser();
		laughingUser.setExtb("a");
		laughingUser.setYearMember("a");
		laughingUser.setCreateTime(new java.util.Date());
		laughingUser.setExta("a");
		laughingUser.setIcon("a");
		laughingUser.setPhone("a");
		laughingUser.setDes("a");
		laughingUser.setExtc("a");
		laughingUser.setUpdateTime(new java.util.Date());
		laughingUser.setStatus("a");
		laughingUser.setNickname("a");
		laughingUser.setEffectiveEndTime(new java.util.Date());
		laughingUser.setPassword("a");
		laughingUser.setEffectiveStartTime(new java.util.Date());
		laughingUser.setAssets(2);
		laughingUser.setMonthMember("a");
		laughingUser.setLoginIp("a");
		laughingUser.setEmail("a");
		laughingUser.setLifeMember("a");
        Integer id = laughingUserDAO.create(laughingUser);
        Assert.assertTrue(id > 0);
        LaughingUser laughingUser_2 = laughingUserDAO.queryById(id);
        // TODO finish update


        laughingUserDAO.update(laughingUser_2);
        laughingUser_2 = laughingUserDAO.queryById(id);
        // TODO finish your asserts

    }


    public void setLaughingUserDAO(LaughingUserDAO laughingUserDAO) {
		this.laughingUserDAO = laughingUserDAO;
	}

}
