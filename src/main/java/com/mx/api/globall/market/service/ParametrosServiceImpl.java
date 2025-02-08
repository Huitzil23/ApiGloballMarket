package com.mx.api.globall.market.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.model.Parametros;
import com.mx.api.globall.market.repository.IParametrosRepository;

@Service
public class ParametrosServiceImpl implements IParametrosService{
	
	@Autowired 
	IParametrosRepository parametrosRepository;

	@Override
	public List<Parametros> findAll() {
		return parametrosRepository.findAll();
	}

	@Override
	public Parametros findByIdParametro(Integer idParametro) {
		return parametrosRepository.findByIdParametro(idParametro);
	}

	@Override
	public List<Parametros> save(Parametros parametros) {
		Integer idParametro = parametros.getIdParametro();
		Parametros existeParametro = parametrosRepository.findByIdParametro(idParametro);
		if(existeParametro != null) {
			existeParametro = parametrosRepository.save(parametros);
		}else {
			Integer idNuevoParametro = findMaxIdParametro();
			parametros.setIdParametro(idNuevoParametro + 1);
			existeParametro = parametrosRepository.save(parametros);
		}		
		
		List<Parametros> lstParametros = new ArrayList<Parametros>();
		if(existeParametro != null) {
			lstParametros = parametrosRepository.findAll();
		}	
		return lstParametros;
	}

	@Override
	public Parametros findByClaveParametroAndDescripcionAndValor(String claveParametro, String descripcion, String valor) {
		return parametrosRepository.findByClaveParametroAndDescripcionAndValor(claveParametro, descripcion, valor);
	}

	@Override
	public Parametros findByClaveParametro(String claveParametro) {
		return parametrosRepository.findByClaveParametro(claveParametro);
	}

	@Override
	public Parametros findByDescripcion(String descripcion) {
		return parametrosRepository.findByDescripcion(descripcion);
	}

	@Override
	public Integer findMaxIdParametro() {	
		return parametrosRepository.findMaxIdParametro();
	}

}
