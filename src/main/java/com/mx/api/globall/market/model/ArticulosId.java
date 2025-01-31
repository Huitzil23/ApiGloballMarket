package com.mx.api.globall.market.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ArticulosId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7023250164110373847L;
	private String claveArticulo;	
	private Integer idSucursal;
	public ArticulosId(String claveArticulo, Integer idSucursal) {
		super();
		this.claveArticulo = claveArticulo;
		this.idSucursal = idSucursal;
	}
	public ArticulosId() {
		super();
	}
}
