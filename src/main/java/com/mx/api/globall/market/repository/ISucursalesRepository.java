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
	
	@Query(value = " SELECT  distinct(I.IdSucursal) AS IdSucursal, "
			+ " S.CodigoPostal, C.NombreComercio, S.Nombre AS NombreSucursal, C.Calle, C.NumeroExt, "
			+ " CL.NobreColonia, D.Descripcion AS Delegacion, E.Estado, P.Pais "
			+ " FROM tbc_GTC_CatSucursal S "
			+ " INNER JOIN tbc_GTC_Inventario I ON I.IdSucursal = S.IdSucursal "
			+ " INNER JOIN tbc_GTC_Comercios C ON S.IdComercio = C.IdComercio "
			+ " INNER JOIN tbc_GTC_Pais P ON P.IdPais = S.IdPais "
			+ " INNER JOIN tbc_GTC_Estado E ON E.IdEstado = S.IdEstado "
			+ " INNER JOIN tbc_GTC_Delegacion D ON D.IdDelegacion = S.IdMunicipioDelegacion "
			+ " INNER JOIN tbc_GTC_Colonia CL ON CL.IdColonia = S.IdColonia "	
			+ " WHERE S.IdTipoDistribucion = 1 "
			+ " AND GEOGRAPHY\\:\\:Point(S.Latitud, S.Longitud, 4326).STDistance(GEOGRAPHY\\:\\:Point(?1, ?2, 4326)) / 1000 < ?3 "
			+ " AND S.Latitud IS NOT NULL "
			+ " AND S.Longitud IS NOT NULL "
			, nativeQuery=true)
	List<Object> findIdSucursalesCercaCPDistancia(String latitud, String longitud, float distancia);
	
	@Query(value = "SELECT A.ClaveArticulo, A.Nombre, A.PrecioPublico, I.ExistenciaFisica, AC.IdCompuestoActivo, "
			+ " A.Receta, (CASE WHEN A.Receta = 1 THEN 'true' ELSE 'false' END ) AS RecetaMax, "
			+ " A.IdGrupoSSA, (CASE WHEN A.IdGrupoSSA = 1 OR A.IdGrupoSSA = 2 OR A.IdGrupoSSA = 3 THEN 'true' ELSE 'false' END) AS ControladoMax "
			+ " FROM tbc_GTC_Articulos A "
			+ " INNER JOIN tbc_GTC_Inventario I ON A.IdSucursal = I.IdSucursal "
			+ " AND A.ClaveArticulo = I.ClaveArticulo "
			+ " INNER JOIN tbc_GTC_ArticulosCompuestoActivo AC ON AC.IdSucursal = A.IdSucursal "
			+ " AND AC.ClaveArticulo = A.ClaveArticulo "
			+ " WHERE A.IdSucursal = ?1 "
			+ " AND AC.IdCompuestoActivo IN (?2) "
			+ " AND A.PrecioPublico = (SELECT MAX(A.PrecioPublico)FROM tbc_GTC_Articulos A "
			+ " INNER JOIN tbc_GTC_ArticulosCompuestoActivo CA ON CA.IdSucursal = A.IdSucursal "
			+ " AND CA.ClaveArticulo = A.ClaveArticulo INNER JOIN tbc_GTC_Inventario I ON I.IdSucursal = A.IdSucursal AND I.ClaveArticulo = A.ClaveArticulo "
			+ "	WHERE A.IdSucursal = AC.IdSucursal AND CA.IdCompuestoActivo = AC.IdCompuestoActivo GROUP BY CA.IdCompuestoActivo ) "
			+ " UNION "
			+ " SELECT A.ClaveArticulo, A.Nombre, A.PrecioPublico, I.ExistenciaFisica, AC.IdCompuestoActivo, "
			+ " A.Receta, (CASE WHEN A.Receta = 1 THEN 'true' ELSE 'false' END ) AS RecetaMax, "
			+ " A.IdGrupoSSA, (CASE WHEN A.IdGrupoSSA = 1 OR A.IdGrupoSSA = 2 OR A.IdGrupoSSA = 3 THEN 'true' ELSE 'false' END) AS ControladoMin "
			+ " FROM tbc_GTC_Articulos A "
			+ " INNER JOIN tbc_GTC_Inventario I ON A.IdSucursal = I.IdSucursal "
			+ " AND A.ClaveArticulo = I.ClaveArticulo "
			+ " INNER JOIN tbc_GTC_ArticulosCompuestoActivo AC ON AC.IdSucursal = A.IdSucursal "
			+ " AND AC.ClaveArticulo = A.ClaveArticulo "
			+ " WHERE A.IdSucursal = ?1"
			+ " AND AC.IdCompuestoActivo IN (?2) "
			+ " AND A.PrecioPublico = ( SELECT MIN(A.PrecioPublico) FROM tbc_GTC_Articulos A  INNER JOIN tbc_GTC_ArticulosCompuestoActivo CA ON CA.IdSucursal = A.IdSucursal "
			+ " AND CA.ClaveArticulo = A.ClaveArticulo INNER JOIN tbc_GTC_Inventario I ON I.IdSucursal = A.IdSucursal  AND I.ClaveArticulo = A.ClaveArticulo WHERE A.IdSucursal = AC.IdSucursal  "
			+ " AND CA.IdCompuestoActivo = AC.IdCompuestoActivo GROUP BY CA.IdCompuestoActivo )"
			+ " ORDER BY AC.IdCompuestoActivo "			
			,nativeQuery = true)
	List<Object> findArticulosByIdSucursalAndIdCompuestoActivo(Integer idSucursal, List<String> lstCompuestoActivo);
	
	
	@Query(value = "SELECT A.ClaveArticulo, A.Nombre, A.PrecioPublico, I.ExistenciaFisica, AC.IdCompuestoActivo, "
			+ " A.Receta, (CASE WHEN A.Receta = 1 THEN 'true' ELSE 'false' END ) AS RecetaMax, "
			+ " A.IdGrupoSSA, (CASE WHEN A.IdGrupoSSA = 1 OR A.IdGrupoSSA = 2 OR A.IdGrupoSSA = 3 THEN 'true' ELSE 'false' END) AS ControladoMax  "
			+ " FROM tbc_GTC_Articulos A "
			+ " INNER JOIN tbc_GTC_Inventario I ON A.IdSucursal = I.IdSucursal "
			+ " LEFT JOIN tbc_GTC_ArticulosCompuestoActivo AC ON AC.IdSucursal = A.IdSucursal  "
			+ " AND AC.ClaveArticulo = A.ClaveArticulo "
			+ " WHERE A.IdSucursal = ?1 "
			+ " AND A.ClaveArticulo IN (?2) "
			+ " AND A.ClaveArticulo = I.ClaveArticulo "			
			,nativeQuery = true)
	List<Object> findArticulosByIdSucursalAndClaveArticulo(Integer idSucursal, List<String> lstClaveArticulo);
	
	@Query(value = "SELECT A.ClaveArticulo, A.Nombre, A.PrecioPublico, I.ExistenciaFisica, AC.IdCompuestoActivo, "
			+ " A.Receta, (CASE WHEN A.Receta = 1 THEN 'true' ELSE 'false' END ) AS RecetaMax, "
			+ " A.IdGrupoSSA, (CASE WHEN A.IdGrupoSSA = 1 OR A.IdGrupoSSA = 2 OR A.IdGrupoSSA = 3 THEN 'true' ELSE 'false' END) AS ControladoMax  "
			+ " FROM tbc_GTC_Articulos A "
			+ " INNER JOIN tbc_GTC_Inventario I ON A.IdSucursal = I.IdSucursal "
			+ " LEFT JOIN tbc_GTC_ArticulosCompuestoActivo AC ON AC.IdSucursal = A.IdSucursal  "
			+ " AND AC.ClaveArticulo = A.ClaveArticulo "
			+ " WHERE A.IdSucursal = ?1 "
			+ " AND A.ClaveArticulo = ?2 "
			+ " AND I.ExistenciaFisica >= ?3 "
			+ " AND A.ClaveArticulo = I.ClaveArticulo "			
			,nativeQuery = true)
	List<Object> findArticulosByIdSucursalAndClaveArticuloValidCantidad(Integer idSucursal, String sku, Integer cantidad);

}
