package com.mx.api.globall.market.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class InformacionPacienteRequest {

	@JsonProperty(value="idPaciente", required = true)
	@NotNull 
	@NotEmpty
	private String idPaciente;	
	
	@JsonProperty(value="nombre", required = true)
	@NotNull 
	@NotEmpty
	private String nombre;	
	
	@JsonProperty(value="apellidoPaterno", required = true)
	@NotNull 
	@NotEmpty
	private String apellidoPaterno;
	
	@JsonProperty(value="apellidoMaterno", required = false)
	@NotNull 
	@NotEmpty
	private String apellidoMaterno;
	
	@JsonProperty(value="telefono", required = true)
	@NotNull 
	@NotEmpty
	private String telefono;
	
	@JsonProperty(value="calle", required = true)
	@NotNull 
	@NotEmpty
	private String calle;
	
	@JsonProperty(value="numeroExterior", required = true)
	@NotNull 
	@NotEmpty
	private String numeroExterior;
	
	@JsonProperty(value="numeroInterior", required = false)	
	@NotNull 
	@NotEmpty
	private String numeroInterior;
	
	@JsonProperty(value="colonia", required = true)
	@NotNull 
	@NotEmpty
	private String colonia;
	
	@JsonProperty(value="municipioDelegacion", required = true)
	@NotNull 
	@NotEmpty
	private String municipioDelegacion;
	
	@JsonProperty(value="estado", required = true)
	@NotNull 
	@NotEmpty
	private String estado;
}
