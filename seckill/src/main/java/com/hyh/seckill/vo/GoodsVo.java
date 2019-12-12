package com.hyh.seckill.vo;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodsVo  {
    //goods
	private Long id;  //商品id  
	private String goodsName;
	private String goodsTitle;
	private String goodsImg;
	private String goodsDetail;
	private Double goodsPrice;
	private Integer goodsStock;
	
	//miaosha_goods
	private Long goodsId;   //商品id
	private Double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;


}
