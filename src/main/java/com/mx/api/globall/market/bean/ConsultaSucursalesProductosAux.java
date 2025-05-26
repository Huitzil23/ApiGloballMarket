package com.mx.api.globall.market.bean;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaSucursalesProductosAux {
	
	private Integer idSucursal;
	private String codigoPostal;
	private String nombreComercio;
	private String nombreSucursal;
	private String calle;
	private String numeroExt;
	private String nombreColonia;
	private String delegacion;
	private String estado;
	private String pais;
	private String claveArticulo;
	private String nombreArticulo;
	private BigDecimal precioVenta;
	private Integer ExistenciaFisica;
	private Integer idCompuestoActivo;
	private Integer receta;
	private Boolean requiereReceta;
	private Integer idGrupoSSA;
	private Boolean controlado;
	private String NombreCompuesto;
}
