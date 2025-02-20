package com.mx.api.globall.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.ClienteMarketplace;

@Repository
public interface IClienteMarketplaceRepository extends JpaRepository<ClienteMarketplace, Integer>{

	ClienteMarketplace findIdClienteMarketByIdMarketplaceAndIdClienteExterno(Integer idMarketplace, Integer idClienteExterno);
	
	@Query(value="SELECT IdCliente FROM tbc_GTC_Cliente WHERE NombreCliente = ?1", nativeQuery = true)
	int findIdClienteByNombreCliente(String nombreCliente);
}
