package com.mx.api.globall.market.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticulosIdAux{
	private String claveArticulo;	
	private Integer idSucursal;
	public ArticulosIdAux(String claveArticulo, Integer idSucursal) {
		super();
		this.claveArticulo = claveArticulo;
		this.idSucursal = idSucursal;
	}
	public ArticulosIdAux() {
		super();
	}
}
