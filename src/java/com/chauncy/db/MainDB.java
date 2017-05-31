package com.chauncy.db;

import com.chauncy.db.dao.UserDaoImpl;
import com.chauncy.db.entity.User;
import com.chauncy.db.entity.UserInfo;
import com.chauncy.db.net.MessageHandler;
import com.chauncy.db.service.UserService;
import com.chauncy.mybatis.Main;
import com.chauncy.nionetframework.NIOServer;

import java.sql.SQLException;

/**
 * db的主函数
 * Created by 13969 on 2017/5/21.
 */
public class MainDB extends NIOServer{
    public static void main(String[] args) {
        new MainDB(1000);
    }
    public MainDB(int port) {
        super(port);
        messageHandler = new MessageHandler(this);
        new Thread(this).start();
    }

    /**
     * 消息处理线程
     */
    private MessageHandler messageHandler;
}
