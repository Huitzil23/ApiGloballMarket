package com.mx.api.globall.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.VentaMarketplace;

@Repository
public interface IVentaMarketplaceRepository extends JpaRepository<VentaMarketplace, Integer>{

	VentaMarketplace findEstatusByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta);
	
	@Query(value = "SELECT VM.IdSucursal, VM.IdVenta,  "
			+ " CM.IdClienteExterno, V.TotalVenta "
			+ "from tbc_GTC_VentaMarketplace VM "
			+ " INNER JOIN tbc_GTC_ClienteMarketplace CM ON VM.IdCliente = CM.IdClienteExterno "
			+ " INNER JOIN tbc_GTC_Venta V ON VM.IdSucursal = V.IdSucursal "
			+ " AND VM.IdVenta = V.IdVenta "			
			+ " WHERE VM.IdMarketplace = ?1 "
			+ " AND VM.Estatus = 4 "
			+ " ORDER BY VM.IdSucursal, VM.IdVenta ", nativeQuery = true)
	List<Object>findVentasByIdMarketplace(Integer idMarketplace);
	
	@Query(value = "SELECT VD.IdVenta, VD.ClaveArticulo, A.Nombre, VD.PrecioVenta, VD.Cantidad "			
			+ " FROM tbc_GTC_VentaDetalle VD "
			+ " INNER JOIN tbc_GTC_Articulos A ON VD.IdSucursal = A.IdSucursal "
			+ " AND VD.ClaveArticulo = A.ClaveArticulo "			
			+ " WHERE VD.IdSucursal = ?1 "
			+ " AND VD.IdVenta =  ?2 ", nativeQuery = true)
	List<Object>findDetalleVentasByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta );
}
