package com.chauncy.db;

import com.chauncy.db.dao.UserDaoImpl;
import com.chauncy.db.entity.User;
import com.chauncy.nionetframework.NIOServer;

import java.sql.SQLException;

/**
 * db的主函数
 * Created by 13969 on 2017/5/21.
 */
public class MainDB {
    public static void main(String[] args) {
        NIOServer nioServer = new NIOServer(1000);
        new Thread(nioServer).start();
    }
}
