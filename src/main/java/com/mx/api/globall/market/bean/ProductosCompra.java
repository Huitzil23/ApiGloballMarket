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
public class ProductosCompra {
	
	@JsonProperty(value="sku", required = true)
	@NotNull 
	@NotEmpty
	private String sku;
	
	@JsonProperty(value="price", required = true)
	@NotNull
	private BigDecimal price;
	
	@JsonProperty(value="quantity", required = true)
	@NotNull 
	private Integer quantity;
}
