package com.mx.api.globall.market.service;

import java.io.IOException;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mx.api.globall.market.bean.ArticulosVentaAuxIn;
import com.mx.api.globall.market.bean.ArticulosVentaRequest;
import com.mx.api.globall.market.bean.CalculoPoliticaPrecioIN;
//import com.mx.globall.ws.beans.Articulosaux;
import com.mx.api.globall.market.bean.CalculoPoliticaPrecioOUT;
//import com.mx.globall.ws.beans.ConsultaVentas;
//import com.mx.globall.ws.beans.ConsultaVentasTotales;
//import com.mx.globall.ws.beans.DetalleArticulosVtaAux;
//import com.mx.globall.ws.beans.ReporteDeliverysaux;
import com.mx.api.globall.market.bean.ResponseExecProc;
import com.mx.api.globall.market.bean.ResponseMessage;
import com.mx.api.globall.market.bean.Ventaaux;
import com.mx.api.globall.market.model.Articulos;
import com.mx.api.globall.market.model.DetalleVentaMarketplace;
import com.mx.api.globall.market.model.Venta;
import com.mx.api.globall.market.model.VentaId;
import com.mx.api.globall.market.model.VentaMarketplace;
import com.mx.api.globall.market.repository.IVentaRepository;
import com.mx.api.globall.market.utils.EstatusMarketplace;


@Service
public class VentaServiceImpl implements IVentaService{
	
	private static final Logger logger = LoggerFactory.getLogger(VentaServiceImpl.class);

	@Autowired
	IVentaRepository ventaRepo;
	
	@Autowired
	IArticulosService articulosService;
	
	@Autowired
	ISseNotifyService iSseNotifyService;
	
	@Autowired
	private IControlVentasMarketplaceService marketplaceService;
	
	@Autowired
	IDetalleVentasMarketplaceService detVtaMarketplaceService;
		
	@Override
	public Venta findById(Integer idVenta) {
		/*Optional<Venta> objResp =  ventaRepo.findById(idVenta);
		if(objResp.isPresent())
			return objResp.get();
		else*/
			return null;
	}
	
	@Override
	public Venta findByIdVentaAndIdSucursal(Integer idVenta, Integer idSucursal) {
		//Optional<Venta> objResp =  ventaRepo.findByIdVentaAndIdSucursal(idVenta, idSucursal);
		Optional<Venta> objResp =  ventaRepo.findById(new VentaId(idVenta, idSucursal));
		if(objResp.isPresent())
			return objResp.get();
		else
			return null;
	}
	
