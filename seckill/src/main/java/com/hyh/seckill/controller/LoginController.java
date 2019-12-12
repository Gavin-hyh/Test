package com.hyh.seckill.controller;

import com.hyh.seckill.result.CodeMsg;
import com.hyh.seckill.result.Result;
import com.hyh.seckill.service.SeckillUserService;
import com.hyh.seckill.util.ValidatorUtil;
import com.hyh.seckill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ahthor 侯一衡
 * @date 2019/11/8 8:52
 */

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private SeckillUserService seckillUserService;


    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> login(Long mobile,String password,HttpServletResponse response) {
        //经过一系列的登录判断
        if(StringUtils.isEmpty(password)) {
            return Result.error(CodeMsg.PASSWORD_ISNULL);
        }

        if(!ValidatorUtil.isMobile(""+mobile)) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }


        String token = this.seckillUserService.doLogin(mobile,password,response);
/*        if(0==codeMsg.getCode()){
            return Result.success(true);
        }*/
        return Result.success(token);
    }



}

