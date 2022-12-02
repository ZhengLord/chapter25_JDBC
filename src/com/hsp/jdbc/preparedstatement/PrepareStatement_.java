package com.hsp.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PrepareStatement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入管理员的名字：");
        String admin_name=input.nextLine();
        System.out.print("请输入管理员的密码：");
        String admin_pwd=input.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql="select name,pwd from admin where name=? and pwd=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给？赋值
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_pwd);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("登入成功");
        }else {
            System.out.println("登入失败");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
