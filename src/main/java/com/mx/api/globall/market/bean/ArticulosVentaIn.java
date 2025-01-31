package com.mx.api.globall.market.bean;

import java.util.List;

//import javax.validation.constraints.NotNull;

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
public class ArticulosVentaIn {
	
	@JsonProperty(value = "IdSucursal", required = true)
	private Integer idSucursal;
	
	@JsonProperty(value="articulos", required = true)
	private List<ArticulosVentaAuxIn> articulos;
	
//	@JsonProperty(value = "ventaCobro", required = true)
//	private VentaCobroaux ventaCobro;

}
