package com.hyh.seckill.controller;

import com.hyh.seckill.pojo.OrderInfo;
import com.hyh.seckill.pojo.SeckillOrder;
import com.hyh.seckill.pojo.SeckillUser;
import com.hyh.seckill.result.CodeMsg;
import com.hyh.seckill.result.Result;
import com.hyh.seckill.service.GoodsService;
import com.hyh.seckill.service.SeckillOrderService;
import com.hyh.seckill.service.SeckillService;
import com.hyh.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ahthor 侯一衡
 * @date 2019/11/12 11:44
 */
@Controller
@RequestMapping("/seckill")
public class SeckiilController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/do_seckill",method=RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> do_seckill(ModelMap map, SeckillUser user, long goodsId){
        //三步骤
        //1 检查是否有库存
        GoodsVo goods = goodsService.queryGoodsByGoodsId(goodsId);
        if(goods.getStockCount()<=0){
            map.put("msg","已经没有库存了");
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        //2 检查当前用户是否已经秒杀过f
        SeckillOrder seckillOrder =  this.seckillOrderService.queryGoodsByUserIdAndGoodsId(user.getId(),goodsId);
        if( null != seckillOrder ){
            map.put("msg","已经参加过秒杀");
            return Result.error(CodeMsg.GOODS_REPEAT);
        }
        //3 减少库存（miaosha_goods）,生成订单详情，生成秒杀订单
        OrderInfo orderInfo =  seckillService.seckill(user,goods);
        return Result.success(orderInfo);
    }



/*  原方法
/*  原方法
    @RequestMapping("do_seckill")
    public String do_seckill(ModelMap map, SeckillUser user,long goodsId){
        //三步骤
        //1 检查是否有库存
        GoodsVo goods = goodsService.queryGoodsByGoodsId(goodsId);
        if(goods.getStockCount()<=0){
            map.put("msg","已经没有库存了");
            return "seckill_error";
        }
        //2 检查当前用户是否已经秒杀过f
        SeckillOrder seckillOrder =  this.seckillOrderService.queryGoodsByUserIdAndGoodsId(user.getId(),goodsId);
        if( null != seckillOrder ){
            map.put("msg","已经参加过秒杀");
            return "seckill_error";
        }

        //3 减少库存（miaosha_goods）,生成订单详情，生成秒杀订单
        OrderInfo orderInfo =  seckillService.seckill(user,goods);
        map.put("orderInfo",orderInfo);
        map.put("goods",goods);
        return "order_detail";
}*/
}
