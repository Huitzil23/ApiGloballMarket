package com.mx.api.globall.market.service;

//import java.io.ByteArrayInputStream;
//import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import com.mx.globall.ws.beans.Articulosaux;
import com.mx.api.globall.market.bean.CalculoPoliticaPrecioOUT;
//import com.mx.globall.ws.beans.ConsultaVentas;
//import com.mx.globall.ws.beans.ConsultaVentasTotales;
//import com.mx.globall.ws.beans.DetalleArticulosVtaAux;
//import com.mx.globall.ws.beans.ReporteDeliverysaux;
import com.mx.api.globall.market.bean.ResponseExecProc;
import com.mx.api.globall.market.bean.Ventaaux;
import com.mx.api.globall.market.model.Venta;

public interface IVentaService {
	Venta findById(Integer idVenta);
	ResponseExecProc registraVentaDetalle(Ventaaux venta, CalculoPoliticaPrecioOUT outPP);
	Venta findByIdVentaAndIdSucursal(Integer idVenta, Integer idSucursal);
	Venta save(Venta vta);
	ResponseExecProc generaVentaCobro(Integer IdVenta, Integer IdSucursal, Integer UsuarioCreacio);
	public Page<Object>getVentasPorFechas(Integer idSucursal,Date startFecha,Date endFecha, Pageable pageable);
	//public Page<Object>getVentasPorTicket(Integer idSucursal,Integer idTicket, Pageable pageable);
	public Page<Object>getVentasPorCliente(Integer idSucursal,Integer idCliente, Pageable pageable);
	public Page<Object>getVentasPorUsuario(Integer idSucursal,Integer usuarioCreacion, Pageable pageable);
	//public List<ConsultaVentas> parseConsultaVentas(List<Object> objConsulta)throws ParseException;
	//public ByteArrayInputStream generaExcelVentas(Integer idSucursal, Integer numTicket,Integer numCte, Integer numVend, String del,String al) throws ParseException;
	//public ConsultaVentasTotales getTotales(Integer idSucursal, Integer numTicket,Integer numCte, Integer numVend, String del,String al) throws ParseException;
	//ResponseExecProc generaCancelacionVenta(Integer IdVenta,Integer IdTerminal, Integer IdSucursal, Integer UsuarioCreacio);
	//Page<Object> getVentasDeliveryPorFechas(Integer idSucursal, Date del, Date al, List<Integer> idsCliente, Pageable page) throws ParseException;
	//List<ReporteDeliverysaux> parseConsultaVentasDeliverys(List<Object> objConsulta) throws ParseException;
	//ByteArrayInputStream generaExcelVentasDeliverys(Integer idSucursal, String del, String al, List<Integer> idsCliente) throws ParseException;
	List<Venta> findByVentaId_IdSucursalAndVentaId_IdVentaIn(Integer idSucursal, List<Integer> idVenta);
	//List<DetalleArticulosVtaAux> getDatosDetalleVentaMedikit(Integer IdVenta, Integer IdSucursal) throws ParseException;
	void deleteVentaByIdVentaAndIdSucursal(Integer IdVenta, Integer IdSucursal);
	//ResponseExecProc actualizaPrecioArticulo(Articulosaux articulo, Integer idSucursal, Integer idComercio);
	//public ResponseMessage registraVentaDetalleMarketPlace(ArticulosVentaRequest articulosVentaRequest) throws IOException;
	
}
