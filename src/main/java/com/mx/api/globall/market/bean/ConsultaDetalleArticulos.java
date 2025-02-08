package com.mx.api.globall.market.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConsultaDetalleArticulos {

	private Integer idSucursal;
	private String ClaveArticulo;	
    private String Nombre;		
    private BigDecimal PrecioPublico;	
    //private BigDecimal PorcDescuento;	
    private BigDecimal PrecioVenta;
    private Integer Existencias;
    
    
	/*public ConsultaDetalleArticulos(Integer idSucursal, String claveArticulo, String nombre, BigDecimal precioPublico,
			BigDecimal precioVenta, Integer existencias) {
		super();
		this.idSucursal = idSucursal;
		ClaveArticulo = claveArticulo;
		Nombre = nombre;
		PrecioPublico = precioPublico;
		PrecioVenta = precioVenta;
		Existencias = existencias;
	}*/
}
