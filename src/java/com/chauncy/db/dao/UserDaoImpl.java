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
    public int add(User user) throws SQLException {
        int t = sqlSession.insert(namespace+"insertUser",user);
        sqlSession.commit();
        return t;
    }

    @Override
    public int delete(User user) throws SQLException {
        int t = sqlSession.delete(namespace+"deleteUser",user);
        sqlSession.commit();
        return t;
    }

    @Override
    public int update(User user) throws SQLException {
        int t = sqlSession.update(namespace+"updateUser",user);
        sqlSession.commit();
        return t;
    }

    @Override
    public User findByID(String id) throws SQLException {
        return sqlSession.selectOne(namespace+"selectUser",id);
    }

    @Override
    public List<User> all() throws SQLException {
        return sqlSession.selectList(namespace+"selectAll");
    }
}
