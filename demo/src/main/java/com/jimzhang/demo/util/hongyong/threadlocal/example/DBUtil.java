package com.jimzhang.demo.util.hongyong.threadlocal.example;

import com.jimzhang.demo.util.hongyong.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 使用ThreadLocal
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-26 17:11
 */
public class DBUtil {
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

    }

    /**
     * 定义一个用于放置数据库连接的局部线程变量（是每个线程拥有自己的连接）
     */
    private static ThreadLocal<Connection> connContainer = new ThreadLocal<>();

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        Connection conn = connContainer.get();
        try {
            if (conn ==null) {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connContainer.set(conn);
        }
        return conn;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        Connection conn = connContainer.get();
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connContainer.remove();
        }
    }
}