	/**
	 * Metodo para registrar el detalle de la venta
	 */	
	public ResponseMessage registraVentaDetalleMarketPlace(ArticulosVentaRequest articulosVentaRequest) throws IOException{
		
		ArticulosVentaAuxIn[] lstArt;
		String jsonArt =  new Gson().toJson(articulosVentaRequest.getArticulos());
		lstArt = new Gson().fromJson(jsonArt, ArticulosVentaAuxIn[].class);
		int numArt = 0;
		int idVenta = 0;
		//int idSucursal = 0;
		
		for(ArticulosVentaAuxIn artIn :  lstArt) {
			numArt++;
			logger.info(jsonArt);
			logger.info("IdSucursal  = " + articulosVentaRequest.getIdSucursal());
			logger.info("cantidad  = " + artIn.getCantidad());
			logger.info("Clave  = " + artIn.getSku());
			
			Ventaaux venta = new Ventaaux();
			venta.setIdSucursal(articulosVentaRequest.getIdSucursal());
			venta.setClaveArticulo(artIn.getSku());
			venta.setCantidad(artIn.getCantidad());			
			venta.setUsuarioCreacion(0);
			Articulos objArt = articulosService.consultaArticulo(artIn.getSku(),articulosVentaRequest.getIdSucursal());
			
			//venta.setSubTotal(null)
			venta.setUtilidad(objArt.getUtilidad().floatValue());
			venta.setPorcentaIVA(objArt.getPorcentaIVA().floatValue());
			venta.setPorcentajeIEPS(objArt.getPorcentajeIEPS().floatValue());
			venta.setPrecioVenta(objArt.getPrecioVenta().floatValue());
			venta.setCosto(objArt.getCosto().floatValue());
			venta.setPrecioPublico(objArt.getPrecioPublico().floatValue());
			venta.setMargen(objArt.getMargen().floatValue());
			venta.setPorcentajeDescuento(objArt.getPorcDescuento().floatValue());
			
			Float utilidad =  objArt.getUtilidad().floatValue();
	    	Float porcentajeIva = objArt.getPorcentaIVA().floatValue();
	    	Float porcentajeIEPS = objArt.getPorcentajeIEPS().floatValue();
	    	Float precioVenta = objArt.getPrecioVenta().floatValue();
	    	Float costo = objArt.getCosto().floatValue();
	    	Float precioPublico = objArt.getPrecioPublico().floatValue();
	    	Float margen = objArt.getMargen().floatValue();
	    	Float porcentajeDescuento = objArt.getPorcDescuento().floatValue();
	    	CalculoPoliticaPrecioIN datos = new CalculoPoliticaPrecioIN(utilidad, porcentajeIva, porcentajeIEPS, precioVenta, costo, precioPublico, margen, porcentajeDescuento, false);
	    	String respPP = articulosService.calculaPoliticaPrecio(datos, objArt.getIdPoliticaPrecio());
	    	ObjectMapper mapper = new ObjectMapper();
	    	CalculoPoliticaPrecioOUT outPP = mapper.readValue(respPP, CalculoPoliticaPrecioOUT.class);
	    	//int valCte = venta.getIdCliente();
	    	int valCte = 1;
	    	venta.setIsDeliv(valCte == 2 ? 1 : valCte == 3 ? 1 : valCte == 4 ? 1 : valCte == 5 ? 1 : valCte == 6 ? 1 : 0);
	    	venta.setIdCliente(valCte);
	    	venta.setIdTerminal(1);
	    	venta.setIdCorteCaja(1);
	    	
			ResponseExecProc response = new ResponseExecProc();
			if(numArt == 1) {
				response = registraVentaDetalle(venta, outPP);
				idVenta = response.getRespuesta();
				venta.setIdVenta(idVenta);
			}else {
				venta.setIdVenta(idVenta);
				response = registraVentaDetalle(venta, outPP);
			}			
	    	
		}
		
		//Registro de estatus y Envio de notificación
		iSseNotifyService.sendNotify(String.valueOf(articulosVentaRequest.getIdSucursal()), "Nueva orden de venta");
		VentaMarketplace ventaMarket = new VentaMarketplace();
		ventaMarket.setIdVenta(idVenta);
		ventaMarket.setIdMarketplace(1);
		ventaMarket.setIdSucursal(articulosVentaRequest.getIdSucursal());
		ventaMarket.setIdCliente(0);
		ventaMarket.setEstatus(EstatusMarketplace.Recibido.getValue());
		ventaMarket.setUsuarioCreacion("0");
		ventaMarket.setFechaCreacion(new Date());    	
		@SuppressWarnings("unused")
		VentaMarketplace resSaveCareBox =  marketplaceService.save(ventaMarket);
		
		DetalleVentaMarketplace detVta = new DetalleVentaMarketplace();
		detVta.setIdMarketplace(1);
		detVta.setIdVentaMarket(resSaveCareBox.getIdVentaMarket());		
    	detVta.setEstatus(EstatusMarketplace.Recibido.getValue());
    	detVta.setFechaCreacion(new Date());
    	detVta.setUsuarioCreacion("0");
    	detVtaMarketplaceService.save(detVta);
		
		
		ResponseMessage respuesta = new ResponseMessage(1,"La orden se ha registrado correctamente", idVenta);
		
		return respuesta;
	}

