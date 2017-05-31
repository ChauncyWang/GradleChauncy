package com.chauncy.db.net;

import com.chauncy.nionetframework.entity.NetMessageType;

/**
 * 数据库系统的 网络消息类型
 * Created by 13969 on 2017/5/31.
 */
public interface DBNetMessageType extends NetMessageType {
    /**
     * 返回信息
     */
    int RETURN = 1;
    /**
     * 登陆
     */
    int LOGIN = 101;
    /**
     * 注册
     */
    int REGISTER = 102;
}
