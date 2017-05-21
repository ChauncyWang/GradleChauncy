package com.chauncy.db.dao;

import com.chauncy.db.entity.User;
import com.chauncy.db.util.SQLHelper;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * 简单的 User类型 dao 实现
 * Created by 13969 on 2017/5/22.
 */
public class UserDaoImpl implements IUserDao {
    /** mybatis配置文件所在位置 */
    public static String url = "com/chauncy/db/mybatis-config.xml";
    /**  sql会话 */
    private static SqlSession sqlSession = SQLHelper.getSqlSession(url);
    /** mapper的namespace */
    private static String namespace = "com.chauncy.db.entity.User.";
    @Override
    public void add(User user) throws SQLException {
        sqlSession.insert(namespace+"insertUser",user);
        sqlSession.commit();
    }

    @Override
    public void delete(User user) throws SQLException {

    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public void findByID(String id) throws SQLException {

    }

    @Override
    public List<User> all() throws SQLException {
        return sqlSession.selectList(namespace+"selectAll");
    }
}
