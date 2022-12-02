package com.hsp.jdbc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;
//利用静态代码块去初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\mysql.properties"));
             user = properties.getProperty("user");
             password = properties.getProperty("password");
             driver = properties.getProperty("driver");
             url = properties.getProperty("url");

        } catch (IOException e) {
            //在实际开发中，我们可以这样处理
            //1.将编译异常转成 运行异常
            //这时调用者 可以选择捕获该异常，也可以选择默认处理该异常
            throw  new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet set, Statement statement,Connection connection){
        try {
            if(set!=null){
                set.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
