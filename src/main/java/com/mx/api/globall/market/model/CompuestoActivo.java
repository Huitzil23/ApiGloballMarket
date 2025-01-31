package com.mx.api.globall.market.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbc_GTC_CompuestoActivo", catalog = "dbo")
public class CompuestoActivo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*@Id
	@Column(name = "IdCompuestoActivo")
	private Integer idCompuestoActivo;*/
	@EmbeddedId
	private CompuestoActivoId compuestoActivoId;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Descripcion")
	private String descripcion;
	@Column(name = "Status")
	private Integer status;
	@Column(name = "UsuarioCreacion")
	private Integer usuarioCreacion;
	@Column(name = "FechaCreacion")
	private Date fechaCreacion;
	@Column(name = "UsuarioModificacion")
	private Integer usuarioModificacion;
	@Column(name = "FechaModificacion")
	private Date fechaModificacion;
	
	public CompuestoActivo() {
		super();
	}

	public CompuestoActivo(CompuestoActivoId compuestoActivoId, String nombre, String descripcion, Integer status,
			Integer usuarioCreacion, Date fechaCreacion, Integer usuarioModificacion, Date fechaModificacion) {
		super();
		this.compuestoActivoId = compuestoActivoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.status = status;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
	}
}
