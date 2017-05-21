package com.chauncy.db;

import com.chauncy.db.dao.UserDaoImpl;
import com.chauncy.db.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * db的主函数
 * Created by 13969 on 2017/5/21.
 */
public class MainDB {
    public static void main(String[] args) {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            System.out.println(userDao.all());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
