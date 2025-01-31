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
@Table(name ="tbc_GTC_TipoDistribucion",catalog = "dbo")
public class TipoDistribucion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "IdTipoDistribucion")
	private Integer idTipoDistribucion;
	@Column(name = "TipoDistribucion")
	private String tipoDistribucion;
	@Column(name = "TD")
	private String td;
	@Column(name = "UsuarioCreacion")
	private Integer usuarioCreacion;
	@Column(name = "FechaCreacion")
	private Date fechaCreacion;

}
