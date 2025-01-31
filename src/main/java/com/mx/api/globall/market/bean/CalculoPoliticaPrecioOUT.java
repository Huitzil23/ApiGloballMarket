package com.mx.api.globall.market.bean;

public class CalculoPoliticaPrecioOUT {
	private Float precioVenta;
	private Float porcIVA; 
	private Float impIVA; 
	private Float porcIEPS;
	private Float impIEPS; 
	private Float impuestos;
	private Float subtotal;
	private Float ultimoCosto; 
	private Float utilidad; 
	private Float margen;
	private Float precioPublico;
	private Float porcDescuento;
	private Float descuentoImporte;
	
	public CalculoPoliticaPrecioOUT() {
		super();
	}

	public CalculoPoliticaPrecioOUT(Float precioVenta, Float porcIVA, Float impIVA,
			Float porcIEPS, Float impIEPS, Float impuestos, Float subtotal, Float ultimoCosto,
			Float utilidad, Float margen, Float precioPublico, Float porcDescuento,Float descuentoImporte) {
		super();
		this.precioVenta = precioVenta;
		this.porcIVA = porcIVA;
		this.impIVA = impIVA;
		this.porcIEPS = porcIEPS;
		this.impIEPS = impIEPS;
		this.impuestos = impuestos;
		this.subtotal = subtotal;
		this.ultimoCosto = ultimoCosto;
		this.utilidad = utilidad;
		this.margen = margen;
		this.precioPublico = precioPublico;
		this.porcDescuento = porcDescuento;
		this.descuentoImporte = descuentoImporte;
	}

	public Float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Float getPorcIVA() {
		return porcIVA;
	}

	public void setPorcIVA(Float porcIVA) {
		this.porcIVA = porcIVA;
	}

	public Float getImpIVA() {
		return impIVA;
	}

	public void setImpIVA(Float impIVA) {
		this.impIVA = impIVA;
	}

	public Float getPorcIEPS() {
		return porcIEPS;
	}

	public void setPorcIEPS(Float porcIEPS) {
		this.porcIEPS = porcIEPS;
	}

	public Float getImpIEPS() {
		return impIEPS;
	}

	public void setImpIEPS(Float impIEPS) {
		this.impIEPS = impIEPS;
	}

	public Float getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Float impuestos) {
		this.impuestos = impuestos;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getUltimoCosto() {
		return ultimoCosto;
	}

	public void setUltimoCosto(Float ultimoCosto) {
		this.ultimoCosto = ultimoCosto;
	}

	public Float getUtilidad() {
		return utilidad;
	}

	public void setUtilidad(Float utilidad) {
		this.utilidad = utilidad;
	}

	public Float getMargen() {
		return margen;
	}

	public void setMargen(Float margen) {
		this.margen = margen;
	}

	public Float getPrecioPublico() {
		return precioPublico;
	}

	public void setPrecioPublico(Float precioPublico) {
		this.precioPublico = precioPublico;
	}

	public Float getPorcDescuento() {
		return porcDescuento;
	}

	public void setPorcDescuento(Float porcDescuento) {
		this.porcDescuento = porcDescuento;
	}

	public Float getDescuentoImporte() {
		return descuentoImporte;
	}

	public void setDescuentoImporte(Float descuentoImporte) {
		this.descuentoImporte = descuentoImporte;
	}

	@Override
	public String toString() {
		return "Datos OUT -> [precioVenta=" + precioVenta + ", porcIVA=" + porcIVA + ", impIVA=" + impIVA + ", porcIEPS=" + porcIEPS + ", impIEPS=" + impIEPS + ", impuestos=" + impuestos + ", subtotal=" + subtotal + ", ultimoCosto=" + ultimoCosto+ ", utilidad=" + utilidad+ ", margen=" + margen+ ", precioPublico=" + precioPublico+ ", porcDescuento=" + porcDescuento+ ", descuentoImporte= "+descuentoImporte+"]";
	}
}
