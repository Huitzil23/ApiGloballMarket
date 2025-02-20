package com.mx.api.globall.market.service;

import com.mx.api.globall.market.model.ClienteMarketplace;

public interface IClienteMarketplaceService {

	ClienteMarketplace findIdClienteMarketByIdMarketplaceAndIdClienteExterno(Integer idMarketplace, Integer idClienteExterno);
	ClienteMarketplace save(ClienteMarketplace clienteMarketplace);
	
	int findIdClienteByNombreCliente(String nombreCliente);
}
