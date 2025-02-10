package com.mx.api.globall.market.service;

import com.mx.api.globall.market.model.VentaMarketplace;

public interface IVentasMarketplaceService {
	
	VentaMarketplace findEstatusByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta);	

}
