package com.laughing2b.html.dal.dao.ibatis;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.laughing2b.html.dal.dao.LaughingUserDAO;
import com.laughing2b.html.dal.LaughingUser;
import com.laughing.dal.ibatis.ReadWriteSqlMapClientDaoSupport;
import com.laughing.help.biz.query.BaseQuery;
import com.laughing.lang.utils.CollectionUtil;
import com.laughing.lang.utils.MapUtil;
/**
* this file is auto generate.
*/
public class LaughingUserDAOIbatis extends ReadWriteSqlMapClientDaoSupport implements LaughingUserDAO {


    @Override
	public Integer create(LaughingUser laughingUser) {
		return (Integer)this.getSqlMapClientTemplate().insert("LaughingUserDAO.create", laughingUser);
	}

    @Override
    public int batchCreate(final List<LaughingUser> laughingUserList) {
        if (CollectionUtil.isEmpty(laughingUserList)) {
            return 0;
        }
        return (Integer)this.getSqlMapClientTemplate().execute(new SqlMapClientCallback(){
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                for (LaughingUser laughingUser : laughingUserList) {
                    executor.insert("LaughingUserDAO.create", laughingUser);
                }
                return executor.executeBatch();
            }
        });
    }

    @Override
	public int delete(Integer id) {
		return this.getSqlMapClientTemplate().update("LaughingUserDAO.delete", id);
	}

    @Override
    public int deleteByIds(List<Integer> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return 0;
        }
        return this.getSqlMapClientTemplate().update("LaughingUserDAO.deleteByIds", ids);
    }

    @Override
	public int update(LaughingUser laughingUser) {
		return this.getSqlMapClientTemplate().update("LaughingUserDAO.update", laughingUser);
	}

    @Override
	public LaughingUser queryById(Integer id) {
	    return (LaughingUser)this.getSqlMapClientTemplate().queryForObject("LaughingUserDAO.queryById", id);
	}

    @Override
    public Map<Integer, LaughingUser> queryByIds(List<Integer> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return MapUtil.newHashMap();
        }
        List<LaughingUser> resultList = (List<LaughingUser>) this.getSqlMapClientTemplate().queryForList("LaughingUserDAO.queryByIds", ids);
        Map<Integer, LaughingUser> ret = MapUtil.newHashMap();
        for (LaughingUser laughingUser : resultList) {
            ret.put(laughingUser.getId(), laughingUser);
        }
        return ret;
    }

    @Override
    public List<LaughingUser> queryForPage(BaseQuery query) {
        long count = (Long)this.getSqlMapClientTemplate().queryForObject("LaughingUserDAO.queryForPageCount", query);
        if (count == 0) {
            return new ArrayList<LaughingUser>(0);
        }
        query.setTotalResultCount(count);
        return (List<LaughingUser>)this.getSqlMapClientTemplate().queryForList("LaughingUserDAO.queryForPage", query);
    }

}
