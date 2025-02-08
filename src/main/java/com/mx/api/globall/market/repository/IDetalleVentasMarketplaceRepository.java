package com.mx.api.globall.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.DetalleVentaMarketplace;

@Repository
public interface IDetalleVentasMarketplaceRepository extends JpaRepository<DetalleVentaMarketplace, Integer>{

	DetalleVentaMarketplace findByIdVentaMarketAndEstatus(Integer idVentaCare, Integer estatus);
	
}
