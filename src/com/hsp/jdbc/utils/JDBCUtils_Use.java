package com.hsp.jdbc.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JDBCUtils_Use {
    @Test
    public void testDML(){
        Connection connection = null;
        String sql="update actor set name=? where id=?";
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"周星驰");
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }
    }
    @Test
    public void testSelect(){
        Connection connection = null;
        String sql="select * from actor ";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection=JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            set = preparedStatement.executeQuery();
            while (set.next()){
                int id= set.getInt("id");
                String name=set.getString("name");
                String sex=set.getString("sex");
                Date bornDate=set.getDate("borndate");
                String phone = set.getString("phone");
                System.out.println(id+"\t"+name+"\t"+sex+"\t"+bornDate+"\t"+phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(set,preparedStatement,connection);
        }
    }
}
