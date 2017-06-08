package com.chauncy.db.net;

import com.chauncy.nionetframework.entity.NetMessage;

import java.io.IOException;
import java.net.Socket;
import static com.chauncy.nionetframework.util.NetTools.*;

/**
 * 服务端线程
 * Created by 13969 on 2017/6/5.
 */
public class ClientNet {
    private static String host = "192.168.3.2";
    private static int port = 18896;
    private Socket socket;
    public ClientNet() throws IOException {
        socket = new Socket(host,port);
    }


    /**
     * 发送网络消息
     * @param msg 消息
     * @throws IOException 发送失败
     */
    public void sendMsg(NetMessage msg) throws IOException {
        writeObject(socket,msg);
    }

    /**
     * 获取网络消息
     * @return 获取到的消息
     * @throws IOException 网络错误
     * @throws ClassNotFoundException 无法转为NetMessage
     */
    public NetMessage recieveMsg() throws IOException, ClassNotFoundException {
        return (NetMessage) readObject(socket);
    }

    /// getter
    public Socket getSocket() {
        return socket;
    }
}
