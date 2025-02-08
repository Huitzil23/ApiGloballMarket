package com.mx.api.globall.market.utils;

import com.mx.api.globall.market.bean.CalculoPoliticaPrecioIN;
import com.mx.api.globall.market.bean.CalculoPoliticaPrecioOUT;

public class CalculaPoliticaPrecios {

	//public static void main(String[] args) {
		//Utilidad
		/*CalculoPoliticaPrecioIN data = new CalculoPoliticaPrecioIN();
		data.setPrecioPublico((float)(120.50));
		data.setPorcentajeIVA((float)(16));
		data.setPorcentajeIEPS((float)(0));
		data.setCosto((float)(50));
		data.setUtilidad((float)(35));
		data.setPorcentajeDescuento((float)(10));
		CalculoPoliticaPrecioOUT resultado =  Utilidad(data);
		System.out.println(resultado);*/
		//Margen
		/*CalculoPoliticaPrecioIN data = new CalculoPoliticaPrecioIN();
		data.setPrecioPublico((float)(100));
		data.setPorcentajeIVA((float)(16));
		data.setPorcentajeIEPS((float)(8));
		data.setCosto((float)(50));
		data.setUtilidad((float)(0));
		data.setMargen((float)(30));
		data.setPorcentajeDescuento((float)(10));
		CalculoPoliticaPrecioOUT resultado =  Margen(data);
		System.out.println(resultado);*/
		//Fijo
		/*CalculoPoliticaPrecioIN data = new CalculoPoliticaPrecioIN();
		data.setPrecioVenta((float)(80));
		data.setPrecioPublico((float)(100));
		data.setPorcentajeIVA((float)(16));
		data.setPorcentajeIEPS((float)(8));
		data.setCosto((float)(50));
		data.setUtilidad((float)(0));
		data.setMargen((float)(0));
		data.setPorcentajeDescuento((float)(0));
		CalculoPoliticaPrecioOUT resultado =  Fijo(data);
		System.out.println(resultado);*/
		//Descuento
		/*CalculoPoliticaPrecioIN data = new CalculoPoliticaPrecioIN();
		data.setPrecioVenta((float) 0);
		data.setPrecioPublico((float)(100));
		data.setPorcentajeIVA((float)(0));
		data.setPorcentajeIEPS((float)(8));
		data.setCosto((float)(20));
		data.setUtilidad((float)(0));
		data.setMargen((float)(0));
		data.setPorcentajeDescuento((float)(50));
		CalculoPoliticaPrecioOUT resultado =  Descuento(data);
		System.out.println(resultado);*/
	//}
	
	public static CalculoPoliticaPrecioOUT Utilidad(CalculoPoliticaPrecioIN data) {
		inicializaValoresEntrada(data);
		System.out.println(data.toString());
		Float dPrecioVenta	 = data.getPrecioVenta();
		Float dPrecioPublico	= data.getPrecioPublico();
		Float dPorIVA	= data.getPorcentajeIVA();
		Float dIVA	 = (float)(0);
		Float dPorIEPS	= data.getPorcentajeIEPS();
		Float dIEPS = (float)(0);
		Float dImpuestos = (float)(0);
		Float dSubtotal = (float)(0);
		Float dUltimoCosto	= data.getCosto();
		Float dUtilidad = data.getUtilidad();
		Float dMargen = data.getMargen();		 
		Float dDescuento = data.getPorcentajeDescuento();
		Float dTempPrecioVent = (float)(0);
		Float dDescuentoImporte = (float)(0);
		
		dSubtotal = dUltimoCosto + dUtilidad;
		dIEPS = dSubtotal * (dPorIEPS / 100);
		dTempPrecioVent = dSubtotal + dIEPS;
		dIVA = (dTempPrecioVent * (dPorIVA / 100));
		dImpuestos = dIVA + dIEPS;
		dMargen = ((dUtilidad / dUltimoCosto) * 100);
		dPrecioVenta = dSubtotal + dImpuestos;

		if (dPrecioVenta < dPrecioPublico){
			dDescuento = 100 - ((dPrecioVenta / dPrecioPublico) * 100);
		}else
			dDescuento = (float)(0);
		
		dDescuentoImporte = (dDescuento / 100) * dPrecioPublico;
		
		//Redondeos
		dPrecioVenta = (float) (Math.round(dPrecioVenta * 100d) / 100d);
		dPorIVA = (float) (Math.round(dPorIVA * 100d) / 100d);
		dIVA = (float) (Math.round(dIVA * 100d) / 100d);
		dPorIEPS = (float) (Math.round(dPorIEPS * 100d) / 100d);
		dIEPS = (float) (Math.round(dIEPS * 100d) / 100d);
		dImpuestos = (float) (Math.round(dImpuestos * 100d) / 100d);
		dSubtotal = (float) (Math.round(dSubtotal * 100d) / 100d);
		dUltimoCosto = (float) (Math.round(dUltimoCosto * 100d) / 100d);
		dUtilidad = (float) (Math.round(dUtilidad * 100d) / 100d);
		dMargen = (float) (Math.round(dMargen * 100d) / 100d);
		dPrecioPublico = (float) (Math.round(dPrecioPublico * 100d) / 100d);
		dDescuento = (float) (Math.round(dDescuento * 100d) / 100d);
		dDescuentoImporte = (float) (Math.round(dDescuentoImporte * 100d) / 100d);
		
		return new CalculoPoliticaPrecioOUT(dPrecioVenta, dPorIVA, dIVA, dPorIEPS, dIEPS, dImpuestos, dSubtotal, dUltimoCosto, dUtilidad, dMargen, dPrecioPublico, dDescuento,dDescuentoImporte);
	}
	
