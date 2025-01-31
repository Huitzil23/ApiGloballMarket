package com.mx.api.globall.market.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.model.CompuestoActivo;
import com.mx.api.globall.market.repository.ICompuestoActivoRepository;

@Service
public class CompuestoActivoServiceImpl implements ICompuestoActivoService{

	@Autowired
	ICompuestoActivoRepository compActRepo;
	
	@Override
	public List<CompuestoActivo> findByStatus(Integer status, Integer idSucursal) {
		//return compActRepo.findByStatus(status);
		return compActRepo.findByStatusAndCompuestoActivoId_IdSucursal(status, idSucursal);
	}

	@Override
	public List<CompuestoActivo> findAll() {
		return compActRepo.findAll();
	}
	
	public List<CompuestoActivo>getAllCompuestoActivo(Integer idSucursal, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		 
	    //Page<CompuestoActivo> pagedResult = compActRepo.findAll(paging);
		Page<CompuestoActivo> pagedResult = compActRepo.findByCompuestoActivoId_IdSucursal(idSucursal,paging);
	    
	    if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<CompuestoActivo>();
        }
	}

	@Override
	public CompuestoActivo findByNombreAndDescripcion(String nombre, String descripcion, Integer idSucursal) {
		return compActRepo.findByNombreAndDescripcionAndCompuestoActivoId_IdSucursal(nombre, descripcion, idSucursal);
	}

	@Override
	public CompuestoActivo findByIdCompuestoActivo(Integer idCompuestoActivo, Integer idSucursal) {
		return compActRepo.findByCompuestoActivoId_IdCompuestoActivoAndCompuestoActivoId_IdSucursal(idCompuestoActivo, idSucursal);
	}

	@Override
	public List<CompuestoActivo> save(CompuestoActivo compuestoActivo) {
		CompuestoActivo comp = compActRepo.save(compuestoActivo);
		 List<CompuestoActivo> lstCompuesto = new ArrayList<CompuestoActivo>();
//		Page<CompuestoActivo> pagedResult = null;
		if (comp != null) {
			// lstCompuesto = compActRepo.findAll();
			Page<CompuestoActivo> paging = findPaginated(compuestoActivo.getCompuestoActivoId().getIdSucursal(), 1, 5, "compuestoActivoId.idCompuestoActivo", "asc");
			lstCompuesto = paging.getContent();

		}
//		if (pagedResult.hasContent()) {
//			return pagedResult.getContent();
//		} else {
//			return new ArrayList<CompuestoActivo>();
//		}
		// }
		 return lstCompuesto;
	}

	@Override
	public Integer findMaxIdCompuestoActivo(Integer idSucursal) {
		return compActRepo.getMaxIdCompuestoActivo(idSucursal);
	}

	@Override
	public Page<CompuestoActivo> getAll(Pageable pageable) {
		return compActRepo.findAll(pageable);
	}

	@Override
	public Page<CompuestoActivo> findPaginated(Integer idSucursal,int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		//return compActRepo.findAll(pageable);	
		return compActRepo.findByCompuestoActivoId_IdSucursal(idSucursal, pageable);
	}
}
