package com.mx.api.globall.market.bean;

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
public class EstatusOrdenIn {

	@JsonProperty(value="idSucursal", required = true)
	@NotNull 	
	private Integer idSucursal;
	
	@JsonProperty(value="idVenta", required = true)
	@NotNull 	
	private Integer idVenta;
}
