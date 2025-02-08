package com.mx.api.globall.market.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.api.globall.market.bean.ConsultaSucursalesProductosRequest;
import com.mx.api.globall.market.bean.ConsultaSucursalesProductosResponse;
import com.mx.api.globall.market.bean.OrdenCompraResponse;
import com.mx.api.globall.market.bean.ResponseMessage;
import com.mx.api.globall.market.bean.ArticulosVentaRequest;
import com.mx.api.globall.market.bean.BuscaSucursalCodigoPostalRequest;
import com.mx.api.globall.market.bean.BuscaSucursalCodigoPostalResponse;
import com.mx.api.globall.market.service.ICatalogoPlataformasMarketplaceService;
import com.mx.api.globall.market.service.ISucursalesService;
import com.mx.api.globall.market.service.IVentaService;
import com.mx.api.globall.market.service.VentaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/ws", produces="application/json;charset=UTF-8")
@Api(value = "Api Integracion Globall-MarketPlace ")
public class IntegracionGloballMarketController {
	private static final Logger log = LoggerFactory.getLogger(IntegracionGloballMarketController.class);
	
	//@Autowired
	//private ICatalogoPlataformasMarketplaceService plataformaMarketService;
	
	@Autowired
	private ISucursalesService sucursalesServices;
	
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	
	/*@PostMapping("/consultaSales")
	@ApiOperation(value="EndPoint Consulta de Sales", response = ConsultaSalesResponse.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Retorna el objeto ConsultaSales.")
	})	
	public ResponseEntity<?>consultaSales(){
		
		ConsultaSalesResponse resp = new ConsultaSalesResponse();
		
		return ResponseEntity.ok().body(resp);
	}*/
	
	/*@PostMapping("/buscaSucursalCodigoPostal")
	@ApiOperation(value = "EndPoint Busca Sucursales por Código Postal", response = BuscaSucursalCodigoPostalResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna objeto BuscaSucursalCodigoPostalResponse") })
	public ResponseEntity<?> buscaSucursalCodigoPostal(@ApiParam(value = "Código Postal",required = true) @Valid @RequestBody BuscaSucursalCodigoPostalRequest objCodigoPostal) {
		BuscaSucursalCodigoPostalResponse resp = new BuscaSucursalCodigoPostalResponse();
		String cp = objCodigoPostal.getCodigoPostal();
		resp = sucursalesServices.consultaSucursalesByCodigoPostalAndEstatus(cp, 1);
		return ResponseEntity.ok().body(resp);
	}*/
	
	@PostMapping("/buscaSucursalesProductosByCodigoPostal")
	@ApiOperation(value = "EndPoint Busca Sucursales con inventario por Código Postal", response = BuscaSucursalCodigoPostalResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna objeto BuscaSucursalCodigoPostalResponse") })
	public ResponseEntity<?> buscaSucursalesProductosByCodigoPostal(@ApiParam(value = "Datos de la busqueda",required = true) @Valid @RequestBody ConsultaSucursalesProductosRequest consultaSucursalesProductosRequest ) {
		List<ConsultaSucursalesProductosResponse> resp = new ArrayList<ConsultaSucursalesProductosResponse>();		
		resp = sucursalesServices.buscaIdSucursalesCercaCPDistancia(consultaSucursalesProductosRequest, 3.0f);
		return ResponseEntity.ok().body(resp);
	}
	
	@PostMapping("/ordenCompra")
	@ApiOperation(value="EndPoint Registra Orden de Compra", response = OrdenCompraResponse.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna el objeto OrdenCompraResponse.")})
	public ResponseEntity<?> ordenCompra(@ApiParam(value = "Datos de la Orden",required = true) @Valid @RequestBody ArticulosVentaRequest articulosVentaRequest) throws IOException {
		log.info("Execute Method Post---> Registra Venta Producto" + articulosVentaRequest);
				
		ResponseMessage resp = ventaServiceImpl.registraVentaDetalleMarketPlace(articulosVentaRequest);
			
		return ResponseEntity.ok().body(resp);
	}
	
	
}
