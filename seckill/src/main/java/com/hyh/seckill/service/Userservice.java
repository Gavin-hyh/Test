package com.hyh.seckill.service;

import com.hyh.seckill.dao.UserMapper;
import com.hyh.seckill.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ahthor 侯一衡
 * @date 2019/11/7 14:21
 */
@Service
public class Userservice {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(int i) {
        // TODO Auto-generated method stub
        return this.userMapper.getUserById(i);
    }

    @Transactional
    public Boolean insert() {
        User user = new User();
        user.setId(2);
        user.setName("ls");
        this.userMapper.insertUser(user);

        user.setId(3);
        user.setName("zs");
        this.userMapper.insertUser(user);


        return true;
    }
}
