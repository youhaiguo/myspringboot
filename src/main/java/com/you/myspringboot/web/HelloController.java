package com.you.myspringboot.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.you.myspringboot.dao.UserDao;
import com.you.myspringboot.mapper.UserMapper;
import com.you.myspringboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
//浏览器默认不支持跨域请求
@CrossOrigin(origins = {"http://localhost:8080"})
public class HelloController {

    //模板查询jpa注入
    @Autowired
    UserDao userDao;

    //mybatis 映射注入
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/user/{id}")
    public Object hello(@PathVariable int id){
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
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
     * 分页查询  （分页功能一般写在service层，此处简化了）
     * 备注：这里使用的是mybatis的分页插件PageHelper,它会自动在执行SQL语句时自动装配SQL执行分页功能
     *      前提是在调用userMapper前通过PageHelper.startPage()传入分页参数
     *      再把结果装入PageInfo对象中
     * @return
     */
    @RequestMapping(value = "/findAllByPage")
    public PageInfo<User> findAllByPage(@RequestParam(value = "start",defaultValue = "0")int start,@RequestParam(value = "size",defaultValue = "5")int size){

        //设置分页信息，分别是当前页数和每页显示的总记录数
        //    【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(start,size,"id desc");

        List<User> userList  = userMapper.selectAll();

        System.out.println(userList.size());
        System.out.println("------------");

        PageInfo<User> page = new PageInfo<>(userList);
        System.out.println(page);
        return page;
    }

}
