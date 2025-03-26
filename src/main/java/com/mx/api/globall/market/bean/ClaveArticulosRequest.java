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
public class ClaveArticulosRequest {

	@JsonProperty(value="sku", required = true)
	@NotNull 
	@NotEmpty
	private String sku;
	
	@JsonProperty(value="cantidad", required = true)
	@NotNull 
	@NotEmpty
	private Integer cantidad;
}
