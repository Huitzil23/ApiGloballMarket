package com.mx.api.globall.market.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.mx.api.globall.market.bean.ConsultaDetalleArticulos;
import com.mx.api.globall.market.model.Articulos;
import com.mx.api.globall.market.model.ArticulosId;

@Repository
public interface IArticulosRepository extends JpaRepository<Articulos, ArticulosId>{
	List<Articulos> findAllByOrderByNombreAsc();

	//List<Articulos> findAllByIdSucursalOrderByNombreAsc(Integer sucursal);//
	//List<Articulos> findAllByArticulosIdOrderByNombreAsc(ArticulosId sucursal);

	/*Optional<Articulos> findByClaveArticuloAndIdSucursal(String cveArticulo, Integer idSucursal);*/

	/*List<Articulos> findAllByIdSucursalAndClaveArticuloOrderByNombreAsc(Integer sucursal, String cveArticulo);*/
	
	List<Articulos> findAllByArticulosIdOrderByNombreAsc(ArticulosId artId);
	
	@Query(value = "SELECT * FROM tbc_GTC_Articulos WHERE IdSucursal = ?1 ORDER BY Nombre ASC" , nativeQuery = true)
	List<Articulos> findAllByArticulosIdOrderByNombreAsc(Integer sucursal);
	
	
	/*List<Articulos> findAllByIdSucursalAndNombreContainsOrderByNombreAsc(Integer sucursal, String nombre);*/
	@Query(value = "SELECT * FROM tbc_GTC_Articulos WHERE IdSucursal = ?1 AND Nombre LIKE CONCAT('%', ?2, '%') ORDER BY Nombre ASC" , nativeQuery = true)
	List<Articulos> findAllByArticulosIdAndNombreContainsOrderByNombreAsc(Integer sucursal, String nombre);
	
	
	/*List<Articulos>findAllByIdSucursalAndClaveArticuloContainsOrderByNombreAsc(Integer sucursal, Integer cveArticulo);*/
	@Query(value = "SELECT * FROM tbc_GTC_Articulos WHERE IdSucursal = ?1 AND ClaveArticulo = ?2 ORDER BY Nombre ASC" , nativeQuery = true)
	List<Articulos>findAllByArticulosIdContainsOrderByNombreAsc(Integer sucursal, String cveArticulo);
	
	/*List<Articulos>findAllByIdSucursalAndClaveArticuloContainsOrNombreContainsOrderByNombreAsc(Integer sucursal, Integer cveArticulo, String nombre);*/
	@Query(value = "SELECT * FROM tbc_GTC_Articulos WHERE IdSucursal = ?1 AND ClaveArticulo = ?2 AND Nombre LIKE CONCAT('%', ?3, '%') ORDER BY Nombre ASC" , nativeQuery = true)
	List<Articulos>findAllByArticulosIdContainsOrNombreContainsOrderByNombreAsc(Integer sucursal , String cveArticulo, String nombre);

	@Query(value = "{CALL dbo.spI_GTC_AgregaInventarioArticulosSucursal(:IdSucursal,:UsuarioCreacion)}", nativeQuery = true)
	int agregaInventarioArticulos(@Param("IdSucursal") Integer IdSucursal,@Param("UsuarioCreacion") Integer UsuarioCreacion);

