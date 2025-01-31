package com.mx.api.globall.market.bean;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaSucursalesProductosResponse implements Serializable{

	private static final long serialVersionUID = 7661741320528484207L;
	
	private int idSucursal;
	private String codigoPostal;
	private String nombreComercio;
	private String nombreSucursal;
	private String calle;
	private String numeroExterior;
	private String colonia;
	private String municipioDelegacion;
	private String estado;
	private String pais;
	private List<Productos>productos;
	
}
