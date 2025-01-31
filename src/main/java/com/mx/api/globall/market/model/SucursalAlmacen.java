package com.mx.api.globall.market.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="tbc_GTC_SucursalAlmacen",catalog = "dbo")
public class SucursalAlmacen implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "IdSucursalAlmacen")
	private Integer idSucursalAlmacen;
	@Column(name = "IdSucursal")
	private Integer idSucursal;
	@Column(name = "IdAlmacen")
	private Integer idAlmacen;
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
	
	@ManyToOne
	@JoinColumn(name = "idSucursal", referencedColumnName = "idSucursal", insertable = false, updatable = false)
	Sucursales sucursales;
	
	@ManyToOne
	@JoinColumn(name = "idAlmacen", referencedColumnName = "idAlmacen", insertable = false, updatable = false )
	Almacenes almacenes;

}
