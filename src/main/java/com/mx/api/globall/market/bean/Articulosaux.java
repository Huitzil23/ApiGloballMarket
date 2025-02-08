package com.mx.api.globall.market.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Articulosaux {
    private String ClaveArticulo;	
    private String Nombre;		
    private BigDecimal PrecioPublico;	
    private BigDecimal PorcDescuento;	
    private BigDecimal Costo;	
    private BigDecimal PrecioVenta;
    private BigDecimal Margen;		
    private BigDecimal Utilidad;	
    private BigDecimal PorcentaIVA;	
    private BigDecimal PorcentajeIEPS;	
    private Short Receta;			
    private Integer CantidadUMM;	
    private Integer IdDepartamento;		
    private Integer IdFabricante;	
    private Integer IdCategoria;
    private Integer IdMedidaSAT;
    private Integer IdPoliticaPrecio;	
    private Integer IdFamilia;		
    private Integer IdGrupoSSA;	
    private Short Estatus;		
    private Integer UsuarioCreacion;
    private Date FechaCreacion;	
    private Integer UsuarioModificacion;
    private Date FechaModificacion;
    private MultipartFile Thumbnail;
    private Integer idSucursal;
    private String CompuestoActivo;
    private String cveProdServicio;
	public Articulosaux() {
		super();
	}
	public Articulosaux(String claveArticulo, String nombre, BigDecimal precioPublico, BigDecimal porcDescuento,
			BigDecimal costo, BigDecimal precioVenta, BigDecimal margen, BigDecimal utilidad, BigDecimal porcentaIVA,
			BigDecimal porcentajeIEPS, Short receta, Integer cantidadUMM, Integer idDepartamento, Integer idFabricante,
			Integer idCategoria, Integer idMedidaSAT, Integer idPoliticaPrecio, Integer idFamilia, Integer idGrupoSSA,
			Short estatus, Integer usuarioCreacion, Date fechaCreacion, Integer usuarioModificacion,
			Date fechaModificacion, MultipartFile thumbnail, Integer idSucursal, String compuestoActivo, String cveProdServicio) {
		super();
		ClaveArticulo = claveArticulo;
		Nombre = nombre;
		PrecioPublico = precioPublico;
		PorcDescuento = porcDescuento;
		Costo = costo;
		PrecioVenta = precioVenta;
		Margen = margen;
		Utilidad = utilidad;
		PorcentaIVA = porcentaIVA;
		PorcentajeIEPS = porcentajeIEPS;
		Receta = receta;
		CantidadUMM = cantidadUMM;
		IdDepartamento = idDepartamento;
		IdFabricante = idFabricante;
		IdCategoria = idCategoria;
		IdMedidaSAT = idMedidaSAT;
		IdPoliticaPrecio = idPoliticaPrecio;
		IdFamilia = idFamilia;
		IdGrupoSSA = idGrupoSSA;
		Estatus = estatus;
		UsuarioCreacion = usuarioCreacion;
		FechaCreacion = fechaCreacion;
		UsuarioModificacion = usuarioModificacion;
		FechaModificacion = fechaModificacion;
		Thumbnail = thumbnail;
		this.idSucursal = idSucursal;
		CompuestoActivo = compuestoActivo;
		this.cveProdServicio = cveProdServicio;
	}
}
