/*
package com.hyh.seckill.service;

import com.hyh.seckill.vo.GoodsVo;
import org.springframework.stereotype.Service;

*/
/**
 * @ahthor 侯一衡
 * @date 2019/11/12 11:50
 *//*

@Service
public class SeckillgoodsService {
    @Autowired
    private Seckillgoodsmapper  miaoshaGoodsMapper;
    public List<GoodsVo> queryGoodsList() {

        return this.miaoshaGoodsMapper.queryGoodsList();
    }
    public GoodsVo queryGoodsByGoodsId(Long goodsId) {
        // TODO Auto-generated method stub
        return this.miaoshaGoodsMapper.queryGoodsByGoodsId(goodsId);
    }
    public int reduceCount(GoodsVo goods) {

        return this.miaoshaGoodsMapper.reduceCount(goods);
    }
    @Transactional
    public OrderInfo insertOrder(MiaoshaUser user, GoodsVo goods) {
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

        int i1 =this.miaoshaGoodsMapper.inserOrderInfo(orderInfo);

        //2秒杀订单表插入数据
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        int i2 = this.miaoshaGoodsMapper.insertMiaoshaOrder(miaoshaOrder);
        if(i1>0 && i2>0) {
            return orderInfo;
        }
        return null;

    }
}
*/
