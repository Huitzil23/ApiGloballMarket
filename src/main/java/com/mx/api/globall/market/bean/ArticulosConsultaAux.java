package com.mx.api.globall.market.bean;

import java.math.BigDecimal;
import javax.persistence.Lob;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArticulosConsultaAux {
	private ArticulosIdAux articulosId;
	
    private String claveArticulo;
    @Lob
   	private byte[] thumbnail;
    private String thumbnailAux;
    private BigDecimal precioVenta;
    private String nombre;
    private BigDecimal precioPublicoDelivery;		
    private BigDecimal descuentoDelivery;
    private BigDecimal precioVentaDelivery;
    private Integer Existencias;
	public ArticulosConsultaAux(ArticulosIdAux articulosId, String claveArticulo, byte[] thumbnail, String thumbnailAux,
			BigDecimal precioVenta, String nombre, BigDecimal precioPublicoDelivery, BigDecimal descuentoDelivery,
			BigDecimal precioVentaDelivery, Integer existencias) {
		super();
		this.articulosId = articulosId;
		this.claveArticulo = claveArticulo;
		this.thumbnail = thumbnail;
		this.thumbnailAux = thumbnailAux;
		this.precioVenta = precioVenta;
		this.nombre = nombre;
		this.precioPublicoDelivery = precioPublicoDelivery;
		this.descuentoDelivery = descuentoDelivery;
		this.precioVentaDelivery = precioVentaDelivery;
		Existencias = existencias;
	}
	public ArticulosConsultaAux() {
		super();
	}
    	
}
