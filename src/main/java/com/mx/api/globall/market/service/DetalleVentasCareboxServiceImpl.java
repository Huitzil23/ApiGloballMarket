package com.mx.api.globall.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.model.DetalleVentaMarketplace;
import com.mx.api.globall.market.repository.IDetalleVentasMarketplaceRepository;


@Service
public class DetalleVentasCareboxServiceImpl implements IDetalleVentasMarketplaceService {

	@Autowired
	IDetalleVentasMarketplaceRepository detVentaRepo;
	
	@Override
	public DetalleVentaMarketplace save(DetalleVentaMarketplace detVentaCareBox) {		
		return detVentaRepo.save(detVentaCareBox);
	}

	@Override
	public DetalleVentaMarketplace findByIdVentaMarketplaceAndEstatus(Integer idVentaCare, Integer estatus) {		
		return detVentaRepo.findByIdVentaMarketAndEstatus(idVentaCare, estatus);
	}
	
}
