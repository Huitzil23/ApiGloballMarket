package com.mx.api.globall.market.bean;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
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
public class ProductosVentas {
	
	@JsonProperty(value="idVenta", required = true)
	@NotNull 
	@NotEmpty
	private Integer idVenta;
	
	@JsonProperty(value="sku", required = true)
	@NotNull 
	@NotEmpty
	private String sku;
	
	@JsonProperty(value="nombre", required = true)
	@NotNull 
	@NotEmpty
	private String nombre;
	
	@JsonProperty(value="precioVenta", required = true)
	@NotNull
	private BigDecimal precioVenta;
	
	@JsonProperty(value="cantidad", required = true)
	@NotNull 
	@NotEmpty
	private Integer cantidad;

}
