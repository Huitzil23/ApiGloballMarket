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
public class BuscaSucursalCodigoPostalResponse implements Serializable{
	

	private static final long serialVersionUID = -6896611577605357654L;
	
	private Integer code;
	private String mensaje;

}
