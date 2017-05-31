package com.chauncy.db.net;

import com.chauncy.db.entity.User;
import com.chauncy.db.entity.UserInfo;
import com.chauncy.nionetframework.entity.NetMessage;
import com.chauncy.nionetframework.util.NetTools;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 做客户端测试
 * Created by 13969 on 2017/5/30.
 */
public class ClientTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try{
            Socket socket = new Socket("127.0.0.1",1000);
            while(true) {
                System.out.println("请输入测试的消息类型");
                System.out.println("1:注册");
                System.out.println("2:登录");
                System.out.println("3:更新信息");
                int a = in.nextInt();
                switch (a) {
                    case 1:
                        User user ;
                        System.out.println("请依次输入id 密码 性别(男/女) 年龄 是否在城市(是/否)");
                        user = new User(in.next(),in.next(),new UserInfo(in.next().equals("男"),in.nextInt(),in.next().equals("是")));
                        NetMessage msg = new NetMessage(DBNetMessageType.REGISTER,user);
                        NetTools.writeObject(socket,msg);
                        System.out.println(NetTools.readObject(socket));
                        break;
                    case 2:
                        System.out.println("请依次输入id 密码");
                        user = new User(in.next(),in.next(),null);
                        msg = new NetMessage(DBNetMessageType.LOGIN,user);
                        NetTools.writeObject(socket,msg);
                        System.out.println(NetTools.readObject(socket));
                        break;
                    case 3:
                        System.out.println("请依次输入id 密码 要修改为的性别 年龄 是否在城市");
                        user = new User(in.next(),in.next(),new UserInfo(in.next().equals("男"),in.nextInt(),in.next().equals("是")));
                        msg = new NetMessage(DBNetMessageType.UPDATE_USER_INFO,user);
                        NetTools.writeObject(socket,msg);
                        System.out.println(NetTools.readObject(socket));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
