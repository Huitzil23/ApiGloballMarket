package com.mx.api.globall.market.model;

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
@Table(name = "tbc_GTC_MarketplaceEstatus",catalog = "dbo")
public class MarketPlaceEstatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdMarketplaceEstatus")
	private Integer idMarketplaceEstatus;
	@Column(name = "IdMarketplace")
	private Integer idMarketplace;
	@Column(name = "Estatus")
	private Integer estatus;
	@Column(name = "Descripcion")
	private String descripcion;
	@Column(name = "Estilo")
	private String estilo;
	@Column(name = "Comentario")
	private String comentario;

}
