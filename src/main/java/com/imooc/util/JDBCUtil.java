package com.imooc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * 1) 获取Connection
 * 2) 释放资源
 */
public class JDBCUtil {
    /**
     * 获取Connection
     *
     * @return 所获取到的JDBC的Connection
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        /**
         * 不建议把配置硬编码到代码中
         *
         * 最佳实践:配置性的建议写到配置文件中
         */
//        String driverClass = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql:///spring_data";
//        String user = "root";
//        String password = "123456";
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String driverClass = properties.getProperty("jdbc.driverClass");
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * 释放DB相关的资源
     *
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
