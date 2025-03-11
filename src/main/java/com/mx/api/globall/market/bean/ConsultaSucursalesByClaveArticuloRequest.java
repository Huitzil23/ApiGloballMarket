package com.mx.api.globall.market.bean;

import java.util.List;

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
public class ConsultaSucursalesByClaveArticuloRequest {

	@JsonProperty(value="codigoPostalPaciente", required = true)
	@NotNull 
	@NotEmpty
	private String codigoPostalPaciente;	
	
	
	@JsonProperty(value="latitudPaciente", required = true)
	@NotNull 
	@NotEmpty
	private String latitudPaciente;
	
	@JsonProperty(value="longitudPaciente", required = true)
	@NotNull 
	@NotEmpty
	private String longitudPaciente;
	
	@JsonProperty(value="informacionPaciente", required = true)
	InformacionPacienteRequest informacionPaciente;
	
	@JsonProperty(value="clavesArticulos", required = true)	
	List<ClaveArticulosRequest>clavesArticulos;
}
