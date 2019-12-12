package com.hyh.seckill.service;

import com.hyh.seckill.dao.GoodsMapper;
import com.hyh.seckill.pojo.OrderInfo;
import com.hyh.seckill.pojo.SeckillOrder;
import com.hyh.seckill.pojo.SeckillUser;
import com.hyh.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ahthor 侯一衡
 * @date 2019/11/12 8:42
 */
@Service
public class GoodsService {

    @Autowired
    private  GoodsMapper goodsMapper;

    public  List<GoodsVo> queryGoodsList() {
        List<GoodsVo> goodsVoList =  goodsMapper.queryGoodsList();
        return goodsVoList;
    }


    public GoodsVo queryGoodsByGoodsId(Long goodsId) {
        GoodsVo goodsVo = goodsMapper.queryGoodsByGoodsId(goodsId);
        return goodsVo;
    }

    public int subtractCount( GoodsVo goodsVo) {
         return goodsMapper.subtractCount(goodsVo);
    }
    @Transactional
    public OrderInfo insertOrder(SeckillUser user, GoodsVo goods) {
        //1 订单表插入数据
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setGoodsChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());

        int i1 =this.goodsMapper.inserOrderInfo(orderInfo);

        //2秒杀订单表插入数据
        SeckillOrder miaoshaOrder = new SeckillOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        int i2 = this.goodsMapper.insertMiaoshaOrder(miaoshaOrder);
        if(i1>0 && i2>0) {
            return orderInfo;
        }
        return orderInfo;
    }
}
