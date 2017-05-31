package com.chauncy.db.net;

import com.chauncy.nionetframework.NIOServer;

/**
 * 消息处理线程
 * Created by 13969 on 2017/5/31.
 */
public class MessageHandler implements Runnable{
    private NIOServer nioServer;
    public MessageHandler(NIOServer nioServer) {
        this.nioServer = nioServer;
    }
    @Override
    public void run() {

    }
}
