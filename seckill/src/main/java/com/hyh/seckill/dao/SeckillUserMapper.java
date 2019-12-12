package com.hyh.seckill.dao;

import com.hyh.seckill.pojo.SeckillUser;
import com.hyh.seckill.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ahthor 侯一衡
 * @date 2019/11/9 8:56
 */
@Mapper
public interface SeckillUserMapper {
    @Select("select * from seckill_user where id = #{loginVo}")
    SeckillUser queryUser(@Param(("loginVo")) Long loginVo);

}
