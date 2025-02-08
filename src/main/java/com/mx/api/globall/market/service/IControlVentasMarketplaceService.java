package com.mx.api.globall.market.service;

import java.util.Date;
import java.util.List;

import com.mx.api.globall.market.model.VentaMarketplace;


public interface IControlVentasMarketplaceService {

	List<Object> findVentasByIdSucursalAndIdVentaAndEstatus(Integer idSucursal, Integer idVenta, Integer estatus);

	//List<ArticulosVentaCareBoxAux> getArticulosVentaCareBox(Integer idSucursal, Integer idVenta, Integer estatus);

	VentaMarketplace save(VentaMarketplace ventaCareBox);

	Object findClienteByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta);

	//ClienteCareBoxVenta getClienteVentaCareBox(Integer idSucursal, Integer idVenta);

	// rcf
	List<VentaMarketplace> findByIdSucursalAndEstatus(Integer IdSucursal, Integer estatus);

	List<VentaMarketplace> findByIdSucursal(Integer IdSucursal);

	VentaMarketplace findByIdSucursalAndIdVenta(Integer IdSucursal, Integer idVenta);

	List<VentaMarketplace> findAll();
	
	List<VentaMarketplace> findAllByFechaCreacionBetweenOrderByFechaCreacionDesc(Date startDate, Date endDate);
	List<VentaMarketplace> findAllByIdSucursalAndEstatusAndFechaCreacionBetweenOrderByFechaCreacionDesc(Integer idSucursal, Integer estatus, Date startDate, Date endDate);
		//
}
