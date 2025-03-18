package com.mx.api.globall.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.CatalogoPlataformasMarketplace;

@Repository
public interface ICatalogoPlataformasMarketplaceRepository extends JpaRepository<CatalogoPlataformasMarketplace, Integer> {

	List<CatalogoPlataformasMarketplace> findAllByEstatus(Integer estatus);
	CatalogoPlataformasMarketplace findAllByApiKeyAndEstatus(String apiKeyMarket, Integer estatus);
	CatalogoPlataformasMarketplace findIdMarketplaceByNombreMarketplace(String nombreMarketPlace);
}