	public static CalculoPoliticaPrecioOUT Margen(CalculoPoliticaPrecioIN data) {
		inicializaValoresEntrada(data);
		System.out.println(data.toString());
		Float dPrecioVenta	 = data.getPrecioVenta();
		Float dPrecioPublico	= data.getPrecioPublico();
		Float dPorIVA	= data.getPorcentajeIVA();
		Float dIVA	 = (float)(0);
		Float dPorIEPS	= data.getPorcentajeIEPS();
		Float dIEPS = (float)(0);
		Float dImpuestos = (float)(0);
		Float dSubtotal = (float)(0);
		Float dUltimoCosto	= data.getCosto();
		Float dUtilidad = data.getUtilidad();
		Float dMargen = data.getMargen();		 
		Float dDescuento = data.getPorcentajeDescuento();
		Float dTempPrecioVent = (float)(0);
		Float dDescuentoImporte = (float)(0);
		
		dSubtotal = dUltimoCosto * (1 + (dMargen / 100));
		dIEPS = dSubtotal * ((dPorIEPS / 100));
		dTempPrecioVent = dSubtotal + dIEPS;
		dIVA = (dTempPrecioVent * (dPorIVA / 100));
		dImpuestos = dIVA + dIEPS;
		dUtilidad = dSubtotal - dUltimoCosto;
		dPrecioVenta = dSubtotal + dImpuestos;
		if (dPrecioVenta < dPrecioPublico)
			dDescuento = 100 - ((dPrecioVenta / dPrecioPublico) * 100);
		else
			dDescuento = (float)(0);
		
		dDescuentoImporte = (dDescuento / 100) * dPrecioPublico;
		
		//Redondeos
		dPrecioVenta = (float) (Math.round(dPrecioVenta * 100d) / 100d);
		dPorIVA = (float) (Math.round(dPorIVA * 100d) / 100d);
		dIVA = (float) (Math.round(dIVA * 100d) / 100d);
		dPorIEPS = (float) (Math.round(dPorIEPS * 100d) / 100d);
		dIEPS = (float) (Math.round(dIEPS * 100d) / 100d);
		dImpuestos = (float) (Math.round(dImpuestos * 100d) / 100d);
		dSubtotal = (float) (Math.round(dSubtotal * 100d) / 100d);
		dUltimoCosto = (float) (Math.round(dUltimoCosto * 100d) / 100d);
		dUtilidad = (float) (Math.round(dUtilidad * 100d) / 100d);
		dMargen = (float) (Math.round(dMargen * 100d) / 100d);
		dPrecioPublico = (float) (Math.round(dPrecioPublico * 100d) / 100d);
		dDescuento = (float) (Math.round(dDescuento * 100d) / 100d);
		dDescuentoImporte = (float) (Math.round(dDescuentoImporte * 100d) / 100d);
		
		return new CalculoPoliticaPrecioOUT(dPrecioVenta, dPorIVA, dIVA, dPorIEPS, dIEPS, dImpuestos, dSubtotal, dUltimoCosto, dUtilidad, dMargen, dPrecioPublico, dDescuento, dDescuentoImporte);
	}
	
