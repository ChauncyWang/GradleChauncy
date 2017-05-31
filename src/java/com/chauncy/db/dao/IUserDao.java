package com.chauncy.db.dao;

import com.chauncy.db.entity.User;

import java.sql.SQLException;

/**
 * 用户相关的 数据dao接口
 * Created by 13969 on 2017/5/21.
 */
public interface IUserDao extends IBaseDao<User> {
    User login(String id,String pw) throws SQLException;
    boolean register(User user);
}
