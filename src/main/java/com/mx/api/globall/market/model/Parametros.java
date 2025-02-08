package com.mx.api.globall.market.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tbc_GTC_Parametros",catalog = "dbo")
public class Parametros {
	
	public Parametros() {
	}
	
	@Id
	@Column(name = "IdParametro")
	private Integer idParametro;
	@Column(name = "ClaveParametro")
	private String claveParametro;
	@Column(name = "Descripcion")
	private String descripcion;
	@Column(name = "Valor")
	private String valor;
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
