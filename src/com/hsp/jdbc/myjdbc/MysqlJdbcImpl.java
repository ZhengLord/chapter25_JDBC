package com.hsp.jdbc.myjdbc;

public class MysqlJdbcImpl implements JdbcInterface{

    @Override
    public Object getConnection() {
        System.out.println("得到MySQL的连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成MySQL的增删改查");

    }

    @Override
    public void close() {
        System.out.println("关闭MySQL的连接");
    }
}
