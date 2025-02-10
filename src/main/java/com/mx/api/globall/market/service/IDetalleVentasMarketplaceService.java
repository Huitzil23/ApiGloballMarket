package com.mx.api.globall.market.service;

import com.mx.api.globall.market.model.DetalleVentaMarketplace;

public interface IDetalleVentasMarketplaceService {

	DetalleVentaMarketplace save(DetalleVentaMarketplace detVentaMarketplaceBox);
	DetalleVentaMarketplace findByIdVentaMarketAndEstatus(Integer idVentaMarketplace, Integer estatus);	
	
}
