package com.mx.api.globall.market.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.api.globall.market.model.CompuestoActivo;
import com.mx.api.globall.market.model.CompuestoActivoId;

@Repository
public interface ICompuestoActivoRepository extends JpaRepository<CompuestoActivo, CompuestoActivoId>{

	List<CompuestoActivo> findByStatusAndCompuestoActivoId_IdSucursal(Integer status, Integer idSucursal);
	
	CompuestoActivo findByCompuestoActivoId_IdCompuestoActivoAndCompuestoActivoId_IdSucursal(Integer idCompuestoActivo, Integer IdSucursal);
	
	CompuestoActivo findByNombreAndDescripcionAndCompuestoActivoId_IdSucursal(String nombre, String descripcion, Integer idSucursal);
	
	//@Query(value = "Select max(idCompuestoActivo) FROM CompuestoActivo")
	@Query(value = "Select ISNULL(max(idCompuestoActivo),0) FROM tbc_GTC_CompuestoActivo Where IdSucursal = ?1 ", nativeQuery = true)
	Integer getMaxIdCompuestoActivo(Integer idSucursal);
	
	Page<CompuestoActivo> findByCompuestoActivoId_IdSucursal(Integer IdSucursal, Pageable pageable);
	

}
