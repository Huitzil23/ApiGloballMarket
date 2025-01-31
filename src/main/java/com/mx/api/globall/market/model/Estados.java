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
@Table(name = "tbc_GTC_Estado",catalog = "dbo")
public class Estados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdEstado")
	private Integer idEstado;
	@Column(name = "IdPais")
	private Integer idPais;
	@Column(name = "Estado")
	private String estado;
	@Column(name = "Abreviatura")
	private String abreviatura;
	@Column(name = "Capital")
	private String capital;
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion;
	@Column(name = "FechaCreacion")
	private Date fechaCreacion;
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion;
	@Column(name = "FechaModificacion")
	private Date fechaModificacion;

	

}
