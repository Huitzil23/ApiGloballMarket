package com.mx.api.globall.market.service;

import java.util.List;

import com.mx.api.globall.market.bean.BuscaSucursalCodigoPostalResponse;
import com.mx.api.globall.market.bean.ConsultaSucursalesProductosRequest;
import com.mx.api.globall.market.bean.ConsultaSucursalesProductosResponse;
import com.mx.api.globall.market.bean.SucursalesAux;
import com.mx.api.globall.market.model.Sucursales;

public interface ISucursalesService {

	List<Sucursales> findByCodigoPostalAndEstatus(String codigoPostal, Integer estatus);	
	BuscaSucursalCodigoPostalResponse consultaSucursalesByCodigoPostalAndEstatus(String codigoPostal, Integer estatus);
	List<Object> findSucursalProductoByCodigoPostal(String codigoPostal);
	List<ConsultaSucursalesProductosResponse>buscaSucursalesProductosByCodigoPostal(String codigoPostal);
	List<Object> findIdSucursalesCercaCPDistancia(String latitud, String longitud, float distancia);
	List<ConsultaSucursalesProductosResponse> buscaIdSucursalesCercaCPDistancia(ConsultaSucursalesProductosRequest ConsultaSucursalesProductosRequest, float distancia);
	
	List<Object> findArticulosByIdSucursalAndIdCompuestoActivo(Integer idSucursal, List<String> lstCompuestosActivos);
	
}
