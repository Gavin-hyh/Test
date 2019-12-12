package com.hyh.seckill.dao;

import com.hyh.seckill.pojo.OrderInfo;
import com.hyh.seckill.pojo.SeckillOrder;
import com.hyh.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ahthor 侯一衡
 * @date 2019/11/12 8:43
 */
@Mapper
public interface GoodsMapper {


    @Select("SELECT g.*,sg.miaosha_price,sg.stock_count,sg.start_date,sg.end_date FROM goods g LEFT JOIN seckill_goods sg on g.id = sg.goods_id")
    List<GoodsVo> queryGoodsList();

    @Select("SELECT g.*,sg.miaosha_price,sg.stock_count,sg.start_date,sg.end_date FROM goods g LEFT JOIN seckill_goods sg on g.id = sg.goods_id WHERE sg.id = #{goodsId} ")
    GoodsVo queryGoodsByGoodsId(@Param("goodsId") Long goodsId);

    @Update("update seckill_goods set stock_count=stock_count-1 where goods_id=#{id} and stock_count >0 ")
    int subtractCount( GoodsVo goods);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, goods_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{goodsChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    int inserOrderInfo(OrderInfo orderInfo);

    @Insert("insert into seckill_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertMiaoshaOrder(SeckillOrder miaoshaOrder);
}