	//@Query(value = "SELECT * FROM tbc_GTC_Articulos ART " //, (SELECT ExistenciaFisica FROM tbc_GTC_Inventario WHERE ClaveArticulo = ART.ClaveArticulo AND IdSucursal = ART.IdSucursal AND IdAlmacen = ?3 ) As existencias FROM tbc_GTC_Articulos ART "
	//@Query(value = "SELECT * , (SELECT ExistenciaFisica FROM tbc_GTC_Inventario WHERE ClaveArticulo = ART.ClaveArticulo AND IdSucursal = ART.IdSucursal AND IdAlmacen = ?3 ) As existencias FROM tbc_GTC_Articulos ART "
	//		+ "WHERE ART.IdSucursal = ?1 AND ART.Nombre LIKE CONCAT('%', ?2 , '%') ORDER BY ART.Nombre ASC " , nativeQuery = true)
	@Query(value = "SET NOCOUNT ON "
			+ "SELECT ART.*, INV.ExistenciaFisica "
			+ "FROM tbc_GTC_Articulos ART WITH(NOLOCK)  "
			+ "INNER JOIN tbc_GTC_Inventario INV WITH(NOLOCK) ON INV.ClaveArticulo = ART.ClaveArticulo AND INV.IdSucursal = ART.IdSucursal "
			+ "WHERE ART.IdSucursal = ?1 AND ART.Nombre LIKE CONCAT('%', ?2 , '%') ORDER BY ART.Nombre ASC "
			+ "SET NOCOUNT OFF " , nativeQuery = true)
	List<Object> pvFindAllByArticulosIdAndNombreContainsOrderByNombreAsc(Integer sucursal, String nombre);
	
	//@Query(value = "SELECT * FROM tbc_GTC_Articulos ART "//, (SELECT ExistenciaFisica FROM tbc_GTC_Inventario WHERE ClaveArticulo = ART.ClaveArticulo AND IdSucursal = ART.IdSucursal AND IdAlmacen = ?3 ) As existencias FROM tbc_GTC_Articulos ART "
	/*@Query(value = "SELECT * , (SELECT ExistenciaFisica FROM tbc_GTC_Inventario WHERE ClaveArticulo = ART.ClaveArticulo AND IdSucursal = ART.IdSucursal AND IdAlmacen = ?3 ) As existencias FROM tbc_GTC_Articulos ART "
			+ " WHERE ART.IdSucursal = ?1 AND ART.ClaveArticulo = ?2 ORDER BY ART.Nombre ASC" , nativeQuery = true)*/
	@Query(value = "SET NOCOUNT ON "
			+ "SELECT ART.*, INV.ExistenciaFisica "
			+ "FROM tbc_GTC_Articulos ART WITH(NOLOCK)  "
			+ "INNER JOIN tbc_GTC_Inventario INV WITH(NOLOCK) ON INV.ClaveArticulo = ART.ClaveArticulo AND INV.IdSucursal = ART.IdSucursal  "
			+ "WHERE ART.IdSucursal = ?1 AND ART.ClaveArticulo = ?2 ORDER BY ART.Nombre ASC " , nativeQuery = true)
	Object pvFindByArticulosIdOrderByNombreAsc(Integer sucursal, String cveArticulo);
	
	@Query(value = "SELECT ExistenciaFisica FROM tbc_GTC_Inventario WHERE ClaveArticulo = ?2 AND IdSucursal = ?1 AND IdAlmacen = ?3 ", nativeQuery = true)
	Object pvFindByArticulosExistencias(Integer sucursal, String cveArticulo, Integer idAlmacen);

