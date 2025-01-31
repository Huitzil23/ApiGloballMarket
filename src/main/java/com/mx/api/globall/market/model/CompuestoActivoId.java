package com.mx.api.globall.market.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CompuestoActivoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCompuestoActivo;	
	private Integer idSucursal;
	public CompuestoActivoId(Integer idCompuestoActivo, Integer idSucursal) {
		super();
		this.idCompuestoActivo = idCompuestoActivo;
		this.idSucursal = idSucursal;
	}
	public CompuestoActivoId() {
		super();
	}
}
