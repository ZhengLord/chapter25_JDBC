package com.hsp.dao_.test;

import com.hsp.dao_.dao.ActorDAO;
import com.hsp.dao_.domain.Actor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {
    @Test
    public void testActorDAO(){
        ActorDAO actorDAO = new ActorDAO();
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id>=?", Actor.class, 1);
        System.out.println("====查询结果====");
        for (Actor actor:actors){
            System.out.println(actor);
        }

        //查询单行单列
        Object o = actorDAO.queryScalar("select name from actor where id =?", 1);
        System.out.println(o);
    }
}
