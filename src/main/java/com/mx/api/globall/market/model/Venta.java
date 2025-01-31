package com.mx.api.globall.market.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
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
@Table(name = "tbc_GTC_Venta",catalog = "dbo")
public class Venta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	/*@Id
    @Column(name = "IdVenta")
	private Integer idVenta; 
	@Column(name = "IdSucursal")
	private Integer idSucursal;*/ 
	@EmbeddedId
	private VentaId ventaId;
	@Column(name = "IdCliente")
	private Integer idCliente;
	@Column(name = "ImporteIEPS")
    private Float importeIEPS;
	@Column(name = "ImporteIVA")
    private Float importeIVA;
	@Column(name = "ImporteDescuento")
    private Float importeDescuento;
	@Column(name = "SubTotal")
    private Float subTotal;
	@Column(name = "SubTotalConDescuento")
    private Float subTotalConDescuento;
	@Column(name = "TotalVenta")
    private Float totalVenta;
	@Column(name = "IdTerminal")
    private Integer idTerminal;
	@Column(name = "IdCorteCaja")
    private Integer idCorteCaja;
	@Column(name = "Estatus")
    private Integer estatus;		
    @Column(name = "UsuarioCreacion")
    private Integer usuarioCreacion;
    @Column(name = "FechaCreacion")
    private Date fechaCreacion;	
    @Column(name = "UsuarioModificacion")
    private Integer usuarioModificacion;
    @Column(name = "FechaModificacion")
    private Date fechaModificacion;
    
    public Venta() {
		super();
	}

	@OneToMany(targetEntity = VentaDetalle.class, cascade = CascadeType.ALL)
   	@JoinColumns({
        @JoinColumn(name="idVenta", referencedColumnName="idVenta",  updatable = false, insertable = false),
        @JoinColumn(name="idSucursal", referencedColumnName="idSucursal",  updatable = false, insertable = false)
    })
   	private List<VentaDetalle> lstVentaDetalle;
	
	@OneToMany(targetEntity = VentaCobro.class, cascade = CascadeType.ALL)
   	@JoinColumns({
        @JoinColumn(name="idVenta", referencedColumnName="idVenta",  updatable = false, insertable = false),
        @JoinColumn(name="idSucursal", referencedColumnName="idSucursal",  updatable = false, insertable = false)
    })
   	private List<VentaCobro> lstVentaCobro;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
        @JoinColumn(name="usuarioCreacion", referencedColumnName="idUsuario",  updatable = false, insertable = false)
   	private Usuario usr;
	
	//para vista
	private transient Integer totalCantidad;
	private transient Float totalImporteDevolucion;
}
