package com.hsp.jdbc;


import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //注册驱动
        Driver driver = new Driver();
        //使用反射加载Driver类 ，动态加载，更加的灵活，减少依赖性
//        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
//        Driver driver= (Driver) aClass.newInstance();
        //得到连接
        //1.jdbc:mysql:// 规定好协议，通过jdbc的方式连接mysql
        //2.localhost 主机
        //3.hsp_db02表示连接到mysql dbms的哪个数据库
        String url="jdbc:mysql://localhost:3306/hsp_db02";
        //将 用户名和密码放入到Properties对象中
        Properties properties = new Properties();
        properties.setProperty("user","root");//用户
        properties.setProperty("password","hsp");//密码
        Connection connect = driver.connect(url, properties);
        //执行sql
        String sql="insert into actor values(null,'刘德华','男','1970-11-11','110')";
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql);//如果是 dml语句，返回的是影响的行数
        System.out.println(rows>0?"成功":"失败");

        statement.close();
        connect.close();


    }
    @Test
    public void connect03() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver= (Driver) aClass.newInstance();
        String url="jdbc:mysql://localhost:3306/hsp_db02";
        String user="root";
        String password="hsp";
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第三种方式"+connection);

    }
    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/hsp_db02";
        String user="root";
        String password="hsp";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第四种方式"+connection);
    }
    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第五种方式"+connection);




    }
}
