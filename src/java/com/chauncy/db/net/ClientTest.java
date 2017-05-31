package com.chauncy.db.net;

import com.chauncy.nionetframework.entity.NetMessage;
import com.chauncy.nionetframework.util.NetTools;

import java.io.IOException;
import java.net.Socket;

/**
 * 做客户端测试
 * Created by 13969 on 2017/5/30.
 */
public class ClientTest {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",1000);
            NetTools.writeObject(socket, NetMessage.getNullMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
