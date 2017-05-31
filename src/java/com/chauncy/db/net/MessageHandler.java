package com.chauncy.db.net;

import com.chauncy.db.entity.User;
import com.chauncy.db.entity.UserInfo;
import com.chauncy.db.service.UserService;
import com.chauncy.nionetframework.NIOServer;
import com.chauncy.nionetframework.entity.IoSession;
import com.chauncy.nionetframework.entity.MessageNode;
import com.chauncy.nionetframework.entity.NetMessage;
import org.apache.log4j.Logger;

/**
 * 消息处理线程
 * Created by 13969 on 2017/5/31.
 */
public class MessageHandler implements Runnable {
    private Logger logger = Logger.getLogger(MessageHandler.class);
    private NIOServer nioServer;

    public MessageHandler(NIOServer nioServer) {
        this.nioServer = nioServer;
        new Thread(this, "消息处理线程").start();
    }

    @Override
    public void run() {
        logger.info("消息处理线程启动...");
        while (true) {
            MessageNode msg = nioServer.getMq().removeToRMQ();
            if (msg != null) {
                handler(msg);
            }
        }
    }

    private void handler(MessageNode msg) {
        NetMessage nmsg = msg.getMessage();
        NetMessage resultMsg = null;
        MessageNode result = null;
        switch (nmsg.what) {
            case DBNetMessageType.LOGIN:
                // 处理 登录消息
                User user = (User) nmsg.obj;
                UserInfo info = UserService.login(user.getId(),user.getPassword());
                resultMsg = new NetMessage(DBNetMessageType.RETURN,info);
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
            case DBNetMessageType.REGISTER:
                //处理 注册消息
                user = (User) nmsg.obj;
                resultMsg = new NetMessage(DBNetMessageType.RETURN,UserService.register(user));
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
            case DBNetMessageType.UPDATE_USER_INFO:
                user = (User) nmsg.obj;
                resultMsg = new NetMessage(DBNetMessageType.RETURN,UserService.updateInfo(user));
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
        }
    }
}
