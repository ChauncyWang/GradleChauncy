package com.chauncy.db.dao;

import com.chauncy.db.entity.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * 简单的 User类型 dao 实现
 * Created by 13969 on 2017/5/22.
 */
public class UserDaoImpl implements IUserDao {
    /** mapper的namespace */
    private static String namespace = "com.chauncy.db.entity.User.";
    @Override
    public int add(User user) throws SQLException {
        int t = sqlSession.insert(namespace+"insertUser",user);
        t += sqlSession.insert(namespace+"insertUserInfo",user);
        sqlSession.commit();
        return t;
    }

    @Override
    public int delete(User user) throws SQLException {
        int t = sqlSession.delete(namespace+"deleteUserInfo",user);
        t += sqlSession.delete(namespace+"deleteUser",user);
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

    @Override
    public User login(String id, String pw) throws SQLException {
        HashMap map = new HashMap();
        map.put("id",id);
        map.put("password",pw);
        return sqlSession.selectOne(namespace+"login",map);
    }

    @Override
    public boolean register(User user){
        try{
            add(user);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
