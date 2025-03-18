package com.mx.api.globall.market.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaVentasBeepResponse implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer idSucursal;
	private Integer idVenta;
	private Integer idCliente;
	private BigDecimal totalVenta;
	List<ProductosVentas>productos;


}
