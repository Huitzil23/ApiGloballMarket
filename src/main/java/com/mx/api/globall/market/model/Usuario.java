package com.mx.api.globall.market.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbc_GTC_Usuario",catalog = "dbo")
public class Usuario {

	@Id
	@Column(name = "IdUsuario")
    private Integer idUsuario;
	@Column(name = "NoSucursal")
	private Integer noSucursal;
	@Column(name = "IdPerfil")
	private Integer idPerfil;
	@Column(name = "Usuario")
    private String usuario;
	@Column(name = "Password")
    private String password;
	@Column(name = "Nombre")
    private String nombre;
	@Column(name = "ApellidoPaterno")
    private String apellidoPaterno;
	@Column(name = "ApellidoMaterno")
    private String apellidoMaterno;
	@Column(name = "Estatus")
    private Integer estatus;
	@Column(name = "UsuarioCreacion")
    private Integer usuarioCreacion;
	@Column(name = "FechaCreacion")
    private Date fechaCreacion;
	@Column(name = "UsuarioModificacion")
    private Integer usuarioModificacion;
	@Column(name = "FechaModificacion")
    private Date fechaModificacion;
	@Column(name = "Celular")
    private String celular;
	@Column(name = "CorreoElectronico")
    private String correoElectronico;
	@Column(name = "ResetPassToken")
	private String resetPassToken;
	@Column(name = "WPassword")
    private String wPassword;
	@Column(name = "IdComercio")
	private Integer idComercio;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "tbc_GTC_UsuarioRoles",
        joinColumns = @JoinColumn(
            name = "IdUsuario", referencedColumnName = "IdUsuario"),
        inverseJoinColumns = @JoinColumn(
            name = "IdRol", referencedColumnName = "IdRol"))

    private Collection <Roles> roles;
	
	public Usuario() {}
}