	@Override
	public ResponseExecProc registraVentaDetalle(Ventaaux venta, CalculoPoliticaPrecioOUT outPP) {
		ResponseExecProc response = new ResponseExecProc();
		try {
			Locale locale = new Locale("mx", "MX");
			DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
			Date fecha1 = new Date();
			String date1 = dateFormat.format(fecha1);
			logger.info("Inicio Ejecucion sp registraVentaDetalle: "+ date1);
			int responseSP = ventaRepo.registraVentaDetalle(
					venta.getIdVenta(),
					venta.getClaveArticulo(),
					venta.getCantidad(),
					outPP.getImpIVA(),
					outPP.getImpIEPS(),
					outPP.getDescuentoImporte(),
					venta.getIdTerminal(),
					venta.getIdCorteCaja(),
					venta.getIdSucursal(),
					venta.getIdCliente(),
					venta.getUsuarioCreacion(),
					venta.getIsDeliv());
			response.setRespuesta(responseSP);
			Date fecha2 = new Date();
			String date2 = dateFormat.format(fecha2);
			logger.info("Fin : "+ date2);
			long diferencia=fecha2.getTime()-fecha1.getTime();
			long segundos = TimeUnit.MILLISECONDS.toSeconds(diferencia); 
			logger.info("Diferencia ejecucion sp registraVentaDetalle: "+ segundos+" seg.");
			return response;
		} catch (Exception e) {
			if (e.getCause().getCause() instanceof SQLException) {
	            SQLException es = (SQLException) e.getCause().getCause();
	            return new ResponseExecProc(-1, es.getMessage());
	        }else {
	        	return new ResponseExecProc(-1, "Error en proceso de ejecución de registro venta");
	        }
		}
	}

	@Override
	public Venta save(Venta vta) {
		return ventaRepo.save(vta);
	}

	@Override
	public ResponseExecProc generaVentaCobro(Integer IdVenta, Integer IdSucursal, Integer UsuarioCreacio) {
		ResponseExecProc response = new ResponseExecProc();
		try {
			int responseSP = ventaRepo.generaVentaCobro(IdVenta, IdSucursal, UsuarioCreacio);
			response.setRespuesta(responseSP);
			return response;
		} catch (Exception e) {
			if (e.getCause().getCause() instanceof SQLException) {
	            SQLException es = (SQLException) e.getCause().getCause();
	            return new ResponseExecProc(-1, es.getMessage());
	        }else {
	        	return new ResponseExecProc(-1, "Error en proceso de ejecución de generacion venta");
	        }
		}
	}

	@Override
	public Page<Object> getVentasPorFechas(Integer idSucursal, Date startFecha, Date endFecha, Pageable pageable) {
		Page<Object> response = ventaRepo.getVentasPorFechas(idSucursal, startFecha, endFecha, pageable);
		return response;
	}

	/*@Override
	public Page<Object> getVentasPorTicket(Integer idSucursal, Integer idTicket, Pageable pageable) {
		Page<Object> response = ventaRepo.getVentasPorTicket(idSucursal, idTicket, pageable);
		return response;
	}*/

	@Override
	public Page<Object> getVentasPorCliente(Integer idSucursal, Integer idCliente, Pageable pageable) {
		Page<Object> response = ventaRepo.getVentasPorCliente(idSucursal, idCliente, pageable);
		return response;
	}

	@Override
	public Page<Object> getVentasPorUsuario(Integer idSucursal, Integer usuarioCreacion, Pageable pageable) {
		Page<Object> response = ventaRepo.getVentasPorUsuario(idSucursal, usuarioCreacion, pageable);
		return response;
	}
	
	/*@Override
	public List<ConsultaVentas> parseConsultaVentas(List<Object> objConsulta) throws ParseException {
		List<ConsultaVentas>listRespuesta = new ArrayList<ConsultaVentas>();
		for(Object object : objConsulta) {
			Object[] obj = (Object[])object;
			ConsultaVentas objVta = new  ConsultaVentas();
			objVta.setIdVenta((obj[0] != null ? (Integer)obj[0] : 0));
			objVta.setIdTerminal((obj[1] != null ? (Integer)obj[1] : 0));
			objVta.setNombreTerminal((obj[2] != null ? (String)obj[2] : ""));
			objVta.setFechaCreacion((obj[3] != null ? (Date) obj[3] : new Date()));
			objVta.setIdCliente((obj[4] != null ? (Integer)obj[4] : 0));
			objVta.setNombreCliente((obj[5] != null ? (String)obj[5] : ""));
			objVta.setCantidad((obj[6] != null ? (Integer)obj[6] : 0));
			objVta.setSubTotal((obj[7] != null ? (BigDecimal)obj[7] : new BigDecimal(0)));
			objVta.setDescuentoSImp((obj[8] != null ? (BigDecimal)obj[8] : new BigDecimal(0)));
			objVta.setDescuentoCImp((obj[9] != null ? (BigDecimal)obj[9] : new BigDecimal(0)));
			objVta.setSubTotalCDcto((obj[10] != null ? (BigDecimal)obj[10] : new BigDecimal(0)));
			objVta.setImporteIva((obj[11] != null ? (BigDecimal)obj[11] : new BigDecimal(0)));
			objVta.setImporteIeps((obj[12] != null ? (BigDecimal)obj[12] : new BigDecimal(0)));
			objVta.setImpuestos((obj[13] != null ? (BigDecimal)obj[13] : new BigDecimal(0)));
			objVta.setEstatus((obj[14] != null ? (byte)obj[14] : null));
			objVta.setDescripcionEstatus((obj[15] != null ? (String)obj[15] : ""));
			objVta.setUsuario((obj[16] != null ? (String)obj[16] : ""));
			objVta.setEnvioDomicilio((obj[17] != null ? (String)obj[17] : ""));
			objVta.setVentaTotal((obj[18] != null ? (BigDecimal)obj[18] : new BigDecimal(0)));
			listRespuesta.add(objVta);						
		}
		
		return listRespuesta;
	}*/

