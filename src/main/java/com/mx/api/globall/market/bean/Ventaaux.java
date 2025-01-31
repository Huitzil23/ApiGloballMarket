package com.mx.api.globall.market.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ventaaux {

	private Integer idVenta;
	private String claveArticulo;
	private Integer cantidad; 
	private Integer idSucursal;
	private Integer idCliente;
	private Integer usuarioCreacion;
	private Float precioVenta;
	private Float precioPublico;
	private Float porcentajeDescuento;
	private Float importeDescuento;
	private Float total;
	private Integer operacion;
	private Integer idTerminal;
	private Integer idCorteCaja;
	private Integer isDeliv;
	private Float costo;
	private Float margen;
	private Float utilidad;
	private Float porcentaIVA;
	private Float porcentajeIEPS;
	private Float importeIVA;
	private Float importeIEPS;
	private Float subTotal;
	private Float subTotalConDescuento;
	
	public Ventaaux() {
		super();
	}
	
	public Ventaaux(Integer idVenta, String claveArticulo, Integer cantidad, Integer idSucursal, Integer idCliente,
			Integer usuarioCreacion, Float precioVenta,Float precioPublico, Float porcentajeDescuento, Float importeDescuento, Float total, Integer operacion, Integer idTerminal, Integer idCorteCaja,
			Integer isDeliv, Float costo, Float margen, Float utilidad, Float porcentaIVA, Float porcentajeIEPS, Float importeIVA, Float importeIEPS, Float subTotal, Float subTotalConDescuento) {
		super();
		this.idVenta = idVenta;
		this.claveArticulo = claveArticulo;
		this.cantidad = cantidad;
		this.idSucursal = idSucursal;
		this.idCliente = idCliente;
		this.usuarioCreacion = usuarioCreacion;
		this.precioVenta = precioVenta;
		this.precioPublico = precioPublico;
		this.porcentajeDescuento = porcentajeDescuento;
		this.importeDescuento = importeDescuento;
		this.total = total;
		this.operacion = operacion;
		this.idTerminal = idTerminal;
		this.idCorteCaja = idCorteCaja;
		this.isDeliv = isDeliv;
		this.costo = costo;
		this.margen = margen;
		this.utilidad = utilidad;
		this.porcentaIVA = porcentaIVA;
		this.porcentajeIEPS = porcentajeIEPS;
		this.importeIVA = importeIVA;
		this.importeIEPS = importeIEPS;
		this.subTotal = subTotal;
		this.subTotalConDescuento = subTotalConDescuento;
	}
	
}
