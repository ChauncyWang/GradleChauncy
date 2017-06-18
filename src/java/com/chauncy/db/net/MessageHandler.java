package com.chauncy.db.net;

import com.chauncy.db.entity.Account;
import com.chauncy.db.entity.User;
import com.chauncy.db.entity.UserInfo;
import com.chauncy.db.service.AccountService;
import com.chauncy.db.service.UserService;
import com.chauncy.nionetframework.NIOServer;
import com.chauncy.nionetframework.entity.MessageNode;
import com.chauncy.nionetframework.entity.NetMessage;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

import static com.chauncy.db.net.DBNetMessageType.*;

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
        NetMessage resultMsg ;
        MessageNode result;
        switch (nmsg.what) {
            case LOGIN:
                // 处理 登录消息
                User user = (User) nmsg.obj;
                UserInfo info = UserService.login(user.getId(),user.getPassword());
                int t = info==null?LOGIN_FAILED:LOGIN_SUCCESS;
                resultMsg = new NetMessage(t,info);
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
            case REGISTER:
                //处理 注册消息
                user = (User) nmsg.obj;
                boolean b = UserService.register(user);
                t = b?REGISTER_SUCCESS:REGISTER_FAILED;
                resultMsg = new NetMessage(t,"");
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
            case UPDATE_USER_INFO:
                user = (User) nmsg.obj;
                b = UserService.updateInfo(user);
                resultMsg = new NetMessage(b?UPDATE_USER_INFO_SUCCESS:UPDATE_USER_INFO_FAILED,b);
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
            case ADD_ACCOUNT:
                Account account = (Account) nmsg.obj;
                t = account == null?ADD_ACCOUNT_FAILED:ADD_ACCOUNT_SUCCESS;
                resultMsg = new NetMessage(t, AccountService.newAccount(account));
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
            case MY_ACCOUNT:
                String id = (String) nmsg.obj;
                List<Account> accounts = AccountService.myAccount(id);
                if(accounts == null) {
                    accounts = new LinkedList<>();
                }
                resultMsg = new NetMessage(MY_ACCOUNT, accounts);
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
            case ALL_ACCOUNT:
                resultMsg = new NetMessage(ALL_ACCOUNT, AccountService.allAccount());
                result = new MessageNode(msg.getIp(),msg.getPort(),resultMsg);
                nioServer.getMq().addToWMQ(result);
                break;
        }
    }
}
