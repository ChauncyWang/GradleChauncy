package com.chauncy.db.dao;

import com.chauncy.db.entity.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * Account Dao的mysql实现
 * Created by 13969 on 2017/6/1.
 */
public class AccountDaoImpl implements IAccountDao {
    /** mapper的namespace */
    private static String namespace = "com.chauncy.db.entity.Account.";
    @Override
    public int add(Account account) throws SQLException {
        int t = sqlSession.insert(namespace+"insertAccount",account);
        sqlSession.commit();
        return t;
    }

    @Override
    public int delete(Account account) throws SQLException {
        int t = sqlSession.delete(namespace+"deleteAccount",account);
        sqlSession.commit();
        return t;
    }

    @Override
    public int update(Account account) throws SQLException {
        return 0;
    }

    @Override
    public Account findByID(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Account> all() throws SQLException {
        return null;
    }
}
