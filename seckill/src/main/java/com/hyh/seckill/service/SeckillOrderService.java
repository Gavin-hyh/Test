package com.hyh.seckill.service;

import com.hyh.seckill.dao.SeckillOrderMapper;
import com.hyh.seckill.pojo.OrderInfo;
import com.hyh.seckill.pojo.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ahthor 侯一衡
 * @date 2019/11/12 14:09
 */
@Service
public class SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    public SeckillOrder queryGoodsByUserIdAndGoodsId(Long id, long goodsId) {
        return seckillOrderMapper.queryGoodsByUserIdAndGoodsId(id,goodsId);
    }


    public OrderInfo queryOrderByOrderId(Long orderId) {
        return seckillOrderMapper.queryOrderByOrderId(orderId);
    }
}
