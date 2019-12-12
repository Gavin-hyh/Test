package com.hyh.seckill.service;

import com.hyh.seckill.pojo.OrderInfo;
import com.hyh.seckill.pojo.SeckillUser;
import com.hyh.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ahthor 侯一衡
 * @date 2019/11/12 14:23
 */
@Service
public class SeckillService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;


    public OrderInfo seckill(SeckillUser user, GoodsVo goods) {

        //减库存
        int count = goodsService.subtractCount(goods);
        if(count >0 ){
            OrderInfo orderInfo = goodsService.insertOrder(user, goods);
            return orderInfo;
        }
        //订单
        return null;
    }
}
