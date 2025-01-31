package com.mx.api.globall.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.Sucursales;

@Repository
public interface ISucursalesRepository extends JpaRepository<Sucursales, Integer>{

	List<Sucursales> findByCodigoPostalAndEstatus(String codigoPostal, Integer estatus);
	
	@Query(value = "SELECT C.IdComercio, C.NombreComercio, S.IdSucursal, S.Nombre as nombreSucursal,  A.ClaveArticulo, A.Nombre, I.ExistenciaFisica "
			+ "FROM tbc_GTC_CatSucursal S "
			+ "INNER JOIN tbc_GTC_Comercios C ON S.IdComercio = C.IdComercio "
			+ "INNER JOIN tbc_GTC_Articulos A ON A.IdSucursal = S.IdSucursal "
			+ "INNER JOIN tbc_GTC_CompuestoActivo  CA ON A.IdSucursal = CA.IdSucursal "
			+ "AND A.IdCompuestoActivo = CA.IdCompuestoActivo "
			+ "INNER JOIN tbc_GTC_Inventario I ON A.IdSucursal = I.IdSucursal "
			+ "AND A.ClaveArticulo = I.ClaveArticulo "
			+ "AND I.ExistenciaFisica > 0 "
			+ "WHERE S.CodigoPostal = ?1 "
			+ "ORDER BY S.IdSucursal ASC " , nativeQuery = true)
	List<Object> findSucursalProductoByCodigoPostal(String codigoPostal);
	
	@Query(value ="SELECT  S.IdSucursal, "
			+ " S.CodigoPostal, C.NombreComercio, S.Nombre AS NombreSucursal, C.Calle, C.NumeroExt, "
			+ " CL.NobreColonia, D.Descripcion AS Delegacion, E.Estado, P.Pais "
			+ " FROM tbc_GTC_CatSucursal S "
			+ " INNER JOIN tbc_GTC_Comercios C ON S.IdComercio = C.IdComercio "
			+ " INNER JOIN tbc_GTC_Pais P ON P.IdPais = S.IdPais "
			+ " INNER JOIN tbc_GTC_Estado E ON E.IdEstado = S.IdEstado "
			+ " INNER JOIN tbc_GTC_Delegacion D ON D.IdDelegacion = S.IdMunicipioDelegacion "
			+ " INNER JOIN tbc_GTC_Colonia CL ON CL.IdColonia = S.IdColonia "
			+ " WHERE S.CodigoPostal =?1 "
			+ " AND S.IdTipoDistribucion = 1 "
			+ " UNION  "
			+ " SELECT  S.IdSucursal, "
			+ " S.CodigoPostal, C.NombreComercio, S.Nombre AS NombreSucursal, C.Calle, C.NumeroExt, "
			+ " CL.NobreColonia, D.Descripcion AS Delegacion, E.Estado, P.Pais "
			+ " FROM tbc_GTC_CatSucursal S "
			+ " INNER JOIN tbc_GTC_Comercios C ON S.IdComercio = C.IdComercio "
			+ " INNER JOIN tbc_GTC_Pais P ON P.IdPais = S.IdPais "
			+ " INNER JOIN tbc_GTC_Estado E ON E.IdEstado = S.IdEstado "
			+ " INNER JOIN tbc_GTC_Delegacion D ON D.IdDelegacion = S.IdMunicipioDelegacion "
			+ " INNER JOIN tbc_GTC_Colonia CL ON CL.IdColonia = S.IdColonia "	
			+ " WHERE S.IdTipoDistribucion = 1 "
			+ " AND GEOGRAPHY\\:\\:Point(S.Latitud, S.Longitud, 4326).STDistance(GEOGRAPHY\\:\\:Point(?2, ?3, 4326)) / 1000 < ?4 "
			+ " AND S.Latitud IS NOT NULL "
			+ " AND S.Longitud IS NOT NULL "
			, nativeQuery=true)
	List<Object> findIdSucursalesCercaCPDistancia(String codigoPostal, String latitud, String longitud, float distancia);
	
	@Query(value = "SELECT A.ClaveArticulo, A.Nombre, A.PrecioPublico, I.ExistenciaFisica "
			+ " FROM tbc_GTC_Articulos A "
			+ " INNER JOIN tbc_GTC_Inventario I ON A.IdSucursal = I.IdSucursal "
			+ " AND A.ClaveArticulo = I.ClaveArticulo "
			+ " WHERE A.IdSucursal = ?1 "
			+ " AND A.IdCompuestoActivo IN (?2) "
			+ " AND A.PrecioPublico = (SELECT MAX(PrecioPublico) FROM tbc_GTC_Articulos where IdSucursal = A.IdSucursal AND IdCompuestoActivo = A.IdCompuestoActivo) "
			+ " UNION "
			+ " SELECT A.ClaveArticulo, A.Nombre, A.PrecioPublico, I.ExistenciaFisica "
			+ " FROM tbc_GTC_Articulos A "
			+ " INNER JOIN tbc_GTC_Inventario I ON A.IdSucursal = I.IdSucursal "
			+ " AND A.ClaveArticulo = I.ClaveArticulo "
			+ " WHERE A.IdSucursal = ?1"
			+ " AND A.IdCompuestoActivo IN (?2) "
			+ " AND A.PrecioPublico = (SELECT MIN(PrecioPublico) FROM tbc_GTC_Articulos where IdSucursal = A.IdSucursal AND IdCompuestoActivo = A.IdCompuestoActivo) "
			
			,nativeQuery = true)
	List<Object> findArticulosByIdSucursalAndIdCompuestoActivo(Integer idSucursal, List<String> lstCompuestoActivo);
}
