package com.mx.api.globall.market.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class VentaDetalleId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idVentaDetalle; 
	private Integer idSucursal; 
	
	public VentaDetalleId() {
		super();
	}

	public VentaDetalleId(Integer idVentaDetalle, Integer idSucursal) {
		super();
		this.idVentaDetalle = idVentaDetalle;
		this.idSucursal = idSucursal;
	}
}