	/*@Override
	public ByteArrayInputStream generaExcelVentas(Integer idSucursal, Integer numTicket, Integer numCte, Integer numVend, String del,
			String al) throws ParseException {
		List<Object>lstConsultaObj = new ArrayList<Object>();
		List<ConsultaVentas>lstConsulta = new ArrayList<ConsultaVentas>();
		
		Date dDel = new Date();
    	Date dAl = new Date();
    	if(!del.isEmpty() && !al.isEmpty()) {
    		dDel = new SimpleDateFormat("dd/MM/yyyyy").parse(del);
    		dAl = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss").parse(al);
    		lstConsultaObj = ventaRepo.getVentasPorFechasXls(idSucursal, dDel, dAl);
    	}else if(numTicket > 0){
    		lstConsultaObj = ventaRepo.getVentasPorTicketXls(idSucursal, numTicket);
    	}else if(numCte > 0){
    		lstConsultaObj = ventaRepo.getVentasPorClienteXls(idSucursal, numCte);
		}else if(numVend > 0){
			lstConsultaObj = ventaRepo.getVentasPorUsuarioXls(idSucursal, numVend);
		}
    	lstConsulta = parseConsultaVentas(lstConsultaObj);   
		
    	ByteArrayInputStream lstRespuesta = getExcel(lstConsulta);
 
    	return lstRespuesta;
	}*/
	
	/*public ByteArrayInputStream getExcel(List<ConsultaVentas> lstConsulta) {
		String[] Headers = {"TICKET","TERMINAL","FECHA","CLIENTE","CANTIDAD","SUBTOTAL","DESCUENTO SIN IMPUESTOS","DESCUENTO CON IMPUESTOS","SUBTOTAL CON DESCUENTO","IVA","IEPS","IMPUESTOS","ESTATUS","VENDEDOR","ENVÍO A DOMICILIO","IMPORTE TOTAL"};
		
		try  (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
			
			Sheet sheet = workbook.createSheet("ConsultaVentas");

		      // Encabezados
		      Row headerRow = sheet.createRow(0);

		      for (int col = 0; col < Headers.length; col++) {
		        Cell cell = headerRow.createCell(col);
		        cell.setCellValue(Headers[col]);
		      }
		      
		      int rowIdx = 1;
		      for(ConsultaVentas vta :  lstConsulta) {
		    	  Row row = sheet.createRow(rowIdx++);
		    	  row.createCell(0).setCellValue(vta.getIdVenta());
		    	  row.createCell(1).setCellValue(vta.getNombreTerminal());
		    	  row.createCell(2).setCellValue(Utiles.StringDateToString(vta.getFechaCreacion(), "dd/MM/yyyy HH:mm:ss"));
		    	  row.createCell(3).setCellValue(vta.getNombreCliente());
		    	  row.createCell(4).setCellValue(vta.getCantidad());
		    	  row.createCell(5).setCellValue(""+vta.getSubTotal());
		    	  row.createCell(6).setCellValue(""+vta.getDescuentoSImp());
		    	  row.createCell(7).setCellValue(""+vta.getDescuentoCImp());
		    	  row.createCell(8).setCellValue(""+vta.getSubTotalCDcto());
		    	  row.createCell(9).setCellValue(""+vta.getImporteIva());
		    	  row.createCell(10).setCellValue(""+vta.getImporteIeps());
		    	  row.createCell(11).setCellValue(""+vta.getImpuestos());
		    	  row.createCell(12).setCellValue(""+vta.getDescripcionEstatus());
		    	  row.createCell(13).setCellValue(""+vta.getUsuario());
		    	  row.createCell(14).setCellValue(""+vta.getEnvioDomicilio());
		    	  row.createCell(15).setCellValue(""+vta.getVentaTotal());
		      }
			
			 workbook.write(out);
		     return new ByteArrayInputStream(out.toByteArray());
		}catch (IOException e) {
		      throw new RuntimeException("Ha ocurrido un error al generar el archivo excel: " + e.getMessage());
		    }
	}*/

