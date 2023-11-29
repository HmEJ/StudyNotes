package com.mh.springbootmybatis.mapper;

import com.mh.springbootmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2023/11/27 下午5:01
 * @Created by joneelmo
 */
@Mapper
@Repository
public interface UserMapper {
    public List<User> findAll();
}
