package com.hyh.seckill.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeckillOrder {
	private Long id;
	private Long userId;
	private Long  orderId;
	private Long goodsId;

}
