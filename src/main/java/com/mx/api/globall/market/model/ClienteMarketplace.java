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
@Table(name = "tbc_GTC_ClienteMarketplace",catalog = "dbo")
public class ClienteMarketplace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdClienteMarket")
	private Integer idClienteMarket;
	@Column(name="IdMarketplace")
	private Integer idMarketplace;
	@Column(name="IdClienteExterno")
	private Integer idClienteExterno;
	@Column(name="NombreCliente")
	private String nombreCliente;
	@Column(name="ApellidoPaterno")
	private String apellidoPaterno;
	@Column(name="ApellidoMaterno")
	private String apellidoMaterno;
	@Column(name="Telefono")
	private String telefono;
	@Column(name="Genero")
	private String genero;
	@Column(name="Calle")
	private String calle;
	@Column(name="NumExterior")
	private String numExterior;
	@Column(name="NumInterior")
	private String numInterior;
	@Column(name="Colonia")
	private String colonia;
	@Column(name="MunicipioDelegacion")
	private String municipioDelegacion;
	@Column(name="Estado")
	private String estado;	
	@Column(name="Pais")
	private String pais;	
	@Column(name="CodigoPostal")
	private String codigoPostal;
	@Column(name="EntreCalle1")
	private String entreCalle1;
	@Column(name="EntreCalle2")
	private String entreCalle2;
	@Column(name="ReferenciaDir")
	private String referenciaDir;
	@Column(name="Notas")
	private String notas;
	@Column(name="IdAliasDir")
	private String idAliasDir;
	@Column(name="IdTipoEntrega")
	private String idTipoEntrega;
	@Column(name="Latitud")
	private String latitud;
	@Column(name="Longitud")
	private String longitud;
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
