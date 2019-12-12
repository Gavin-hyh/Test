package com.hyh.seckill.dao;

import com.hyh.seckill.pojo.OrderInfo;
import com.hyh.seckill.pojo.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @ahthor 侯一衡
 * @date 2019/11/12 14:10
 */
@Mapper
public interface SeckillOrderMapper {

    @Select("SELECT * FROM seckill_order WHERE user_id =#{id} AND goods_id = ${goodsId}")
    SeckillOrder queryGoodsByUserIdAndGoodsId(@Param("id") Long id,@Param("goodsId") long goodsId);

    @Select("select * from seckill_order where order_id=#{orderId} ")
    OrderInfo queryOrderByOrderId(Long orderId);

}
