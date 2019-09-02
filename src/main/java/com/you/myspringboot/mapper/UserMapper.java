package com.you.myspringboot.mapper;

import com.you.myspringboot.pojo.User;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Component
public interface UserMapper extends Mapper<User> {

    /**
     * mybatis 注解映射
     * @return
     */
//    @Select("select * from t_user")
//    public List<User> findAll();

    List<User> findAll();
}
