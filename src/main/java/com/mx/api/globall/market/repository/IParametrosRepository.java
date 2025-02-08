package com.mx.api.globall.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.Parametros;

@Repository
public interface IParametrosRepository extends JpaRepository<Parametros, Integer> {
	
	@Query(value = "Select ISNULL(max(IdParametro), 0) FROM tbc_GTC_Parametros" , nativeQuery = true)
	Integer findMaxIdParametro();
		
	Parametros findByIdParametro(Integer idParametro);	
	
	Parametros findByClaveParametro(String claveParametro);
	
	Parametros findByDescripcion(String descripcion);
	
	Parametros findByClaveParametroAndDescripcionAndValor(String claveParametro, String descripcion, String valor);

}
