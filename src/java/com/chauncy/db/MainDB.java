package com.chauncy.db;

import com.chauncy.db.dao.UserDaoImpl;
import com.chauncy.db.entity.User;

import java.sql.SQLException;

/**
 * db的主函数
 * Created by 13969 on 2017/5/21.
 */
public class MainDB {
    public static void main(String[] args) {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            user.setId("111");
            user.setPassword("111");
            userDao.add(user);
            user.setPassword("222");
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
