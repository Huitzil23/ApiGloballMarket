package com.mx.api.globall.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.model.ClienteMarketplace;
import com.mx.api.globall.market.repository.IClienteMarketplaceRepository;

@Service
public class ClienteMarketplaceServiceImpl implements IClienteMarketplaceService{

	@Autowired
	IClienteMarketplaceRepository iClienteMarketplaceRepository;
	
	@Override
	public ClienteMarketplace findIdClienteMarketByIdMarketplaceAndIdClienteExterno(Integer idMarketplace, Integer idClienteExterno) {
		return iClienteMarketplaceRepository.findIdClienteMarketByIdMarketplaceAndIdClienteExterno(idMarketplace, idClienteExterno);
	}
	
	@Override
	public ClienteMarketplace save(ClienteMarketplace clienteMarketplace) {		
		return iClienteMarketplaceRepository.save(clienteMarketplace);
	}

	@Override
	public int findIdClienteByNombreCliente(String nombreCliente) {
		return iClienteMarketplaceRepository.findIdClienteByNombreCliente(nombreCliente);
	}

	

}
