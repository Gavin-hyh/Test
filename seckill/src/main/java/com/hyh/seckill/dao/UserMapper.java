package com.hyh.seckill.dao;

import com.hyh.seckill.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ahthor 侯一衡
 * @date 2019/11/7 14:15
 */
@Mapper
public interface UserMapper {


    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id")int i);
    @Select("insert into user(id,name) values(#{id},#{name}) ")
    void insertUser(User user);
}
