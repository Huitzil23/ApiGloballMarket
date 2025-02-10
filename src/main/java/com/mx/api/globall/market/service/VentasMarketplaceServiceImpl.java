package com.mx.api.globall.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.bean.EstatusOrdenIn;
import com.mx.api.globall.market.bean.EstatusOrdenResponse;
import com.mx.api.globall.market.model.VentaMarketplace;
import com.mx.api.globall.market.repository.IVentaMarketplaceRepository;
import com.mx.api.globall.market.utils.EstatusMarketplace;

@Service
public class VentasMarketplaceServiceImpl implements IVentasMarketplaceService{
	
	@Autowired
	IVentaMarketplaceRepository iVentaMarketplaceRepository;

	@Override
	public VentaMarketplace findEstatusByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta) {
		return iVentaMarketplaceRepository.findEstatusByIdSucursalAndIdVenta(idSucursal, idVenta);
	}

	/**
	 * Metodo para consultar el estatus de la orden de compra
	 * @param estatusOrdenIn
	 * @return
	 */
	public EstatusOrdenResponse consultaEstatusOrdenMarketplace(EstatusOrdenIn estatusOrdenIn) {
		
		VentaMarketplace ventaMarketplace = findEstatusByIdSucursalAndIdVenta(estatusOrdenIn.getIdSucursal(), estatusOrdenIn.getIdVenta());
		EstatusOrdenResponse estatusOrdenResponse = new EstatusOrdenResponse();
		if(ventaMarketplace != null) {
			estatusOrdenResponse.setStatus_order(ventaMarketplace.getEstatus());
			if(estatusOrdenResponse.getStatus_order() == EstatusMarketplace.Recibido.getValue()){				
				estatusOrdenResponse.setMessage("Recibido");			
			}else if(estatusOrdenResponse.getStatus_order() == EstatusMarketplace.Aceptado.getValue()) {
				estatusOrdenResponse.setMessage("Aceptado");
			}else if(estatusOrdenResponse.getStatus_order() == EstatusMarketplace.Procesando.getValue()) {
				estatusOrdenResponse.setMessage("Procesando");
			}else if(estatusOrdenResponse.getStatus_order() == EstatusMarketplace.EnCamino.getValue()) {
				estatusOrdenResponse.setMessage("EnCamino");
			}else if(estatusOrdenResponse.getStatus_order() == EstatusMarketplace.Entregado.getValue()) {
				estatusOrdenResponse.setMessage("Entregado");
			}
		}else {
			estatusOrdenResponse.setStatus_order(-1);
			estatusOrdenResponse.setMessage("El numero de venta no se encuentra registrado");
		}
		
		return estatusOrdenResponse;
	}

}