	/*@Override
	public ConsultaVentasTotales getTotales(Integer idSucursal, Integer numTicket, Integer numCte, Integer numVend, String del,
			String al) throws ParseException {
		
		Object objResp = null;
		Date dDel = new Date();
    	Date dAl = new Date();
    	if(!del.isEmpty() && !al.isEmpty()) {
    		dDel = new SimpleDateFormat("dd/MM/yyyyy").parse(del);
    		dAl = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss").parse(al);
    		objResp = ventaRepo.getVentasPorFechasTotal(idSucursal, dDel, dAl);
    		
    	}else if(numTicket > 0){
    		objResp = ventaRepo.getVentasPorTicketTotal(idSucursal, numTicket);
    	}else if(numCte > 0){
    		objResp = ventaRepo.getVentasPorClienteTotal(idSucursal, numCte);
		}else if(numVend > 0){
			objResp = ventaRepo.getVentasPorUsuarioTotal(idSucursal, numVend);
		} 
    	ConsultaVentasTotales parseResp = parseConsultaVentasTotales(objResp);
		return parseResp;
	}*/

	/*public ConsultaVentasTotales parseConsultaVentasTotales(Object object) throws ParseException {
		ConsultaVentasTotales objResponse = new ConsultaVentasTotales();
		Object[] obj = (Object[])object;
		objResponse.setPiezas((obj[0] != null ? (Integer)obj[0] : 0));
		objResponse.setImporte((obj[1] != null ? (BigDecimal)obj[1] : new BigDecimal(0)));
		return objResponse;
	}*/

	/*@Override
	public ResponseExecProc generaCancelacionVenta(Integer IdVenta, Integer IdTerminal, Integer IdSucursal,
			Integer UsuarioCreacio) {
		ResponseExecProc response = new ResponseExecProc();
		try {
			int responseSP = ventaRepo.generaCancelacionVenta(IdVenta, IdTerminal, IdSucursal, UsuarioCreacio);
			response.setRespuesta(responseSP);
			return response;
		} catch (Exception e) {
			if (e.getCause().getCause() instanceof SQLException) {
	            SQLException es = (SQLException) e.getCause().getCause();
	            return new ResponseExecProc(-1, es.getMessage());
	        }else {
	        	return new ResponseExecProc(-1, "Error en proceso de ejecución de cancelación venta");
	        }
		}
	}*/
	
	/*@Override
	public Page<Object> getVentasDeliveryPorFechas(Integer idSucursal, Date del, Date al, List<Integer> idsCliente, Pageable page) throws ParseException {
		Page<Object> response = ventaRepo.getVentasDeliveryPorFechas(idSucursal, del, al, idsCliente, page);
		return response;
	}*/

