package com.mx.api.globall.market.service;

import java.util.List;

import com.mx.api.globall.market.model.VentaMarketplace;

public interface IVentasMarketplaceService {
	
	VentaMarketplace findEstatusByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta);
	List<Object>findVentasByIdMarketplace(Integer idMarketplace);
	List<Object>findDetalleVentasByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta);

}
