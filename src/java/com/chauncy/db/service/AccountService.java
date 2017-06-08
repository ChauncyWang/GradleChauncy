package com.chauncy.db.service;

import com.chauncy.db.dao.AccountDaoImpl;
import com.chauncy.db.dao.IAccountDao;
import com.chauncy.db.entity.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * 账目相关服务
 * Created by 13969 on 2017/6/7.
 */
public class AccountService {
    /**
     * 用来 服务的dao层
     */
    private static IAccountDao accountDao = new AccountDaoImpl();

    /**
     * 记录新的账目
     * @param account 账目
     * @return 是否记录成功
     */
    public static boolean newAccount(Account account) {
        try {
            accountDao.add(account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查看我的账单
     * @param id 我的ID
     * @return 账单list
     */
    public static List<Account> myAccount(String id) {
        try{
            return accountDao.findById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查看全部用户的账单
     * @return
     */
    public static List<Account> allAccount() {
        try{
            return accountDao.all();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
