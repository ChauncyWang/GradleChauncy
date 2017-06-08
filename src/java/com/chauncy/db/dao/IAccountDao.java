package com.chauncy.db.dao;

import com.chauncy.db.entity.Account;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * Created by 13969 on 2017/6/1.
 */
public abstract class IAccountDao implements IBaseDao<Account> {
    @Override
    public int delete(Account account) throws SQLException {
        return 0;
    }

    @Override
    public int update(Account account) throws SQLException {
        return 0;
    }


    @Override
    public Account findByID(String id) throws SQLException {
        return null;
    }

    public abstract List<Account> findById(String id);
}
