package com.mx.api.globall.market.bean;

import java.math.BigDecimal;
import java.util.List;

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
public class OrdenCompraRequest {
	
	@JsonProperty(value="vendorId", required = true)
	@NotNull 
	@NotEmpty
	private String vendorId;
	
	@JsonProperty(value="orderId", required = true)
	@NotNull 
	@NotEmpty
	private String orderId;
	
	@JsonProperty(value="totalAmount", required = true)
	@NotNull
	private BigDecimal totalAmount;
	
	@JsonProperty(value="paymetMethod", required = true)
	@NotNull 
	@NotEmpty
	private String paymetMethod;
	
	@JsonProperty(value="items", required = true)
	private List<ProductosCompra> items; 
}
