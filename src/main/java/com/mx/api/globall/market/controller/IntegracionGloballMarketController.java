package com.mx.api.globall.market.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.api.globall.market.bean.OrdenCompraRequest;
import com.mx.api.globall.market.bean.OrdenCompraResponse;
import com.mx.api.globall.market.service.ICatalogoPlataformasMarketplaceService;

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
	
	@Autowired
	ICatalogoPlataformasMarketplaceService plataformaMarketService;
	
	@PostMapping("/ordenCompra")
	@ApiOperation(value="EndPoint Orden de Compra", response = OrdenCompraResponse.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Retorna el objeto OrdenCompraResponse.")
	})
	public ResponseEntity<?> ordenCompra(@ApiParam(value = "Datos de la Orden",required = true) @Valid @RequestBody OrdenCompraRequest ordenCompraRequest) {
		log.info("/ordenCompra");
		/*
		 * AQUI TODA LA LOGICA
		 */
		OrdenCompraResponse resp = new OrdenCompraResponse("ok", "100", "1");
		return ResponseEntity.ok().body(resp);
	}
}
