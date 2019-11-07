package com.hyh.seckill.controller;

import com.hyh.seckill.pojo.User;
import com.hyh.seckill.result.Result;
import com.hyh.seckill.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ahthor 侯一衡
 * @date 2019/11/7 14:20
 */
@Controller
public class Usercontroller {
    @Autowired
    private Userservice userservice;

    @RequestMapping("db/get")
    @ResponseBody
    public Result<User> getUserById() {
        User user = this.userservice.getUserById(1);
        return Result.success(user);
    }
    //测试事物
    @RequestMapping("db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        Boolean flag = this.userservice.insert();
        return Result.success(flag);
    }
}
