package com.mx.api.globall.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mx.api.globall.market.model.CatalogoPlataformasMarketplace;

@Service
public interface ICatalogoPlataformasMarketplaceService {

	List<CatalogoPlataformasMarketplace>findAllByEstatus(Integer estatus);
	CatalogoPlataformasMarketplace findAllByApiKeyAndEstatus(String apiKeyMarket, Integer estatus);
}
