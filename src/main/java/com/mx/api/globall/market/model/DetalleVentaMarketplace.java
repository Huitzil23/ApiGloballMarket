package com.mx.api.globall.market.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbc_GTC_DetalleVentaMarketplace",catalog = "dbo")
public class DetalleVentaMarketplace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDetVentaMarket")
	private Integer idDetVentaMarket;
	@Column(name = "IdMarketplace")
	private Integer idMarketplace;
	@Column(name = "IdVentaMarket")
	private Integer idVentaMarket;
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
	

}
