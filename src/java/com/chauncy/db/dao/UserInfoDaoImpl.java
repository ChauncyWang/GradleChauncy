package com.chauncy.db.dao;

import com.chauncy.db.entity.UserInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * Mysql的 Dao 实现
 * Created by 13969 on 2017/5/31.
 */
public class UserInfoDaoImpl implements IUserInfoDao {
    /** mapper的namespace */
    private static String namespace = "com.chauncy.db.entity.UserInfo.";
    @Override
    public int add(UserInfo userInfo) throws SQLException {
        return 0;
    }

    @Override
    public int delete(UserInfo userInfo) throws SQLException {
        return 0;
    }

    @Override
    public int update(UserInfo userInfo) throws SQLException {
        return 0;
    }

    @Override
    public UserInfo findByID(String id) throws SQLException {
        return null;
    }

    @Override
    public List<UserInfo> all() throws SQLException {
        return null;
    }
}
