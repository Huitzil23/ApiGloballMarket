package com.mx.api.globall.market.bean;

public class CalculoPoliticaPrecioIN {
	private Float utilidad;
	private Float porcentajeIVA;
	private Float porcentajeIEPS;
	private Float precioVenta;
	private Float costo;
	private Float precioPublico;
	private Float margen;
	private Float porcentajeDescuento;
	//
	private Boolean conIva;
	
	public CalculoPoliticaPrecioIN() {
		super();
	}
	
	public CalculoPoliticaPrecioIN(Float utilidad, Float porcentajeIVA, Float porcentajeIEPS, Float precioVenta,
			Float costo, Float precioPublico, Float margen, Float porcentajeDescuento, Boolean conIva) {
		super();
		this.utilidad = utilidad;
		this.porcentajeIVA = porcentajeIVA;
		this.porcentajeIEPS = porcentajeIEPS;
		this.precioVenta = precioVenta;
		this.costo = costo;
		this.precioPublico = precioPublico;
		this.margen = margen;
		this.porcentajeDescuento = porcentajeDescuento;
		this.conIva = conIva;
	}

	public Float getUtilidad() {
		return utilidad;
	}
	public void setUtilidad(Float utilidad) {
		this.utilidad = utilidad;
	}
	public Float getPorcentajeIVA() {
		return porcentajeIVA;
	}
	public void setPorcentajeIVA(Float porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}
	public Float getPorcentajeIEPS() {
		return porcentajeIEPS;
	}
	public void setPorcentajeIEPS(Float porcentajeIEPS) {
		this.porcentajeIEPS = porcentajeIEPS;
	}
	public Float getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Float precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Float getCosto() {
		return costo;
	}
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	public Float getPrecioPublico() {
		return precioPublico;
	}
	public void setPrecioPublico(Float precioPublico) {
		this.precioPublico = precioPublico;
	}
	public Float getMargen() {
		return margen;
	}
	public void setMargen(Float margen) {
		this.margen = margen;
	}
	public Float getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	public void setPorcentajeDescuento(Float porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	public Boolean getConIva() {
		return conIva;
	}
	public void setConIva(Boolean conIva) {
		this.conIva = conIva;
	}
	@Override
	public String toString() {
		return "Datos IN -> [utilidad=" + utilidad + ", porcentajeIVA=" + porcentajeIVA + ", porcentajeIEPS=" + porcentajeIEPS + ", precioVenta=" + precioVenta + ", costo=" + costo + ", precioPublico=" + precioPublico + ", margen=" + margen + ", porcentajeDescuento=" + porcentajeDescuento+"]";
	}
}
