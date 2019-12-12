package com.hyh.seckill.vo;

import com.hyh.seckill.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVo {

	@NotNull
	@IsMobile
	private String mobile;
	@NotNull
	@Length(min=32)
	private String password;
	

	
}
