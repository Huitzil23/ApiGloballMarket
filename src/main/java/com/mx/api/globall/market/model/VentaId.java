package com.mx.api.globall.market.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class VentaId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idVenta; 
	private Integer idSucursal; 
	
	public VentaId() {
		super();
	}

	public VentaId(Integer idVenta, Integer idSucursal) {
		super();
		this.idVenta = idVenta;
		this.idSucursal = idSucursal;
	}
}
