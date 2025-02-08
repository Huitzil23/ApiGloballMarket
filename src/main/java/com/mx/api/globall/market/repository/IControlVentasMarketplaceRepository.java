package com.mx.api.globall.market.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.VentaMarketplace;

@Repository
public interface IControlVentasMarketplaceRepository extends JpaRepository<VentaMarketplace, Integer>{

	@Query(value="SELECT VD.IdVenta, VD.IdSucursal, VC.IdCliente, VD.ClaveArticulo, AR.Nombre, VD.Cantidad "
			+ " FROM tbc_GTC_VentaCareBox VC "
			+ " INNER JOIN tbc_GTC_Venta V ON V.IdSucursal = VC.IdSucursal"
			+ " AND V.IdVenta = VC.IdVenta "
			+ " INNER JOIN tbc_GTC_VentaDetalle VD ON VD.IdSucursal = V.IdSucursal AND VD.IdVenta = V.IdVenta "
			+ " INNER JOIN tbc_GTC_Articulos AR ON AR.IdSucursal = VD.IdSucursal "
			+ " AND AR.ClaveArticulo = VD.ClaveArticulo "
			+ " WHERE V.IdSucursal = ?1"
			+ " AND V.IdVenta = ?2 "
			+ " AND V.Estatus = ?3 ", nativeQuery=true)
	List<Object>findVentasByIdSucursalAndIdVentaAndEstatus(Integer idSucursal, Integer idVenta, Integer estatus);
	
	@Query(value="SELECT CC.NombreCliente, CC.ApellidoPaterno, CC.ApellidoMaterno, CC.Telefono, CC.Calle, CC.NumExterior, "
			+ " CC.NumInterior, CL.NobreColonia, DL.Descripcion, CP.Codigo, ES.Estado, PS.Pais, CC.EntreCalle1, CC.EntreCalle2, "
			+ " CC.ReferenciaDir, CC.Notas, TE.Descripcion AS TipoEntrega, CC.IdClienteCBox, VC.IdVentaCare, VC.UsuarioCreacion,"
			+ " VC.FechaCreacion  "
			+ " FROM tbc_GTC_ClienteCareBox CC "
			+ " INNER JOIN tbc_GTC_VentaCareBox VC ON VC.IdCliente = CC.IdClienteCBox "
			+ " INNER JOIN tbc_GTC_Colonia CL ON CL.IdColonia = CC.IdColonia "
			+ " INNER JOIN tbc_GTC_Delegacion DL ON DL.IdDelegacion = CC.IdMunicipioDelegacion "
			+ " INNER JOIN tbc_GTC_CodigoPostal CP ON CP.Codigo =  CC.CodigoPostal "
			+ " INNER JOIN tbc_GTC_Estado ES ON ES.IdEstado = CC.IdEstado "
			+ " INNER JOIN tbc_GTC_Pais PS ON PS.IdPais = CC.IdPais "
			+ " INNER JOIN tbc_GTC_TipoEntrega TE ON TE.IdTipoEntrega = CC.IdTipoEntrega "
			+ " WHERE VC.IdSucursal = ?1"
			+ " AND VC.IdVenta = ?2 ", nativeQuery= true)
	Object findClienteByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta);	
	
	//rcf
	List<VentaMarketplace> findByIdSucursalAndEstatus(Integer IdSucursal, Integer estatus);
	List<VentaMarketplace> findByIdSucursal(Integer IdSucursal);
	VentaMarketplace findByIdSucursalAndIdVenta(Integer IdSucursal, Integer idVenta);
	List<VentaMarketplace> findAllByFechaCreacionBetweenOrderByFechaCreacionDesc(Date startDate, Date endDate);
	List<VentaMarketplace> findAllByIdSucursalAndEstatusAndFechaCreacionBetweenOrderByFechaCreacionDesc(Integer idSucursal, Integer estatus, Date startDate, Date endDate);
	//
}
