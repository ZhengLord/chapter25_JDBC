package com.hsp.jdbc.datasource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtils_Use {
    @Test
    public void testQueryMany() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql="select * from actor ";
        List<Actor> list = queryRunner.query(connection,sql,new BeanListHandler<>(Actor.class));
        for (Actor actor:list){
            System.out.println(actor);
        }
        JDBCUtilsByDruid.close(null,null,connection);

    }
    @Test
    public void testQuerySingle() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql="select * from actor where id=?";
        Actor actor= queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 1);
        System.out.println(actor);
        JDBCUtilsByDruid.close(null,null,connection);

    }
}