	public static CalculoPoliticaPrecioOUT Fijo(CalculoPoliticaPrecioIN data) {
		inicializaValoresEntrada(data);
		System.out.println(data.toString());
		Float dPrecioVenta	 = data.getPrecioVenta();
		Float dPrecioPublico	= data.getPrecioPublico();
		Float dPorIVA	= data.getPorcentajeIVA();
		Float dIVA	 = (float)(0);
		Float dPorIEPS	= data.getPorcentajeIEPS();
		Float dIEPS = (float)(0);
		Float dImpuestos = (float)(0);
		Float dSubtotal = (float)(0);
		Float dUltimoCosto	= data.getCosto();
		Float dUtilidad = data.getUtilidad();
		Float dMargen = data.getMargen();		 
		Float dDescuento = data.getPorcentajeDescuento();
		Float dTempPrecioVent = (float)(0);
		Float dDescuentoImporte = (float)(0);
		
		 dIVA = dPrecioVenta - (dPrecioVenta / ((dPorIVA / 100) + 1));
         dTempPrecioVent = dPrecioVenta - dIVA;
         dIEPS = dTempPrecioVent - (dTempPrecioVent / ((dPorIEPS / 100) + 1));
         dImpuestos = dIVA + dIEPS;
         dSubtotal = dPrecioVenta - dImpuestos;
         dUtilidad = dSubtotal - dUltimoCosto;
	       if (dUltimoCosto == 0)
	           //Cuando el Costo sea Cero, utilizamos esta formula para obtener la rentabilidad del margen
	           if (dSubtotal == 0)
	                dMargen = (float)(0);
	           else
	                dMargen = ((dSubtotal - dUltimoCosto) / dSubtotal) * 100;
	       else
	            dMargen = ((dSubtotal - dUltimoCosto) * 100) / dUltimoCosto;
	
	       if (dPrecioVenta < dPrecioPublico)
	            dDescuento = ((dPrecioPublico - dPrecioVenta) * 100) / dPrecioPublico;
	       else
	            dDescuento = (float)(0);

	       dDescuentoImporte = (dDescuento / 100) * dPrecioPublico;
	       
		//Redondeos
		dPrecioVenta = (float) (Math.round(dPrecioVenta * 100d) / 100d);
		dPorIVA = (float) (Math.round(dPorIVA * 100d) / 100d);
		dIVA = (float) (Math.round(dIVA * 100d) / 100d);
		dPorIEPS = (float) (Math.round(dPorIEPS * 100d) / 100d);
		dIEPS = (float) (Math.round(dIEPS * 100d) / 100d);
		dImpuestos = (float) (Math.round(dImpuestos * 100d) / 100d);
		dSubtotal = (float) (Math.round(dSubtotal * 100d) / 100d);
		dUltimoCosto = (float) (Math.round(dUltimoCosto * 100d) / 100d);
		dUtilidad = (float) (Math.round(dUtilidad * 100d) / 100d);
		dMargen = (float) (Math.round(dMargen * 100d) / 100d);
		dPrecioPublico = (float) (Math.round(dPrecioPublico * 100d) / 100d);
		dDescuento = (float) (Math.round(dDescuento * 100d) / 100d);
		dDescuentoImporte = (float) (Math.round(dDescuentoImporte * 100d) / 100d);
		
		return new CalculoPoliticaPrecioOUT(dPrecioVenta, dPorIVA, dIVA, dPorIEPS, dIEPS, dImpuestos, dSubtotal, dUltimoCosto, dUtilidad, dMargen, dPrecioPublico, dDescuento, dDescuentoImporte);
	}
	
