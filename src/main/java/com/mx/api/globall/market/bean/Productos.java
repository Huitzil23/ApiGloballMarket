package com.mx.api.globall.market.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Productos {
	
	private String sku;
	private String nombre;
	private String descripcion;
	private int existencia;	
	private BigDecimal PrecioPublico;
	private Boolean requiereReceta;
	private Boolean esControlado;
	private Integer idCompuestoActivo;
	private String nombreCompuestoActivo;

}