	/*@Override
	public List<ReporteDeliverysaux> parseConsultaVentasDeliverys(List<Object> objConsulta) throws ParseException {
		List<ReporteDeliverysaux>listRespuesta = new ArrayList<ReporteDeliverysaux>();
		for(Object object : objConsulta) {
			Object[] obj = (Object[])object;
			ReporteDeliverysaux objVta = new  ReporteDeliverysaux();
			objVta.setNombreCliente((obj[0] != null ? (String)obj[0] : ""));
			objVta.setFechaCreacion((obj[1] != null ? (Date) obj[1] : new Date()));
			objVta.setIdVenta((obj[2] != null ? (Integer)obj[2] : 0));
			objVta.setClaveArticulo((obj[3] != null ? (String)obj[3] : ""));
			objVta.setNombre((obj[4] != null ? (String)obj[4] : ""));
			objVta.setCantidad((obj[5] != null ? (Integer)obj[5] : 0));
			objVta.setPorcIva((obj[6] != null ? (BigDecimal)obj[6] : new BigDecimal(0)));
			objVta.setPorcIeps((obj[7] != null ? (BigDecimal)obj[7] : new BigDecimal(0)));
			objVta.setTotalVenta((obj[8] != null ? (BigDecimal)obj[8] : new BigDecimal(0)));
			objVta.setCosto((obj[9] != null ? (BigDecimal)obj[9] : new BigDecimal(0)));
			objVta.setEstatus((obj[10] != null ? (String)obj[10] : ""));
			objVta.setTipoPago((obj[11] != null ? (String)obj[11] : ""));
			objVta.setIdCorteCaja((obj[12] != null ? (Integer)obj[12] : 0));
			objVta.setUsuario((obj[13] != null ? (String)obj[13] : ""));
			objVta.setReferencia((obj[14] != null ? (String)obj[14] : ""));
			listRespuesta.add(objVta);						
		}
		
		return listRespuesta;
	}*/
	
	/*@Override
	public ByteArrayInputStream generaExcelVentasDeliverys(Integer idSucursal, String del, String al, List<Integer> idsCliente) throws ParseException {
		List<Object> lstConsultaObj = new ArrayList<Object>();
		List<ReporteDeliverysaux> lstConsulta = new ArrayList<ReporteDeliverysaux>();
		
		Date dDel = new Date();
    	Date dAl = new Date();
    	if(!del.isEmpty() && !al.isEmpty()) {
    		dDel = new SimpleDateFormat("dd/MM/yyyyy").parse(del);
    		dAl = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss").parse(al);
    		lstConsultaObj = ventaRepo.getVentasDeliveryPorFechasXls(idSucursal, dDel, dAl, idsCliente);
    		lstConsulta = parseConsultaVentasDeliverys(lstConsultaObj);
    	}
    	  
    	ByteArrayInputStream lstRespuesta = getExcelDeliverys(lstConsulta);
 
    	return lstRespuesta;
	}*/
	
	/*public ByteArrayInputStream getExcelDeliverys(List<ReporteDeliverysaux> lstConsulta) {
		String[] Headers = {"CLIENTE","FECHA VENTA","TICKET","CLAVE ARTICULO","ARTICULO","CANTIDAD","% IVA","% IEPS","TOTAL VENTA","COSTO SIN IMP.","ESTATUS VENTA","TIPO PAGO","CORTE CAJA","USUARIO","REFERENCIA"};
		
		try  (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
			
			Sheet sheet = workbook.createSheet("VentasDeliverys");

		      // Encabezados
		      Row headerRow = sheet.createRow(0);

		      for (int col = 0; col < Headers.length; col++) {
		        Cell cell = headerRow.createCell(col);
		        cell.setCellValue(Headers[col]);
		      }
		      
		      int rowIdx = 1;
		      for(ReporteDeliverysaux vta :  lstConsulta) {
		    	  Row row = sheet.createRow(rowIdx++);
		    	  row.createCell(0).setCellValue(vta.getNombreCliente());
		    	  row.createCell(1).setCellValue(Utiles.StringDateToString(vta.getFechaCreacion(), "dd/MM/yyyy HH:mm:ss"));
		    	  row.createCell(2).setCellValue(vta.getIdVenta());
		    	  row.createCell(3).setCellValue(vta.getClaveArticulo());
		    	  row.createCell(4).setCellValue(vta.getNombre());
		    	  row.createCell(5).setCellValue(vta.getCantidad());
		    	  row.createCell(6).setCellValue(""+vta.getPorcIva());
		    	  row.createCell(7).setCellValue(""+vta.getPorcIeps());
		    	  row.createCell(8).setCellValue(""+vta.getTotalVenta());
		    	  row.createCell(9).setCellValue(""+vta.getCosto());
		    	  row.createCell(10).setCellValue(vta.getEstatus());
		    	  row.createCell(11).setCellValue(vta.getTipoPago());
		    	  row.createCell(12).setCellValue(vta.getIdCorteCaja());
		    	  row.createCell(13).setCellValue(vta.getUsuario());
		    	  row.createCell(14).setCellValue(vta.getReferencia());
		      }
			
			 workbook.write(out);
		     return new ByteArrayInputStream(out.toByteArray());
		}catch (IOException e) {
	      throw new RuntimeException("Ha ocurrido un error al generar el archivo excel: " + e.getMessage());
	    }
	}*/
	
