package com.mx.api.globall.market.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbc_GTC_Almacenes", catalog = "dbo" )
public class Almacenes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "IdAlmacen")
	private Integer idAlmacen;
//	@Column(name = "IdSucursal", insertable = true, updatable = false)
//	private Integer idSucursal;
	@Column(name = "NombreAlmacen")
	private String nombreAlmacen;
	@Column(name = "Calle")
	private String calle;
	@Column(name = "Numero")
	private String numero;	
	@Column(name = "IdEstado")
	private Integer idEstado;
	@Column(name = "IdPais")
	private Integer idPais;
	@Column(name = "CodigoPostal")
	private String codigoPostal;
	@Column(name = "IdColonia")
	private Integer idColonia;
	@Column(name = "IdMunicipioDelegacion")
	private Integer idMunicipioDelegacion;
	@Column(name = "IndMultiSucursal")
	private Integer indMultiSucursal;
	@Column(name = "IndPublico")
	private Integer indPublico;	
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
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sucursales")
	Set<SucursalAlmacen>sucursalAlmacen;
	
	@OneToMany(targetEntity = Colonias.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "IdColonia" , referencedColumnName = "idColonia",  updatable = false)
	private List<Colonias>colonias;
	
	@OneToMany(targetEntity = Delegaciones.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "IdDelegacion" , referencedColumnName = "idMunicipioDelegacion",  updatable = false)
	private List<Delegaciones>delegaciones;
	
	@OneToMany(targetEntity = Paises.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "IdPais" , referencedColumnName = "idPais",  updatable = false)
	private List<Paises>paises;
	
	@OneToMany(targetEntity = Estados.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "IdEstado" , referencedColumnName = "idEstado",  updatable = false)
	private List<Estados>estados;
	
//	@OneToMany(targetEntity = PropietariosComercios.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "IdPropietario" , referencedColumnName = "idPropietario",  updatable = false)
//	private List<PropietariosComercios>propietarioComercio;
	
	@OneToMany(targetEntity = CodigosPostales.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Codigo" , referencedColumnName = "codigoPostal",  updatable = false)
	private List<CodigosPostales>codigosPostales;
}
