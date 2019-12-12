package com.hyh.seckill.controller;

import com.hyh.seckill.pojo.SeckillUser;
import com.hyh.seckill.redis.MiaoshaUserKey;
import com.hyh.seckill.redis.RedisService;
import com.hyh.seckill.redis.SeckillGoodsKey;
import com.hyh.seckill.result.Result;
import com.hyh.seckill.service.GoodsService;
import com.hyh.seckill.service.SeckillUserService;
import com.hyh.seckill.vo.GoodsDetailVo;
import com.hyh.seckill.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ahthor 侯一衡
 * @date 2019/11/11 19:48
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;


    /**
     * 1.使用页面优化 存储redis
     * 2.使用thymeleaf 静态化
     *
     */



    @RequestMapping(value = "to_list",produces = "text/html")
    @ResponseBody
    public String TogoodsList(@CookieValue(value = SeckillUserService.COOKIE_TOKEN_NAME,required = false)String cookieToken,
                              @RequestParam(value = SeckillUserService.COOKIE_TOKEN_NAME,required = false)String paramToken, ModelMap map,
                                HttpServletRequest request, HttpServletResponse response, Model model

    ){
        String token =  StringUtils.isEmpty(cookieToken)?paramToken :cookieToken;

        SeckillUser user = this.redisService.get(MiaoshaUserKey.token, token, SeckillUser.class);
        List<GoodsVo> goodsVoList =this.goodsService.queryGoodsList();
        map.put("goodsList",goodsVoList);
        map.put("user",user);

        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        //7 如果html不为空 把html保存到reids里面，注意保持时间为60秒
        if(!StringUtils.isEmpty(html)) {
            this.redisService.set(SeckillGoodsKey.getGoodsListKey, "", html);
        }

        return html;
    }

    @RequestMapping(value = "/to_detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(ModelMap map, SeckillUser user, @PathVariable("goodsId")Long goodsId,
                                        HttpServletResponse response, HttpServletRequest request, Model model
    ){
        GoodsVo goodsVo = goodsService.queryGoodsByGoodsId(goodsId);
        //秒杀状态 0未开始 1 已经开始 2 结束
        long seckillStatus = 0;
        //剩余时间
        long remainSeconds = 0;
        long start = goodsVo.getStartDate().getTime();
        long end = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();
        if(now < start ) {//秒杀未开始
            seckillStatus = 0;
            remainSeconds  =(start - now )/1000;
        }else if(now>end){//秒杀结束
            seckillStatus = 2;
            remainSeconds  = -1;
        }else{//开始秒杀
            seckillStatus = 1;
            remainSeconds  = -0;
        }

        map.put("seckillStatus", seckillStatus);
        map.put("remainSeconds", remainSeconds);
        GoodsDetailVo detailVo = new GoodsDetailVo();
        detailVo.setGoodsVo(goodsVo);
        detailVo.setSeckillUser(user);
        detailVo.setSeckillStatus(seckillStatus);
        detailVo.setRemainSeconds(remainSeconds);
        return Result.success(detailVo);
    }
}
