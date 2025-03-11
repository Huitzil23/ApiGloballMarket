package com.mx.api.globall.market.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.model.VentaMarketplace;
import com.mx.api.globall.market.repository.IControlVentasMarketplaceRepository;


@Service
public class ControlVentasMarketplaceServiceImpl implements IControlVentasMarketplaceService {

	@Autowired
	IControlVentasMarketplaceRepository ventaRepo;
	
	@Override
	public List<Object> findVentasByIdSucursalAndIdVentaAndEstatus(Integer idSucursal, Integer idVenta,
			Integer estatus) {		
		return ventaRepo.findVentasByIdSucursalAndIdVentaAndEstatus(idSucursal, idVenta, estatus);
	}

	/*@Override
	public List<ArticulosVentaCareBoxAux> getArticulosVentaCareBox(Integer idSucursal, Integer idVenta,
			Integer estatus) {
		
		List<Object> lstObj = new ArrayList<Object>();
		List<ArticulosVentaCareBoxAux> lstArt = new ArrayList<ArticulosVentaCareBoxAux>();
		lstObj = findVentasByIdSucursalAndIdVentaAndEstatus(idSucursal, idVenta, estatus);
		
		for(Object objArt : lstObj) {
			Object[] obj = (Object[])objArt;
			ArticulosVentaCareBoxAux artVenta = new ArticulosVentaCareBoxAux();
			artVenta.setIdVenta((obj[0] != null ? (Integer)obj[0] : 0));
			artVenta.setIdSucursal((obj[1] != null ? (Integer)obj[1] : 0));
			artVenta.setIdCliente((obj[2] != null ? (Integer)obj[2] : 0));
			artVenta.setClaveArticulo((obj[3] != null ? (String)obj[3] : ""));
			artVenta.setNombre((obj[4] != null ? (String)obj[4] : ""));
			artVenta.setCantidad((obj[5] != null ? (Integer)obj[5] : 0));
			lstArt.add(artVenta);
		}
		
		return lstArt;
	}*/

	@Override
	public VentaMarketplace save(VentaMarketplace ventaMarketplace) {		
		return ventaRepo.save(ventaMarketplace);
	}

	@Override
	public Object findClienteByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta) {
		return ventaRepo.findClienteByIdSucursalAndIdVenta(idSucursal, idVenta);
	}

	/*@Override
	public ClienteCareBoxVenta getClienteVentaCareBox(Integer idSucursal, Integer idVenta) {
		Object objCliente = findClienteByIdSucursalAndIdVenta(idSucursal, idVenta);
		Object[] obj = (Object[])objCliente;
		ClienteCareBoxVenta infoCliente = new ClienteCareBoxVenta();
		infoCliente.setNombreCliente((obj[0] != null ? (String)obj[0] : ""));
		infoCliente.setApePaterno((obj[1] != null ? (String)obj[1] : ""));
		infoCliente.setApeMaterno((obj[2] != null ? (String)obj[2] : ""));
		infoCliente.setTelefono((obj[3] != null ? (String)obj[3] : ""));
		infoCliente.setCalle((obj[4] != null ? (String)obj[4] : ""));
		infoCliente.setNumExterior((obj[5] != null ? (String)obj[5] : ""));
		infoCliente.setNumInterior((obj[6] != null ? (String)obj[6] : ""));
		infoCliente.setColonia((obj[7] != null ? (String)obj[7] : ""));
		infoCliente.setDelegacion((obj[8] != null ? (String)obj[8] : ""));
		infoCliente.setCodigoPostal((obj[9] != null ? (String)obj[9] : ""));
		infoCliente.setEstado((obj[10] != null ? (String)obj[10] : ""));
		infoCliente.setPais((obj[11] != null ? (String)obj[11] : ""));
		infoCliente.setCalle1((obj[12] != null ? (String)obj[12] : ""));
		infoCliente.setCalle2((obj[13] != null ? (String)obj[13] : ""));
		infoCliente.setReferencia((obj[14] != null ? (String)obj[14] : ""));
		infoCliente.setNotas((obj[15] != null ? (String)obj[15] : ""));
		//Corregir tipo de entrega envio en duro  temporal 
		//infoCliente.setTipoEntrega((obj[16] != null ? (String)obj[16] : ""));		
		infoCliente.setTipoEntrega("Envío a Domicilio");
		infoCliente.setIdClienteCBox((obj[17] != null ? (Integer)obj[17] : 0));
		infoCliente.setIdVentaCare((obj[18] != null ? (Integer)obj[18] : 0));
		infoCliente.setUsuarioCreacion((obj[19] != null ? (Integer)obj[19] : 0));
		infoCliente.setFechaCreacion((obj[20] != null ? (Date)obj[20] : null));
		
		
		return infoCliente;
	}*/
	
	//RCF
		@Override
		public List<VentaMarketplace> findByIdSucursalAndEstatus(Integer IdSucursal, Integer estatus) {
			return ventaRepo.findByIdSucursalAndEstatus(IdSucursal, estatus);
		}

		@Override
		public List<VentaMarketplace> findByIdSucursal(Integer IdSucursal) {
			return ventaRepo.findByIdSucursal(IdSucursal);
		}

		@Override
		public VentaMarketplace findByIdSucursalAndIdVenta(Integer IdSucursal, Integer idVenta) {
			return ventaRepo.findByIdSucursalAndIdVenta(IdSucursal, idVenta);
		}
		
		@Override
		public List<VentaMarketplace> findAll() {
			return ventaRepo.findAll();
		}

		@Override
		public List<VentaMarketplace> findAllByFechaCreacionBetweenOrderByFechaCreacionDesc(Date startDate, Date endDate) {			
			return ventaRepo.findAllByFechaCreacionBetweenOrderByFechaCreacionDesc(startDate, endDate);
		}

		@Override
		public List<VentaMarketplace> findAllByIdSucursalAndEstatusAndFechaCreacionBetweenOrderByFechaCreacionDesc(Integer idSucursal, Integer estatus,
				Date startDate, Date endDate) {			
			return ventaRepo.findAllByIdSucursalAndEstatusAndFechaCreacionBetweenOrderByFechaCreacionDesc(idSucursal, estatus,startDate, endDate);
		}
}
