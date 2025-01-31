package com.mx.api.globall.market.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SucursalesAux {

	private Integer idSucursal;
	private String codigoPostal;
	private String nombreComercio;
	private String nombreSucursal;
	private String calle;
	private String numeroExterior;
	private String colonia;
	private String municipioDelegacion;
	private String estado;
	private String pais;
}
