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
@Table(name = "tbc_GTC_PropietariosComercios" , catalog = "dbo")
public class PropietariosComercios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPropietario")
	private Integer idPropietario;
	@Column(name = "IdComercio")
	private Integer idComercio;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "ApellidoPaterno")
	private String apellidoPaterno;
	@Column(name = "ApellidoMaterno")
	private String apellidoMaterno;
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
