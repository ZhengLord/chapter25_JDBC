package com.hsp.jdbc.datasource;

import com.hsp.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class JDBCUtilsByDruid_Use {
    @Test
    public void testSelect(){
        Connection connection = null;
        String sql="select * from actor ";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection= JDBCUtilsByDruid.getConnection();
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
            JDBCUtilsByDruid.close(set,preparedStatement,connection);
        }
    }
    @Test
    public void testSelectToArrayList(){
        Connection connection = null;
        String sql="select * from actor ";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        ArrayList<Actor> list=new ArrayList();
        try {
            connection= JDBCUtilsByDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            set = preparedStatement.executeQuery();
            while (set.next()){
                int id= set.getInt("id");
                String name=set.getString("name");
                String sex=set.getString("sex");
                Date bornDate=set.getDate("borndate");
                String phone = set.getString("phone");
                list.add(new Actor(id,name,sex,bornDate,phone));
            }
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(set,preparedStatement,connection);
        }
    }
}