	//@Query(value = "SELECT * FROM tbc_GTC_Articulos ART " //, (SELECT ExistenciaFisica FROM tbc_GTC_Inventario WHERE ClaveArticulo = ART.ClaveArticulo AND IdSucursal = ART.IdSucursal AND IdAlmacen = ?2 ) As Existencias FROM tbc_GTC_Articulos ART "
	/*@Query(value = "SELECT * , (SELECT ExistenciaFisica FROM tbc_GTC_Inventario WHERE ClaveArticulo = ART.ClaveArticulo AND IdSucursal = ART.IdSucursal AND IdAlmacen = ?2 ) As Existencias FROM tbc_GTC_Articulos ART "
			+ "WHERE ART.IdSucursal = ?1 "
			+ "AND ART.ClaveArticulo IN("
			+ "  SELECT TOP 30 SumDetalle.ClaveArticulo "
			+ "  FROM("
			+ "      SELECT ClaveArticulo, "
			+ "      SUM(Cantidad) Total_Venta "
			+ "      FROM tbc_GTC_VentaDetalle "
			+ "      WHERE "
			+ "      IdSucursal = ?1 "
			+ "      GROUP BY ClaveArticulo "
			+ "  ) SumDetalle "
			+ "  ORDER BY SumDetalle.Total_Venta DESC"
			+ ") "
			+ "ORDER BY ART.Nombre ASC" , nativeQuery = true)*/
	@Query(value = "SET NOCOUNT ON "
			+ "SELECT   "
			+ "ART.*, INV.ExistenciaFisica "
			+ "FROM ( "
			+ "   SELECT TOP 30 ClaveArticulo,        "
			+ "   SUM(Cantidad) Total_Venta, "
			+ "   IdSucursal "
			+ "   FROM tbc_GTC_VentaDetalle  WITH(NOLOCK)      "
			+ "   WHERE IdSucursal = ?1   "
			+ "   GROUP BY ClaveArticulo, IdSucursal ORDER BY Total_Venta DESC ) XXX "
			+ "INNER JOIN tbc_GTC_Articulos ART WITH(NOLOCK) ON XXX.IdSucursal = ART.IdSucursal AND XXX.ClaveArticulo = ART.ClaveArticulo "
			+ "INNER JOIN tbc_GTC_Inventario INV WITH(NOLOCK) ON INV.ClaveArticulo = ART.ClaveArticulo AND INV.IdSucursal = ART.IdSucursal AND IdAlmacen = ?2 "
			+ "ORDER BY Total_Venta DESC "
			+ "SET NOCOUNT OFF " , nativeQuery = true)
	List<Object> pvFindAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(Integer sucursal);
	
	@Query(value = "SELECT TOP 30 SumDetalle.ClaveArticulo, SumDetalle.Total_Venta "
			+ "FROM("
			+ "SELECT ClaveArticulo, "
			+ "sum(Cantidad) Total_Venta "
			+ "FROM tbc_GTC_VentaDetalle "
			+ "WHERE "
			+ "IdSucursal = ?1 "
			+ "GROUP BY ClaveArticulo "
			+ ") SumDetalle "
			+ "ORDER BY SumDetalle.Total_Venta desc" , nativeQuery = true)
	List<Object> findArticulosTopVendidos(Integer sucursal);
	
	List<Articulos> findAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(Integer idSucursal, List<String> cvesArt);

	
	@Query(value = "SELECT ISNULL(AVG(Estatus),0) FROM tbc_GTC_ControlCargaMasivaArticulos"
			+ " WHERE IdSucursal = ?1 AND IdComercio = ?2 ", nativeQuery = true)
	Integer getEstatusCarga(Integer sucursal, Integer comercio);
	
	@Query(value = "SET NOCOUNT ON "
			+ "SELECT ExistenciaFisica "
			+ "FROM tbc_GTC_Inventario WITH(NOLOCK) "
			+ "WHERE IdSucursal = ?1 AND ClaveArticulo = ?2 AND IdAlmacen = ?3 " , nativeQuery = true)
	Integer getExistenciaArticuloSucursalAlmacen(Integer sucursal, String cveArticulo, Integer idAlmacen);
	
	
	@Query(value = "SET NOCOUNT ON  "
			+ "SELECT INV.IdSucursal, ART.ClaveArticulo, ART.Nombre, ART.PrecioPublico, ART.PrecioVenta, INV.ExistenciaFisica  "
			+ "FROM tbc_GTC_Articulos ART WITH(NOLOCK) "
			+ "INNER JOIN tbc_GTC_Inventario INV WITH(NOLOCK) ON INV.ClaveArticulo = ART.ClaveArticulo "
			+ "AND INV.IdSucursal = ART.IdSucursal  "
			+ "INNER JOIN tbc_GTC_ConfiguracionSucursalesAlivia CSA ON CSA.IdSucursal = INV.IdSucursal "
			//+ "WHERE INV.ExistenciaFisica > 0 "
			+ "ORDER BY INV.IdSucursal, ART.Nombre ASC " ,nativeQuery = true)
	List<Object> getArticulos();
	
}
