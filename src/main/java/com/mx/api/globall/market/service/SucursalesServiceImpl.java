package com.mx.api.globall.market.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.bean.BuscaSucursalCodigoPostalResponse;
import com.mx.api.globall.market.bean.ConsultaSucursalesProductosRequest;
import com.mx.api.globall.market.bean.ConsultaSucursalesProductosResponse;
import com.mx.api.globall.market.bean.Productos;
import com.mx.api.globall.market.bean.SucursalesAux;
import com.mx.api.globall.market.model.ClienteMarketplace;
import com.mx.api.globall.market.model.Sucursales;
import com.mx.api.globall.market.repository.IClienteMarketplaceRepository;
import com.mx.api.globall.market.repository.ISucursalesRepository;

@Service
public class SucursalesServiceImpl implements ISucursalesService{
	
	@Autowired
	ISucursalesRepository sucursalesRepository;
	
	@Autowired
	IClienteMarketplaceRepository clienteRepo;
	
	@Value("${app.general.id.marketplace.beep}")
    private Integer idMarketplaceBeep;
		
	private final Integer ACTIVO = 1;

	@Override
	public List<Sucursales> findByCodigoPostalAndEstatus(String codigoPostal, Integer estatus) {	
		return sucursalesRepository.findByCodigoPostalAndEstatus(codigoPostal, estatus);
	}

	@Override
	public BuscaSucursalCodigoPostalResponse consultaSucursalesByCodigoPostalAndEstatus(String codigoPostal,
			Integer estatus) {
		List<Sucursales> lstSucursales = new ArrayList<Sucursales>();
		BuscaSucursalCodigoPostalResponse bscSucCodResponse = new BuscaSucursalCodigoPostalResponse();		
		lstSucursales = sucursalesRepository.findByCodigoPostalAndEstatus(codigoPostal, estatus);
		if(lstSucursales.size() > 0) {
			bscSucCodResponse.setCode(201);
			bscSucCodResponse.setMensaje("");
		}else {
			bscSucCodResponse.setCode(200);
			bscSucCodResponse.setMensaje("No existen sucursales disponibles para el código postal");
		}
		
		return bscSucCodResponse;
	}

	@Override
	public List<Object> findSucursalProductoByCodigoPostal(String codigoPostal) {		
		return sucursalesRepository.findSucursalProductoByCodigoPostal(codigoPostal);
	}

	@Override
	public List<ConsultaSucursalesProductosResponse> buscaSucursalesProductosByCodigoPostal(String codigoPostal) {
		List<ConsultaSucursalesProductosResponse>lstConsulta = new ArrayList<ConsultaSucursalesProductosResponse>();
		List<Object> lstObj = findSucursalProductoByCodigoPostal(codigoPostal);
		if(lstObj != null) {
			for(Object object:lstObj) {
				Object[]obj = (Object[])object;
				ConsultaSucursalesProductosResponse cSuc = new ConsultaSucursalesProductosResponse();
				Productos prod = new Productos();
				cSuc.setIdSucursal((obj[0] != null ? (Integer)obj[0] : 0));
				prod.setSku((obj[1] != null ? (String)obj[1] : ""));
				prod.setNombre((obj[2] != null ? (String)obj[2] : ""));
				prod.setDescripcion((obj[3] != null ? (String)obj[3] : ""));
				prod.setExistencia((obj[4] != null ? (Integer)obj[4] : 0));
				cSuc.getProductos().add(prod);				
			}
		}		
		return lstConsulta;
	}

	@Override
	public List<Object> findIdSucursalesCercaCPDistancia(String latitud, String longitud, float distancia) {		
		return sucursalesRepository.findIdSucursalesCercaCPDistancia(latitud, longitud, distancia);
	}

