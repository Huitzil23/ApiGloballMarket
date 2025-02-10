package com.mx.api.globall.market.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstatusOrdenResponse implements Serializable{
	
	private static final long serialVersionUID = -5775203129636732200L;
	
	private Integer status_order;
	private String message;	

}
