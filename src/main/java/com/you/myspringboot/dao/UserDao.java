package com.you.myspringboot.dao;

import com.you.myspringboot.pojo.User;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