	@Override
	public List<ConsultaSucursalesProductosResponse> buscaIdSucursalesCercaCPDistancia(ConsultaSucursalesProductosRequest consultaSucursalesProductosRequest, float distancia) {
		List<SucursalesAux>lstIdSuc = new ArrayList<SucursalesAux>();
		List<Object> lstObj = new ArrayList<Object>();
		@SuppressWarnings("unused")
		String cp = consultaSucursalesProductosRequest.getCodigoPostalPaciente();
		String latitud = consultaSucursalesProductosRequest.getLatitudPaciente();
		String longitud = consultaSucursalesProductosRequest.getLongitudPaciente();
		System.out.println("Lista id compuesto activo " + consultaSucursalesProductosRequest.getCompuestosActivos().toString());
		
		
		
		
		if(consultaSucursalesProductosRequest.getInformacionPaciente() != null) {
			ClienteMarketplace validaCliente = clienteRepo.findIdClienteMarketByIdMarketplaceAndIdClienteExterno(idMarketplaceBeep, 
					Integer.parseInt(consultaSucursalesProductosRequest.getInformacionPaciente().getIdPaciente()));
			ClienteMarketplace clienteSave = new ClienteMarketplace();
			if(validaCliente != null && validaCliente.getIdClienteMarket() != null) {
				clienteSave.setIdClienteMarket(validaCliente.getIdClienteMarket());
				clienteSave.setUsuarioCreacion(validaCliente.getUsuarioCreacion());
				clienteSave.setFechaCreacion(validaCliente.getFechaCreacion());	
				clienteSave.setUsuarioModificacion(String.valueOf(idMarketplaceBeep));
				clienteSave.setFechaModificacion(new Date());
				
			}else {
				clienteSave.setUsuarioCreacion(String.valueOf(idMarketplaceBeep));
				clienteSave.setFechaCreacion(new Date());	
			}
			
			clienteSave.setIdMarketplace(idMarketplaceBeep);
			clienteSave.setIdClienteExterno(Integer.parseInt(consultaSucursalesProductosRequest.getInformacionPaciente().getIdPaciente()));
			clienteSave.setNombreCliente(consultaSucursalesProductosRequest.getInformacionPaciente().getNombre());
			clienteSave.setApellidoPaterno(consultaSucursalesProductosRequest.getInformacionPaciente().getApellidoPaterno());
			clienteSave.setApellidoMaterno(consultaSucursalesProductosRequest.getInformacionPaciente().getApellidoMaterno());
			clienteSave.setTelefono(consultaSucursalesProductosRequest.getInformacionPaciente().getTelefono());
			clienteSave.setCalle(consultaSucursalesProductosRequest.getInformacionPaciente().getCalle());
			clienteSave.setNumExterior(consultaSucursalesProductosRequest.getInformacionPaciente().getNumeroExterior());
			clienteSave.setNumInterior(consultaSucursalesProductosRequest.getInformacionPaciente().getNumeroInterior());
			clienteSave.setColonia(consultaSucursalesProductosRequest.getInformacionPaciente().getColonia());
			clienteSave.setMunicipioDelegacion(consultaSucursalesProductosRequest.getInformacionPaciente().getMunicipioDelegacion());
			clienteSave.setEstado(consultaSucursalesProductosRequest.getInformacionPaciente().getEstado());
			clienteSave.setCodigoPostal(cp);
			clienteSave.setLatitud(latitud);
			clienteSave.setLongitud(longitud);
			clienteSave.setEstatus(ACTIVO);
					
			
			clienteSave = clienteRepo.save(clienteSave);
		}
		
		
		lstObj = findIdSucursalesCercaCPDistancia(latitud, longitud, distancia);
		for(Object object:lstObj) {
			Object[]obj = (Object[])object;
			SucursalesAux scAuc = new SucursalesAux();
			scAuc.setIdSucursal((obj[0] != null ? (Integer)obj[0] : 0));
			scAuc.setCodigoPostal((obj[1] != null ? (String)obj[1] : ""));
			scAuc.setNombreComercio((obj[2] != null ? (String)obj[2] : ""));
			scAuc.setNombreSucursal((obj[3] != null ? (String)obj[3] : ""));
			scAuc.setCalle((obj[4] != null ? (String)obj[4] : ""));
			scAuc.setNumeroExterior((obj[5] != null ? (String)obj[5] : ""));
			scAuc.setColonia((obj[6] != null ? (String)obj[6] : ""));
			scAuc.setMunicipioDelegacion((obj[7] != null ? (String)obj[7] : ""));
			scAuc.setEstado((obj[8] != null ? (String)obj[8] : ""));
			scAuc.setPais((obj[9] != null ? (String)obj[9] : ""));
			lstIdSuc.add(scAuc);
		}
		
		String idCA = "";
		for(int i = 0;i < consultaSucursalesProductosRequest.getCompuestosActivos().size(); i++) {
		
			idCA = consultaSucursalesProductosRequest.getCompuestosActivos().get(i).getIdCompuestoActivo() + "," + idCA;
		}
		List<String> lstCompuestos = new ArrayList<String>(Arrays.asList(String.valueOf(idCA).split(",")));
		
		List<ConsultaSucursalesProductosResponse>lstConsulta = new ArrayList<ConsultaSucursalesProductosResponse>();
		for(int i=0; i < lstIdSuc.size(); i++) {
		
			List<Object>lstOb = findArticulosByIdSucursalAndIdCompuestoActivo(lstIdSuc.get(i).getIdSucursal(), lstCompuestos);
			
			if(lstOb != null && lstOb.size() > 0) {
				List<Productos> lstPrd = new ArrayList<Productos>();
				ConsultaSucursalesProductosResponse csRs = new ConsultaSucursalesProductosResponse();
				for(Object ob: lstOb) {
					csRs = new ConsultaSucursalesProductosResponse();
					Object[]obj = (Object[])ob;					
					Productos prd = new Productos();					
					prd.setSku((obj[0] != null ? (String)obj[0] : ""));
					prd.setNombre((obj[1] != null ? (String)obj[1] : ""));
					prd.setDescripcion((obj[1] != null ? (String)obj[1] : ""));
					prd.setPrecioPublico((obj[2] != null ? (BigDecimal)obj[2] : new BigDecimal(0)));
					prd.setExistencia((obj[3] != null ? (Integer)obj[3] : 0));
					prd.setRequiereReceta((obj[6] != null ? Boolean.valueOf(obj[6].toString()) : false));
					prd.setEsControlado((obj[8] != null ? Boolean.valueOf(obj[8].toString()): false));
					lstPrd.add(prd);	
					
					csRs.setIdSucursal(lstIdSuc.get(i).getIdSucursal());					
					csRs.setCodigoPostal(lstIdSuc.get(i).getCodigoPostal());
					csRs.setNombreComercio(lstIdSuc.get(i).getNombreComercio());
					csRs.setNombreSucursal(lstIdSuc.get(i).getNombreSucursal());
					csRs.setCalle(lstIdSuc.get(i).getCalle());
					csRs.setNumeroExterior(lstIdSuc.get(i).getNumeroExterior());
					csRs.setColonia(lstIdSuc.get(i).getColonia());
					csRs.setMunicipioDelegacion(lstIdSuc.get(i).getMunicipioDelegacion());
					csRs.setEstado(lstIdSuc.get(i).getEstado());
					csRs.setPais(lstIdSuc.get(i).getPais());
					csRs.setProductos(lstPrd);					
				}
				lstConsulta.add(csRs);
			}
			
			
		}		
		return lstConsulta;
	}

	@Override
	public List<Object> findArticulosByIdSucursalAndIdCompuestoActivo(Integer idSucursal, List<String> lstCompuestosActivos) {		
		return sucursalesRepository.findArticulosByIdSucursalAndIdCompuestoActivo(idSucursal, lstCompuestosActivos);
	}

}
