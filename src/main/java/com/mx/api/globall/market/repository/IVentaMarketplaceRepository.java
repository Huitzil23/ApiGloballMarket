package com.mx.api.globall.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.VentaMarketplace;

@Repository
public interface IVentaMarketplaceRepository extends JpaRepository<VentaMarketplace, Integer>{

	VentaMarketplace findEstatusByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta);
}
