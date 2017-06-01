package com.laughing2b.html.dal.dao;

import java.util.List;
import java.util.Map;
import com.laughing2b.html.dal.LaughingUser;
import com.laughing.help.biz.query.BaseQuery;

/**
* this file is auto generate.
*/
public interface LaughingUserDAO {

	Integer create(LaughingUser laughingUser);

    int batchCreate(List<LaughingUser> laughingUserList);

	int delete(Integer id);

    int deleteByIds(List<Integer> ids);

    int update(LaughingUser laughingUser);

	LaughingUser queryById(Integer id);

    Map<Integer, LaughingUser> queryByIds(List<Integer> ids);

    List<LaughingUser> queryForPage(BaseQuery query);
}
