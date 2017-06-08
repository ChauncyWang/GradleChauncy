package com.chauncy.db.net;

import com.chauncy.nionetframework.entity.NetMessageType;

/**
 * 数据库系统的 网络消息类型
 * Created by 13969 on 2017/5/31.
 */
public interface DBNetMessageType extends NetMessageType {
    /** 返回信息 */
    int RETURN = 1;
    /** 登陆 */
    int LOGIN = 101;
    /** 登陆成功 */
    int LOGIN_SUCCESS = 102;
    /** 登陆失败 */
    int LOGIN_FAILED = 103;
    /** 修改个人信息 */
    int UPDATE_USER_INFO = 105;
    /** 修改个人信息成功 */
    int UPDATE_USER_INFO_SUCCESS = 106;
    /** 修改个人信息失败 */
    int UPDATE_USER_INFO_FAILED = 107;
    /** 注册 */
    int REGISTER = 201;
    /** 注册失败 */
    int REGISTER_FAILED = 202;
    /** 注册成功 */
    int REGISTER_SUCCESS = 203;
    /** 记录新的账目 */
    int ADD_ACCOUNT = 301;
    /** 记录新的账目成功 */
    int ADD_ACCOUNT_SUCCESS = 302;
    /** 记录新的账目失败 */
    int ADD_ACCOUNT_FAILED = 303;
    /** 查看我的账单 */
    int MY_ACCOUNT = 401;
    /** 查看全部用户账单 */
    int ALL_ACCOUNT = 501;
}
