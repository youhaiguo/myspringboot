package com.you.myspringboot.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.you.myspringboot.dao.UserDao;
import com.you.myspringboot.mapper.UserMapper;
import com.you.myspringboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

    //模板查询jpa注入
    @Autowired
    UserDao userDao;

    //mybatis 映射注入
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/hello/{id}")
    public String hello(@PathVariable String id){

        return "Hello Spring Boot for hot"+"/n"+id;
    }

    @RequestMapping(value = "/exception")
    public String exceptionTest() throws Exception {
        if (true){

            throw new Exception("exception test");
        }
        return "test";
    }

    /**
     * 模本查询（JPA）所有
     * @return
     */
    @RequestMapping(value = "/findAll")
    public List<User> findAll(@RequestParam(value = "start",defaultValue = "0") int start ){
        System.out.println(start);
        List<User> userList = userDao.findAll();

        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    /**
     * 映射查询所有
     * @return
     */
    @RequestMapping(value = "/findAllByMapper")
    public List<User> findAllByMapper(){
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = "/findAllByPage")
    public PageInfo<User> findAllByPage(@RequestParam(value = "start",defaultValue = "0")int start,@RequestParam(value = "size",defaultValue = "5")int size){
        PageHelper.startPage(start,size,"id desc");
        List<User> userList = userMapper.findAll();
        System.out.println(userList.size());
        System.out.println("------------");
        PageInfo<User> page = new PageInfo<>(userList);
        System.out.println(page);
        return page;
    }
}
