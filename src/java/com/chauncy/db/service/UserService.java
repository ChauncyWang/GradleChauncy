package com.chauncy.db.service;


import com.chauncy.db.dao.IUserDao;
import com.chauncy.db.dao.UserDaoImpl;
import com.chauncy.db.entity.User;
import com.chauncy.db.entity.UserInfo;

/**
 * User的 服务层
 * Created by 13969 on 2017/5/31.
 */
public class UserService {
    /**
     * 用来 服务的dao层
     */
    private static IUserDao userDao = new UserDaoImpl();

    /**
     * login
     * @param id 登录 id
     * @param password 登录 密码
     * @return 登陆成功的 账号信息 登陆失败为空
     */
    public static UserInfo login(String id, String password) {
        UserInfo user = null;
        try{
            user = userDao.login(id,password).getUserInfo();
        }catch (Exception e) {

        }
        return user;
    }

    /**
     * 注册
     * @param user 要注册的 用户
     * @return 是否注册成功
     */
    public static boolean register(User user) {
        return userDao.register(user);
    }

    /**
     * 更新 信息
     * @param user 要修改的 账户信息
     * @return 是否更新成功
     */
    public static boolean updateInfo(User user) {
        try {
            userDao.update(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
