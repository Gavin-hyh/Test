package com.hyh.seckill.service;

import com.hyh.seckill.dao.SeckillUserMapper;
import com.hyh.seckill.pojo.SeckillUser;
import com.hyh.seckill.redis.MiaoshaUserKey;
import com.hyh.seckill.redis.RedisService;
import com.hyh.seckill.redis.UserKey;
import com.hyh.seckill.result.CodeMsg;
import com.hyh.seckill.util.MD5Util;
import com.hyh.seckill.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @ahthor 侯一衡
 * @date 2019/11/9 8:52
 */
@Service
public class SeckillUserService {

    public static final String COOKIE_TOKEN_NAME="token";

    @Autowired
    private RedisService redisService;

    @Autowired
    private SeckillUserMapper seckillUserMapper;


    private SeckillUser loginByMobile(Long mobile){
        SeckillUser seckillUser = seckillUserMapper.queryUser(mobile);
        if(null != seckillUser){
            return seckillUser;
        }
        seckillUser = seckillUserMapper.queryUser(mobile);
        if(null != seckillUser){
              this.redisService.set(UserKey.getById,""+mobile,seckillUser);
        }
        return seckillUser;
    }

    public String doLogin(Long mobile, String password, HttpServletResponse response) {
        SeckillUser user =  loginByMobile(mobile);

        //判断非空
        if(user == null){
            return CodeMsg.MOBILE_NOT_EXIST.getMsg();
        }
        String salt = user.getSalt();
        String dbPass = MD5Util.formPassToDBPass(password, salt);
        String dbpassword = user.getPassword();
        if(!dbPass.equals(dbpassword)){
            //返回密码错误
            return CodeMsg.PASSWORD_ERROR.getMsg();
        }
        //cookie里面保存一个token（redis里面的key），必须保证唯一存在
        // value值是redis的key  ，要保证唯一
        String token = UUIDUtil.uuid();  //asdfasfassfadffafd233232
        addOrUpdateCookie(token,user,response);
        return token;
    }

    private void addOrUpdateCookie(String token, SeckillUser user, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_TOKEN_NAME,token);
        cookie.setMaxAge(MiaoshaUserKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
        //把数据保存到redis 里面
        this.redisService.set(MiaoshaUserKey.token, token, user);

    }


    public SeckillUser getUserByToken(String token, HttpServletResponse response) {
        SeckillUser user = this.redisService.get(MiaoshaUserKey.token, token, SeckillUser.class);
        //刷新用户登录时间
        this.addOrUpdateCookie(token, user, response);
        return user;
    }
}
