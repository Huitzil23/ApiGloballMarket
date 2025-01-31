package com.mx.api.globall.market.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbc_GTC_Articulos",catalog = "dbo")
public class Articulos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8870847736925183714L;
	//@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "ClaveArticulo")
    //private String claveArticulo;	
	@EmbeddedId
	private ArticulosId articulosId;
    @Column(name = "Nombre")
    private String nombre;		
    @Column(name = "PrecioPublico")
    private BigDecimal PrecioPublico;	
    @Column(name = "PorcDescuento")
    private BigDecimal PorcDescuento;	
    @Column(name = "Costo")
    private BigDecimal Costo;	
    @Column(name = "PrecioVenta")
    private BigDecimal PrecioVenta;
    @Column(name = "Margen")
    private BigDecimal Margen;		
    @Column(name = "Utilidad")
    private BigDecimal Utilidad;	
    @Column(name = "PorcentaIVA")
    private BigDecimal PorcentaIVA;	
    @Column(name = "PorcentajeIEPS")
    private BigDecimal PorcentajeIEPS;	
    @Column(name = "Receta")
    private Short Receta;			
    @Column(name = "CantidadUMM")
    private Integer CantidadUMM;	
    @Column(name = "IdDepartamento")
    private Integer IdDepartamento;		
    @Column(name = "IdFabricante")
    private Integer IdFabricante;	
    @Column(name = "IdCategoria")
    private Integer IdCategoria;
    @Column(name = "IdMedidaSAT")
    private Integer IdMedidaSAT;
    @Column(name = "IdPoliticaPrecio")
    private Integer IdPoliticaPrecio;	
    @Column(name = "IdFamilia")
    private Integer IdFamilia;		
    @Column(name = "IdGrupoSSA")
    private Integer IdGrupoSSA;	
    //@Column(name = "IdSucursal")
    //private Integer idSucursal;	
    @Column(name = "Estatus")
    private Short Estatus;		
    @Column(name = "UsuarioCreacion")
    private Integer UsuarioCreacion;
    @Column(name = "FechaCreacion")
    private Date FechaCreacion;	
    @Column(name = "UsuarioModificacion")
    private Integer UsuarioModificacion;
    @Column(name = "FechaModificacion")
    private Date FechaModificacion;
    @Lob
	@Column(name = "Thumbnail")
	private byte[] Thumbnail;
    
    private String ThumbnailAux;
    @Column(name = "CveProdServicio")
    private String cveProdServicio;	
    
    //para deliverys
    @Column(name = "PrecioPublicoDelivery")
    private BigDecimal precioPublicoDelivery;
    @Column(name = "DescuentoDelivery")
    private BigDecimal descuentoDelivery;
    @Column(name = "PrecioVentaDelivery")
    private BigDecimal precioVentaDelivery;
    //
    
	public Articulos() {
		super();
	}

	public Articulos(String nombre, BigDecimal precioPublico, BigDecimal porcDescuento,
			BigDecimal costo, BigDecimal precioVenta, BigDecimal margen, BigDecimal utilidad, BigDecimal porcentaIVA,
			BigDecimal porcentajeIEPS, Short receta, Integer cantidadUMM, Integer idDepartamento, Integer idFabricante,
			Integer idCategoria, Integer idMedidaSAT, Integer idPoliticaPrecio, Integer idFamilia, Integer idGrupoSSA,
			Short estatus, Integer usuarioCreacion, Date fechaCreacion, Integer usuarioModificacion,
			Date fechaModificacion, byte[] thumbnail, String thumbnailAux, String cveProdServicio) {
		super();
		//this.claveArticulo = claveArticulo;
		this.nombre = nombre;
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
		//this.idSucursal = idSucursal;
		Estatus = estatus;
		UsuarioCreacion = usuarioCreacion;
		FechaCreacion = fechaCreacion;
		UsuarioModificacion = usuarioModificacion;
		FechaModificacion = fechaModificacion;
		Thumbnail = thumbnail;
		ThumbnailAux = thumbnailAux;
		this.cveProdServicio = cveProdServicio;
	}

	
    //para vista
    private transient Integer existencias;
	
}
