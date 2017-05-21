package com.chauncy.db.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * SQL相关的 工具帮助
 * Created by 13969 on 2017/5/22.
 */
public class SQLHelper {
    /**
     * 根据 配置的xml文件 获取 SQL会话
     * @param fileName 文件名字
     * @return Sql会话
     */
    public static SqlSession getSqlSession(String fileName){
        try {
            InputStream is = Resources.getResourceAsStream(fileName);
            SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
            return sqlSession;
        } catch (IOException e) {
            return null;
        }
    }
}
