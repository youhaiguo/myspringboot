package com.you.myspringboot.mapper;

import com.you.myspringboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    /**
     * mybatis 注解映射
     * @return
     */
//    @Select("select * from t_user")
//    public List<User> findAll();

    List<User> findAll();
}
