package com.mx.api.globall.market.model;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbc_GTC_VentaMarketplace",catalog = "dbo")
public class VentaMarketplace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVentaMarket")
	private Integer idVentaMarket;
	@Column(name = "IdMarketplace")
	private Integer idMarketplace;
	@Column(name = "IdSucursal")
	private Integer idSucursal;
	@Column(name = "IdVenta")
	private Integer idVenta;
	@Column(name = "IdCliente")
	private Integer idCliente;
	@Column(name = "Estatus")
	private Integer estatus;
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion;
	@Column(name = "FechaCreacion")
	private Date fechaCreacion;
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion;
	@Column(name = "FechaModificacion")
	private Date fechaModificacion;
	
/*@OneToOne(targetEntity = CatalogoPlataformasMarketplace.class, cascade = CascadeType.ALL)
	@JoinColumn(name="IdMarketplace")
	private CatalogoPlataformasMarketplace catalogoPlataformasMarketplace;*/

}
