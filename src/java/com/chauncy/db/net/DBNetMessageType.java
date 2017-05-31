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
    /** 注册 */
    int REGISTER = 102;
    /** 修改个人信息 */
    int UPDATE_USER_INFO = 103;
    /** 记录新的账目 */
    int ADD_ACCOUNT = 201;
    /** 查看我的账单 */
    int MY_ACCOUNT = 202;
    /** 查看全部用户账单分析 */
    int ALL_ACCOUNT = 203;
}
