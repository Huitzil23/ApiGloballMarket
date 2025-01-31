package com.mx.api.globall.market.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.api.globall.market.model.CompuestoActivo;

public interface ICompuestoActivoService {
	
	List<CompuestoActivo> findByStatus(Integer status, Integer idSucursal);
	
	List<CompuestoActivo> findAll();
	
	CompuestoActivo findByIdCompuestoActivo(Integer idCompuestoActivo, Integer idSucursal);
		
	CompuestoActivo findByNombreAndDescripcion(String nombre, String descripcion, Integer idSucursal);
	
	List<CompuestoActivo>save(CompuestoActivo compuestoActivo);
	
	Integer findMaxIdCompuestoActivo(Integer idSucursal);
	
	Page<CompuestoActivo>getAll(Pageable pageable);
	Page<CompuestoActivo> findPaginated(Integer idSucursal,int pageNo, int pageSize, String sortField, String sortDirection);
	
	List<CompuestoActivo> getAllCompuestoActivo(Integer idSucursal, Integer pageNo, Integer pageSize, String sortBy);
	
}
