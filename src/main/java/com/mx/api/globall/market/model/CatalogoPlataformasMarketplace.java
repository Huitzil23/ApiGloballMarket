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
@Table(name = "tbc_GTC_CatalogoPlataformasMarketplace",catalog = "dbo")
public class CatalogoPlataformasMarketplace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdMarketplace")
	private Integer idMarketplace;
	@Column(name = "NombreMarketplace")
	private String nombreMarketplace;
	@Column(name = "ApiKey")
	private String apiKey;
	@Column(name="Estatus")
	private Integer estatus;
	@Column(name="UsuarioCreacion")
	private String usuarioCreacion;
	@Column(name="FechaCreacion")
	private Date fechaCreacion;
	@Column(name="UsuarioModificacion")	
	private String usuarioModificacion;
	@Column(name="FechaModificacion")
	private Date fechaModificacion;
	@Column(name = "EstiloClass")
	private String estiloClass;
}
