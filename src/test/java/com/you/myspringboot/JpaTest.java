package com.you.myspringboot;

import com.you.myspringboot.dao.UserDao;
import com.you.myspringboot.pojo.User;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * jpa测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest//(classes = Application.class)
public class JpaTest {

    @Autowired
    UserDao userDao;
    RedisAutoConfiguration redisAutoConfiguration;

    @Test
    public void test(){
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
