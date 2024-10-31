package com.mx.api.globall.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.model.CatalogoPlataformasMarketplace;
import com.mx.api.globall.market.repository.ICatalogoPlataformasMarketplaceRepository;

@Service
public class CatalogoPlataformasMarketplaceServiceImpl implements ICatalogoPlataformasMarketplaceService{

	@Autowired
	ICatalogoPlataformasMarketplaceRepository catPlataformasRepo;
	
	@Override
	public List<CatalogoPlataformasMarketplace> findAllByEstatus(Integer estatus) {		
		return catPlataformasRepo.findAllByEstatus(estatus);
	}

	@Override
	public CatalogoPlataformasMarketplace findAllByApiKeyAndEstatus(String apiKeyMarket, Integer estatus) {
		return catPlataformasRepo.findAllByApiKeyAndEstatus(apiKeyMarket, estatus);
	}

}
