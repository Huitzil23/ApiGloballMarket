package com.mx.api.globall.market.bean;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArticulosVentaAuxIn {

	@JsonProperty(value = "sku", required = true)
	@NotNull
	private String sku;
	@JsonProperty(value = "cantidad", required = true )
	@NotNull
	private Integer cantidad;
}
