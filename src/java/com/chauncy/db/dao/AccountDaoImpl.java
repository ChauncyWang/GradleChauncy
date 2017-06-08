package com.chauncy.db.dao;

import com.chauncy.db.entity.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * Account Dao的mysql实现
 * Created by 13969 on 2017/6/1.
 */
public class AccountDaoImpl extends IAccountDao {
    /** mapper的namespace */
    private static String namespace = "com.chauncy.db.entity.Account.";
    @Override
    public int add(Account account) throws SQLException {
        int t = sqlSession.insert(namespace+"insertAccount",account);
        sqlSession.commit();
        return t;
    }

    @Override
    public List<Account> all() throws SQLException {
        return sqlSession.selectList(namespace+"selectAll");
    }

    @Override
    public List<Account> findById(String id) {
        return sqlSession.selectList(namespace+"selectById",id);
    }
}
