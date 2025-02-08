package com.mx.api.globall.market.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.Venta;
import com.mx.api.globall.market.model.VentaId;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, VentaId> {
	
	Optional<Venta> findByVentaId_IdVentaAndVentaId_IdSucursal(Integer idVenta, Integer idSucursal);
	
	@Query(value = "{CALL dbo.spI_GTC_RegistraVentaDetalle("
			+ ":IdVenta,:ClaveArticulo,:Cantidad,:ImporteIva,:ImporteIeps,:ImporteDesc,:IdTerminal,:IdCorteCaja,:IdSucursal,"
			+ ":IdCliente,:UsuarioCreacion,:IsDeliv)}"
			, nativeQuery = true)
	int registraVentaDetalle(@Param("IdVenta") Integer IdVenta, @Param("ClaveArticulo") String ClaveArticulo,
			@Param("Cantidad") Integer Cantidad, @Param("ImporteIva") Float ImporteIva, @Param("ImporteIeps") Float ImporteIeps,
			@Param("ImporteDesc") Float ImporteDesc, @Param("IdTerminal") Integer IdTerminal, @Param("IdCorteCaja") Integer IdCorteCaja, @Param("IdSucursal") Integer IdSucursal,
			@Param("IdCliente") Integer IdCliente, @Param("UsuarioCreacion") Integer UsuarioCreacion, @Param("IsDeliv") Integer IsDeliv);
	
	@Query(value = "{CALL dbo.spI_GTC_GeneraVentaCobro("
			+ ":IdVenta,:IdSucursal,:UsuarioCreacion)}", nativeQuery = true)
	int generaVentaCobro(
			@Param("IdVenta") Integer IdVenta,
			@Param("IdSucursal") Integer IdSucursal,
			@Param("UsuarioCreacion") Integer UsuarioCreacion);
	
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.FechaCreacion >= :startFecha "
			+ "AND vta.FechaCreacion <= :endFecha "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			countQuery = "SELECT COUNT(*) FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.FechaCreacion >= :startFecha "
					+ "AND vta.FechaCreacion <= :endFecha "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Page<Object>getVentasPorFechas(@Param("idSucursal") Integer idSucursal, @Param("startFecha") Date startFecha, @Param("endFecha") Date endFecha, Pageable pageable);
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.IdVenta = :idTicket "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			countQuery = "SELECT COUNT(*) FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.IdVenta = :idTicket "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Page<Object>getVentasPorTicket(@Param("idSucursal") Integer idSucursal, @Param("idTicket") Integer idTicket, Pageable pageable);
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.IdCliente = :idCliente "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			countQuery = "SELECT COUNT(*) FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.IdCliente = :idCliente "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Page<Object>getVentasPorCliente(@Param("idSucursal") Integer idSucursal, @Param("idCliente") Integer idCliente, Pageable pageable);
	
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.UsuarioCreacion = :usuarioCreacion "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			countQuery = "SELECT COUNT(*) FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.UsuarioCreacion = :usuarioCreacion "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Page<Object>getVentasPorUsuario(@Param("idSucursal") Integer idSucursal, @Param("usuarioCreacion") Integer usuarioCreacion, Pageable pageable);
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.FechaCreacion >= :startFecha "
			+ "AND vta.FechaCreacion <= :endFecha "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			nativeQuery = true)
	public List<Object> getVentasPorFechasXls(@Param("idSucursal") Integer idSucursal, @Param("startFecha") Date startFecha, @Param("endFecha") Date endFecha);
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.IdVenta = :idTicket "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			nativeQuery = true)
	public List<Object> getVentasPorTicketXls(@Param("idSucursal") Integer idSucursal, @Param("idTicket") Integer idTicket);
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.IdCliente = :idCliente "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			nativeQuery = true)
	public List<Object> getVentasPorClienteXls(@Param("idSucursal") Integer idSucursal, @Param("idCliente") Integer idCliente);
	
	@Query(value = "SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
			+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
			+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
			+ "FROM tbc_GTC_Venta vta "
			+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
			+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
			+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
			+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
			+ "WHERE vta.IdSucursal = :idSucursal "
			+ "AND vta.UsuarioCreacion = :usuarioCreacion "
			+ "AND vta.Estatus IN (1,2,3) "
			//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
			+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
			+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
			+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
			+ "vta.Estatus, us.Usuario, vta.TotalVenta" ,
			nativeQuery = true)
	public List<Object> getVentasPorUsuarioXls(@Param("idSucursal") Integer idSucursal, @Param("usuarioCreacion") Integer usuarioCreacion);
	
	@Query(value = "SELECT SUM(Cantidad) cant, SUM(TotalVenta) impVta FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.FechaCreacion >= :startFecha "
					+ "AND vta.FechaCreacion <= :endFecha "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Object getVentasPorFechasTotal(@Param("idSucursal") Integer idSucursal, @Param("startFecha") Date startFecha, @Param("endFecha") Date endFecha);
	
	@Query(value = "SELECT SUM(Cantidad) cant, SUM(TotalVenta) impVta FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.IdVenta = :idTicket "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Object getVentasPorTicketTotal(@Param("idSucursal") Integer idSucursal, @Param("idTicket") Integer idTicket);
	
	
	@Query(value = "SELECT SUM(Cantidad) cant, SUM(TotalVenta) impVta FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.IdCliente = :idCliente "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Object getVentasPorClienteTotal(@Param("idSucursal") Integer idSucursal, @Param("idCliente") Integer idCliente);
	
	@Query(value = "SELECT SUM(Cantidad) cant, SUM(TotalVenta) impVta FROM (SELECT vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, cte.IdCliente, (cte.NombreCliente+' '+cte.ApellidoPaterno+' '+cte.ApellidoMaterno) NombreCliente, "
					+ "SUM(vtad.Cantidad) Cantidad, ISNULL(vta.SubTotal,0) SubTotal, 0.00 DescuentoSImpuestos, 0.00 DescuentoCImpuestos, ISNULL(vta.SubTotalConDescuento,0) SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "(vta.ImporteIVA + vta.ImporteIEPS) Impuestos, vta.Estatus, (CASE vta.Estatus WHEN 1 THEN 'TERMINADA' WHEN 2 THEN 'CANCELADA' WHEN 3 THEN 'DEVUELTA' END) DescEstatus, "
					+ "us.Usuario, '' EnvioDomicilio, vta.TotalVenta "
					+ "FROM tbc_GTC_Venta vta "
					+ "INNER JOIN tbc_GTC_Terminales trm ON vta.IdTerminal = trm.IdTerminal "
					+ "INNER JOIN tbc_GTC_Cliente cte ON vta.IdCliente = cte.IdCliente "
					+ "INNER JOIN tbc_GTC_VentaDetalle vtad ON vta.IdVenta = vtad.IdVenta AND vta.IdSucursal = vtad.IdSucursal "
					+ "INNER JOIN tbc_GTC_Usuario us ON vta.UsuarioCreacion = us.IdUsuario "
					+ "WHERE vta.IdSucursal = :idSucursal "
					+ "AND vta.UsuarioCreacion = :usuarioCreacion "
					+ "AND vta.Estatus IN (1,2,3) "
					//+" --1=TERMINADA, 2=CANCELADA, 3=DEVUELTA "
					+ "GROUP BY vta.IdVenta, vta.IdTerminal, trm.NombreTerminal, vta.FechaCreacion, "
					+ "cte.IdCliente, cte.NombreCliente, cte.ApellidoPaterno, cte.ApellidoMaterno, "
					+ "vta.SubTotal, vta.SubTotalConDescuento, vta.ImporteIVA, vta.ImporteIEPS, "
					+ "vta.Estatus, us.Usuario, vta.TotalVenta) T " ,
			nativeQuery = true)
	public Object getVentasPorUsuarioTotal(@Param("idSucursal") Integer idSucursal, @Param("usuarioCreacion") Integer usuarioCreacion);
	
	@Query(value = "{CALL dbo.spI_GTC_GeneraCancelacionTicket(:IdVenta,:IdTerminal,:IdSucursal,:UsuarioCreacion)}", nativeQuery = true)
	int generaCancelacionVenta(
			@Param("IdVenta") Integer IdVenta,
			@Param("IdTerminal") Integer IdTerminal,
			@Param("IdSucursal") Integer IdSucursal,
			@Param("UsuarioCreacion") Integer UsuarioCreacion);
	
	//Para consulta de ventas por delivery
	@Query(value = "SELECT CTE.NombreCliente, VTA.FechaCreacion, VTA.IdVenta, ART.ClaveArticulo, ART.Nombre, "
			+ "VTAD.Cantidad, VTAD.PorcentajeIVA, VTAD.PorcentajeIEPS, VTAD.TotalVenta, ART.Costo,'TERMINADA' AS Estatus, TP.TipoPago , "
			+ "VTA.IdCorteCaja, USR.Usuario, VTAC.Referencia "
			+ "FROM tbc_GTC_Venta VTA "
			+ "INNER JOIN tbc_GTC_VentaDetalle VTAD ON VTA.IdVenta = VTAD.IdVenta AND VTA.IdSucursal = VTAD.IdSucursal "
			+ "INNER JOIN tbc_GTC_Cliente CTE ON VTA.IdCliente = CTE.IdCliente "
			+ "INNER JOIN tbc_GTC_Articulos ART ON VTAD.ClaveArticulo = ART.ClaveArticulo AND VTAD.IdSucursal = ART.IdSucursal "
			+ "INNER JOIN tbc_GTC_VentaCobro VTAC ON VTA.IdVenta = VTAC.IdVenta AND VTA.IdSucursal = VTAC.IdSucursal "
			+ "INNER JOIN tbc_GTC_TipoPago TP ON VTAC.IdTipoPago = TP.IdTipoPago "
			+ "INNER JOIN tbc_GTC_Usuario USR ON VTA.UsuarioCreacion = USR.IdUsuario "
			+ "WHERE VTA.IdCliente IN (:idsCliente) "
			+ "AND VTA.FechaCreacion >= :startFecha "
			+ "AND VTA.FechaCreacion <= :endFecha "
			+ "AND VTA.IdSucursal = :idSucursal "
			+ "AND VTA.Estatus = 1 " ,
			countQuery = "SELECT COUNT(*) "
					+ "FROM tbc_GTC_Venta VTA "
					+ "INNER JOIN tbc_GTC_VentaDetalle VTAD ON VTA.IdVenta = VTAD.IdVenta AND VTA.IdSucursal = VTAD.IdSucursal "
					+ "INNER JOIN tbc_GTC_Cliente CTE ON VTA.IdCliente = CTE.IdCliente "
					+ "INNER JOIN tbc_GTC_Articulos ART ON VTAD.ClaveArticulo = ART.ClaveArticulo AND VTAD.IdSucursal = ART.IdSucursal "
					+ "INNER JOIN tbc_GTC_VentaCobro VTAC ON VTA.IdVenta = VTAC.IdVenta AND VTA.IdSucursal = VTAC.IdSucursal "
					+ "INNER JOIN tbc_GTC_TipoPago TP ON VTAC.IdTipoPago = TP.IdTipoPago "
					+ "INNER JOIN tbc_GTC_Usuario USR ON VTA.UsuarioCreacion = USR.IdUsuario "
					+ "WHERE VTA.IdCliente IN (:idsCliente) "
					+ "AND VTA.FechaCreacion >= :startFecha "
					+ "AND VTA.FechaCreacion <= :endFecha "
					+ "AND VTA.IdSucursal = :idSucursal "
					+ "AND VTA.Estatus = 1 " ,
			nativeQuery = true)
	public Page<Object> getVentasDeliveryPorFechas(@Param("idSucursal") Integer idSucursal, @Param("startFecha") Date startFecha, @Param("endFecha") Date endFecha, @Param("idsCliente") List<Integer> idsCliente, Pageable pageable);
	
	//Para reporte xls de ventas por delivery 
	@Query(value = "SELECT CTE.NombreCliente, VTA.FechaCreacion, VTA.IdVenta, ART.ClaveArticulo, ART.Nombre, "
			+ "VTAD.Cantidad, VTAD.PorcentajeIVA, VTAD.PorcentajeIEPS, VTAD.TotalVenta, ART.Costo,'TERMINADA' AS Estatus, TP.TipoPago , "
			+ "VTA.IdCorteCaja, USR.Usuario, VTAC.Referencia "
			+ "FROM tbc_GTC_Venta VTA "
			+ "INNER JOIN tbc_GTC_VentaDetalle VTAD ON VTA.IdVenta = VTAD.IdVenta AND VTA.IdSucursal = VTAD.IdSucursal "
			+ "INNER JOIN tbc_GTC_Cliente CTE ON VTA.IdCliente = CTE.IdCliente "
			+ "INNER JOIN tbc_GTC_Articulos ART ON VTAD.ClaveArticulo = ART.ClaveArticulo AND VTAD.IdSucursal = ART.IdSucursal "
			+ "INNER JOIN tbc_GTC_VentaCobro VTAC ON VTA.IdVenta = VTAC.IdVenta AND VTA.IdSucursal = VTAC.IdSucursal "
			+ "INNER JOIN tbc_GTC_TipoPago TP ON VTAC.IdTipoPago = TP.IdTipoPago "
			+ "INNER JOIN tbc_GTC_Usuario USR ON VTA.UsuarioCreacion = USR.IdUsuario "
			+ "WHERE VTA.IdCliente IN (:idsCliente) "
			+ "AND VTA.FechaCreacion >= :startFecha "
			+ "AND VTA.FechaCreacion <= :endFecha "
			+ "AND VTA.IdSucursal = :idSucursal "
			+ "AND VTA.Estatus = 1 ",
			nativeQuery = true)
	public List<Object> getVentasDeliveryPorFechasXls(@Param("idSucursal") Integer idSucursal, @Param("startFecha") Date startFecha, @Param("endFecha") Date endFecha, @Param("idsCliente") List<Integer> idsCliente);
	
	List<Venta> findByVentaId_IdSucursalAndVentaId_IdVentaIn(Integer idSucursal, List<Integer> idVenta);
	
	//Para consultar detalle de articulos para ejecutar a surtir medikit
	@Query(value = "SELECT VD.IdVenta, VD.IdSucursal, VD.ClaveArticulo, VD.Cantidad, VD.PrecioVenta, VD.PrecioPublico, "
			+ "( "
			+ "    CASE "
			+ "    WHEN VD.PorcentajeIVA > 0 THEN 1 "
			+ "    WHEN VD.PorcentajeIVA <= 0 THEN 0 "
			+ "    END "
			+ "  )  AS conIva, "
			+ "VD.TotalVenta, VD.IdVentaDetalle "
			+ "FROM tbc_GTC_VentaDetalle VD WITH(TABLOCK) "
			+ "INNER JOIN tbc_GTC_Venta V WITH(TABLOCK) ON VD.IdVenta = V.IdVenta AND VD.IdSucursal = V.IdSucursal "
			+ "WHERE VD.IdVenta = :IdVenta "
			+ "AND VD.IdSucursal = :IdSucursal AND VD.Estatus = 1 AND VD.Cantidad > 0 ",
			nativeQuery = true)
	public List<Object> getDatosDetalleVentaMedikit(@Param("IdVenta") Integer IdVenta, @Param("IdSucursal") Integer IdSucursal);
	
	//Para eliminar ticket generado para medikit si no se completo el surtir
	@Modifying
	@Query(value ="delete from tbc_GTC_Venta where IdVenta = :IdVenta and IdSucursal = :IdSucursal ",
			nativeQuery = true)
	void deleteVentaByIdVentaAndIdSucursal(@Param("IdVenta") Integer IdVenta, @Param("IdSucursal") Integer IdSucursal);
	
	@Query(value = "{CALL dbo.spI_GTC_ActualizaPrecioArticulo(:ClaveArticulo,:PrecioPublico,:PorcDescuento,:Costo,:PrecioVenta,"
			+ ":Margen,:Utilidad,:PorcentaIva,:PorcentaIeps,:IdPoliticaPrecio,:IdComercio,:IdSucursal,:Usuario)}", nativeQuery = true)
	int actualizaPrecioArticulo(
			@Param("ClaveArticulo") String ClaveArticulo,
			@Param("PrecioPublico") BigDecimal PrecioPublico,
			@Param("PorcDescuento") BigDecimal UsuarioCreacion,
			@Param("Costo") BigDecimal Costo,
			@Param("PrecioVenta") BigDecimal PrecioVenta,
			@Param("Margen") BigDecimal Margen,
			@Param("Utilidad") BigDecimal Utilidad,
			@Param("PorcentaIva") BigDecimal PorcentaIva,
			@Param("PorcentaIeps") BigDecimal PorcentaIeps,
			@Param("IdPoliticaPrecio") Integer IdPoliticaPrecio,
			@Param("IdComercio") Integer IdComercio,
			@Param("IdSucursal") Integer IdSucursal,
			@Param("Usuario") Integer Usuario
			);
}