	@Override
	public List<Venta> findByVentaId_IdSucursalAndVentaId_IdVentaIn(Integer idSucursal, List<Integer> idVenta ) {
		List<Venta> objResp =  ventaRepo.findByVentaId_IdSucursalAndVentaId_IdVentaIn(idSucursal,idVenta);
		return objResp;
	}

	/*@Override
	public List<DetalleArticulosVtaAux> getDatosDetalleVentaMedikit(Integer IdVenta, Integer IdSucursal) throws ParseException {
		List<Object> response = ventaRepo.getDatosDetalleVentaMedikit(IdVenta, IdSucursal);
		List<DetalleArticulosVtaAux> lstResponse = parseConsultaVentaMedikit(response);
		return lstResponse;
	}
	

	public List<DetalleArticulosVtaAux> parseConsultaVentaMedikit(List<Object> objConsulta) throws ParseException {
		List<DetalleArticulosVtaAux>listRespuesta = new ArrayList<DetalleArticulosVtaAux>();
		for(Object object : objConsulta) {
			Object[] obj = (Object[])object;
			DetalleArticulosVtaAux objVta = new  DetalleArticulosVtaAux();
			objVta.setIdVenta((obj[0] != null ? (Integer)obj[0] : 0));
			objVta.setIdSucursal((obj[1] != null ? (Integer)obj[1] : 0));
			objVta.setClaveArticulo((obj[2] != null ? (String)obj[2] : ""));
			objVta.setCantidad((obj[3] != null ? (Integer)obj[3] : 0));
			objVta.setPrecioVenta((obj[4] != null ? (BigDecimal)obj[4] : new BigDecimal(0)));
			objVta.setPrecioPublico((obj[5] != null ? (BigDecimal)obj[5] : new BigDecimal(0)));
			objVta.setConIva((obj[6] != null ? (Integer)obj[6] : 0));
			objVta.setTotalVenta((obj[7] != null ? (BigDecimal)obj[7] : new BigDecimal(0)));
			objVta.setIdVentaDetalle((obj[8] != null ? (Integer)obj[8] : 0));
			listRespuesta.add(objVta);						
		}
		
		return listRespuesta;
	}*/

	@Override
	@Transactional
	public void deleteVentaByIdVentaAndIdSucursal(Integer IdVenta, Integer IdSucursal) {
		ventaRepo.deleteVentaByIdVentaAndIdSucursal(IdVenta, IdSucursal);
	}

	

	/*@Override
	public ResponseExecProc actualizaPrecioArticulo(Articulosaux articulo, Integer idSucursal, Integer idComercio) {
		ResponseExecProc response = new ResponseExecProc();
		try {
			int responseSP = ventaRepo.actualizaPrecioArticulo(articulo.getClaveArticulo(), articulo.getPrecioPublico(), articulo.getPorcDescuento(),
					                                           articulo.getCosto(), articulo.getPrecioVenta(), articulo.getMargen(), articulo.getUtilidad(),
					                                           articulo.getPorcentaIVA(), articulo.getPorcentajeIEPS(), articulo.getIdPoliticaPrecio(),
					                                           idComercio, idSucursal, articulo.getUsuarioModificacion());
			response.setRespuesta(responseSP);
			response.setMessage("Ok");
			return response;
		} catch (Exception e) {
			if (e.getCause().getCause() instanceof SQLException) {
	            SQLException es = (SQLException) e.getCause().getCause();
	            return new ResponseExecProc(-1, es.getMessage());
	        }else {
	        	return new ResponseExecProc(-1, "Error en proceso de ejecución de actualizacion de precio");
	        }
		}
	}*/

}
