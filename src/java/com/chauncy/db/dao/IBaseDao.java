package com.chauncy.db.dao;

import com.chauncy.db.util.SQLHelper;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * 基础的 dao 接口
 * Created by 13969 on 2017/5/21.
 */
public interface IBaseDao<T> {
    /**  sql会话 */
    SqlSession sqlSession = SQLHelper.getSqlSession();
    /**
     * 添加
     * @param t 要添加的数据
     * @throws SQLException 添加失败 抛出的 异常
     */
    int add(T t) throws SQLException;

    /**
     * 删除
     * @param t 要删除的数据
     * @throws SQLException 删除失败 抛出的 异常
     */
    int delete(T t) throws SQLException;

    /**
     * 更新
     * @param t 要更新的数据
     * @return 影响的数据个数
     * @throws SQLException 更新失败 抛出的 异常
     */
    int update(T t) throws SQLException;

    /**
     * 根据 ID 查询数据
     * @param id 要查询的 数据 id
     * @throws SQLException 查询失败 抛出的 异常
     */
    T findByID(String id) throws SQLException;

    /**
     * 获取数据表的 所有信息
     * @return 数据表的 所有信息
     * @throws SQLException 查询失败 抛出的 异常
     */
    List<T> all() throws SQLException;
}