	public static CalculoPoliticaPrecioOUT Descuento(CalculoPoliticaPrecioIN data) {
		inicializaValoresEntrada(data);
		System.out.println(data.toString());
		Float dPrecioVenta	 = data.getPrecioVenta();
		Float dPrecioPublico	= data.getPrecioPublico();
		Float dPorIVA	= data.getPorcentajeIVA();
		Float dIVA	 = (float)(0);
		Float dPorIEPS	= data.getPorcentajeIEPS();
		Float dIEPS = (float)(0);
		Float dImpuestos = (float)(0);
		Float dSubtotal = (float)(0);
		Float dUltimoCosto	= data.getCosto();
		Float dUtilidad = data.getUtilidad();
		Float dMargen = data.getMargen();		 
		Float dDescuento = data.getPorcentajeDescuento();
		Float dTempPrecioVent = (float)(0);
		Float dDescuentoImporte = (float)(0);
		
		dPrecioVenta = dPrecioPublico - (dPrecioPublico * (dDescuento / 100));
        dIVA = dPrecioVenta - (dPrecioVenta / ((dPorIVA / 100) + 1));
        dTempPrecioVent = dPrecioVenta - dIVA;
        dIEPS = dTempPrecioVent - (dTempPrecioVent / ((dPorIEPS / 100) + 1));
        dImpuestos =dIVA + dIEPS;
        dSubtotal = dPrecioVenta - dImpuestos;
        dUtilidad = dSubtotal - dUltimoCosto;

        if(dUltimoCosto == 0)
             //Cuando el Costo sea Cero, utilizamos esta formula para obtener la rentabilidad del margen
            if (dSubtotal == 0)
                dMargen = (float)(0);
            else
                dMargen = ((dSubtotal - dUltimoCosto) / dSubtotal) * 100;
        else
            dMargen = ((dUtilidad / dUltimoCosto) * 100);

        dDescuentoImporte = (dDescuento / 100) * dPrecioPublico;
        
		//Redondeos
		dPrecioVenta = (float) (Math.round(dPrecioVenta * 100d) / 100d);
		dPorIVA = (float) (Math.round(dPorIVA * 100d) / 100d);
		dIVA = (float) (Math.round(dIVA * 100d) / 100d);
		dPorIEPS = (float) (Math.round(dPorIEPS * 100d) / 100d);
		dIEPS = (float) (Math.round(dIEPS * 100d) / 100d);
		dImpuestos = (float) (Math.round(dImpuestos * 100d) / 100d);
		dSubtotal = (float) (Math.round(dSubtotal * 100d) / 100d);
		dUltimoCosto = (float) (Math.round(dUltimoCosto * 100d) / 100d);
		dUtilidad = (float) (Math.round(dUtilidad * 100d) / 100d);
		dMargen = (float) (Math.round(dMargen * 100d) / 100d);
		dPrecioPublico = (float) (Math.round(dPrecioPublico * 100d) / 100d);
		dDescuento = (float) (Math.round(dDescuento * 100d) / 100d);
		dDescuentoImporte = (float) (Math.round(dDescuentoImporte * 100d) / 100d);
		
		return new CalculoPoliticaPrecioOUT(dPrecioVenta, dPorIVA, dIVA, dPorIEPS, dIEPS, dImpuestos, dSubtotal, dUltimoCosto, dUtilidad, dMargen, dPrecioPublico, dDescuento, dDescuentoImporte);
	}
	
	public static CalculoPoliticaPrecioIN inicializaValoresEntrada(CalculoPoliticaPrecioIN data) {
		data.setUtilidad(data.getUtilidad() == null ? (float)(0) : data.getUtilidad());
		data.setPorcentajeIVA(data.getPorcentajeIVA() == null ? (float)(0) : data.getPorcentajeIVA());
		data.setPorcentajeIEPS(data.getPorcentajeIEPS() == null ? (float)(0) : data.getPorcentajeIEPS());
		data.setPrecioVenta(data.getPrecioVenta() == null ? (float)(0) : data.getPrecioVenta());
		data.setCosto(data.getCosto() == null ? (float)(0) : data.getCosto());
		data.setPrecioPublico(data.getPrecioPublico() == null ? (float)(0) : data.getPrecioPublico());
		data.setMargen(data.getMargen() == null ? (float)(0) : data.getMargen());
		data.setPorcentajeDescuento(data.getPorcentajeDescuento() == null ? (float)(0) : data.getPorcentajeDescuento());
		return data;
	}
	public static CalculoPoliticaPrecioOUT inicializaValoresSalida(CalculoPoliticaPrecioOUT data) {
		data.setPrecioVenta(data.getPrecioVenta() == null ? (float)(0) : data.getPrecioVenta());
		data.setPorcIVA(data.getPorcIVA() == null ? (float)(0) : data.getPorcIVA());
		data.setImpIVA(data.getImpIVA() == null ? (float)(0) : data.getImpIVA());
		data.setPorcIEPS(data.getPorcIEPS() == null ? (float)(0) : data.getPorcIEPS());
		data.setImpIEPS(data.getImpIEPS() == null ? (float)(0) : data.getImpIEPS());
		data.setImpuestos(data.getImpuestos() == null ? (float)(0) : data.getImpuestos());
		data.setSubtotal(data.getSubtotal() == null ? (float)(0) : data.getSubtotal());
		data.setUltimoCosto(data.getUltimoCosto() == null ? (float)(0) : data.getUltimoCosto());
		data.setUtilidad(data.getUtilidad() == null ? (float)(0) : data.getUtilidad());
		data.setMargen(data.getMargen() == null ? (float)(0) : data.getMargen());
		data.setPrecioPublico(data.getPrecioPublico() == null ? (float)(0) : data.getPrecioPublico());
		data.setPorcDescuento(data.getPorcDescuento() == null ? (float)(0) : data.getPorcDescuento());
		data.setDescuentoImporte(data.getDescuentoImporte() == null ? (float)(0) : data.getDescuentoImporte());
		return data;
	}
}
