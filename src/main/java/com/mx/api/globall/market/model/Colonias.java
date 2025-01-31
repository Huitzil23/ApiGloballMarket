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
@Table(name = "tbc_GTC_Colonia",catalog = "dbo")
public class Colonias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdColonia")
	private Integer idColonia;
	@Column(name = "IdCodigoPostal")
	private Integer idCodigoPostal;
	@Column(name = "IdDelegacion")
	private Integer idDelegacion;
	@Column(name = "NobreColonia")
	private String nobreColonia;
	@Column(name = "Status")
	private Integer status;
	@Column(name = "IdSucursal")
	private Integer idSucursal;
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion;
	@Column(name = "FechaCreacion")
	private Date fechaCreacion;
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion;
	@Column(name = "FechaModificacion")
	private Date fechaModificacion;

	@OneToOne(targetEntity = Delegaciones.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idDelegacion" , referencedColumnName = "idDelegacion",  updatable = false, insertable=false)
	private Delegaciones delegacion;
}
