package com.mx.api.globall.market.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbc_GTC_VentaDetalle",catalog = "dbo")
public class VentaDetalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	/*@Id
	@Column(name = "IdVentaDetalle")
	private Integer idVentaDetalle; 
    @Column(name = "IdVenta")
	private Integer idVenta; 
	@Column(name = "IdSucursal")
	private Integer idSucursal;*/
	@EmbeddedId
	private VentaDetalleId ventaDetalleId;
	@Column(name = "IdVenta")
	private Integer idVenta; 
	@Column(name = "ClaveArticulo")
	private String claveArticulo;
	@Column(name = "Cantidad")
	private Integer cantidad;
	@Column(name = "PrecioVenta")
    private Float precioVenta;
	@Column(name = "PrecioPublico")
    private Float precioPublico;
	@Column(name = "PorcentajeIVA")
    private Float porcentajeIVA;
	@Column(name = "ImporteIVA")
    private Float importeIVA;
	@Column(name = "PorcentajeIEPS")
    private Float porcentajeIEPS;
	@Column(name = "ImporteIEPS")
    private Float importeIEPS;
	@Column(name = "PorcentajeDescuento")
    private Float porcentajeDescuento;
	@Column(name = "ImporteDescuento")
    private Float importeDescuento;
	@Column(name = "SubTotal")
    private Float subTotal;
	@Column(name = "SubTotalConDescuento")
    private Float subTotalConDescuento;
	@Column(name = "TotalVenta")
    private Float totalVenta;
	@Column(name = "Costo")
	private Float costo;
	@Column(name = "Margen")
	private Float margen;
	@Column(name = "Utilidad")
	private Float utilidad;
	@Column(name = "Estatus")
    private Integer Estatus;		
    @Column(name = "UsuarioCreacion")
    private Integer UsuarioCreacion;
    @Column(name = "FechaCreacion")
    private Date FechaCreacion;	
    @Column(name = "UsuarioModificacion")
    private Integer UsuarioModificacion;
    @Column(name = "FechaModificacion")
    private Date FechaModificacion;
	
    public VentaDetalle() {
		super();
	}

	@OneToOne(targetEntity = Articulos.class, cascade = CascadeType.ALL)
   	@JoinColumns({
        @JoinColumn(name="claveArticulo", referencedColumnName="claveArticulo",  updatable = false, insertable = false),
        @JoinColumn(name="idSucursal", referencedColumnName="idSucursal",  updatable = false, insertable = false)
    })
   	private Articulos articulo;
	
}
