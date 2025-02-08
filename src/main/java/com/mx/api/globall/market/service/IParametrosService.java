package com.mx.api.globall.market.service;

import java.util.List;

import com.mx.api.globall.market.model.Parametros;

public interface IParametrosService {

	Integer findMaxIdParametro();
	
	List<Parametros> findAll();	
	
	Parametros findByIdParametro(Integer idParametro);
	
	List<Parametros> save(Parametros parametros);
	
	Parametros findByClaveParametro(String claveParametro);
	
	Parametros findByDescripcion(String descripcion);
	
	Parametros findByClaveParametroAndDescripcionAndValor(String claveParametro, String descripcion, String valor);
}
