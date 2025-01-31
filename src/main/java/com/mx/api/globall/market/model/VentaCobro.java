package com.mx.api.globall.market.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tbc_GTC_VentaCobro",catalog = "dbo")
public class VentaCobro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVentaCobro")
	private Integer idVentaCobro; 
    @Column(name = "IdVenta")
	private Integer idVenta; 
	@Column(name = "IdSucursal")
	private Integer idSucursal; 
	@Column(name = "IdTipoPago")
	private Integer idTipoPago;
	@Column(name = "Importe")
    private Float importe;
	@Column(name = "ImporteCobro")
    private Float importeCobro;
	@Column(name = "Cambio")
    private Float cambio;
	@Column(name = "NoTarjeta")
    private String noTarjeta;
	@Column(name = "FolioAutorizacionTarjeta")
    private String folioAutorizacionTarjeta;
	@Column(name = "AplicaComision")
    private Integer aplicaComision;
	@Column(name = "PorcentajeComisionAplicada")
    private Float porcentajeComisionAplicada;
	@Column(name = "ComisionAplicada")
    private Float comisionAplicada;
	@Column(name = "Referencia")
    private String referencia;
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
	public VentaCobro() {
		super();
	}
	
	@OneToOne(targetEntity = TipoPago.class, cascade = CascadeType.ALL)
    @JoinColumn(name="idTipoPago", referencedColumnName="idTipoPago",  updatable = false, insertable = false)
   	private TipoPago tipoPago;
	
}
