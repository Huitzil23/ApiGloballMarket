package com.mx.api.globall.market.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.bean.EstatusOrdenIn;
import com.mx.api.globall.market.bean.EstatusOrdenResponse;
import com.mx.api.globall.market.model.VentaMarketplace;
import com.mx.api.globall.market.repository.IVentaMarketplaceRepository;
import com.mx.api.globall.market.utils.EstatusMarketplace;
import com.mx.api.globall.market.model.DetalleVentaMarketplace;

@Service
public class VentasMarketplaceServiceImpl implements IVentasMarketplaceService{
	
	@Autowired
	IVentaMarketplaceRepository iVentaMarketplaceRepository;
	
	@Autowired
	private IControlVentasMarketplaceService controlMarketplace;
	
	@Autowired
	private IDetalleVentasMarketplaceService detalleVentaService;
	
	@Autowired
	ISseNotifyService iSseNotifyService;

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
			}else if(estatusOrdenResponse.getStatus_order() == EstatusMarketplace.EsperaConfirmacion.getValue()) {
				estatusOrdenResponse.setMessage("Espera confirmación");
			}
		}else {
			estatusOrdenResponse.setStatus_order(-1);
			estatusOrdenResponse.setMessage("El numero de venta no se encuentra registrado");
		}
		
		return estatusOrdenResponse;
	}
	
	/**
	 * Metodo para actualizar el estatus de la orden de compra
	 * @param estatusOrdenIn
	 * @return
	 */
	public EstatusOrdenResponse actualizaEstatusOrdenRecibido(EstatusOrdenIn estatusOrdenIn) {		
		EstatusOrdenResponse estatusOrdenResponse = new EstatusOrdenResponse();
		
		iSseNotifyService.sendNotify(String.valueOf(estatusOrdenIn.getIdSucursal()), "Nueva orden de venta");
		VentaMarketplace updateVentaMarket = new VentaMarketplace();
		updateVentaMarket = controlMarketplace.findByIdSucursalAndIdVenta(estatusOrdenIn.getIdSucursal(), estatusOrdenIn.getIdVenta());
		if(updateVentaMarket != null) {
			updateVentaMarket.setEstatus(EstatusMarketplace.Recibido.getValue());    		
			controlMarketplace.save(updateVentaMarket);
			
			com.mx.api.globall.market.model.DetalleVentaMarketplace detVta = new DetalleVentaMarketplace();
	    	detVta.setIdVentaMarket(updateVentaMarket.getIdVentaMarket());
	    	detVta.setIdMarketplace(updateVentaMarket.getIdMarketplace());
	    	detVta.setEstatus(EstatusMarketplace.Recibido.getValue());
	    	detVta.setFechaCreacion(new Date());
	    	detVta.setUsuarioCreacion(updateVentaMarket.getUsuarioCreacion());
	    	detalleVentaService.save(detVta);
	    	estatusOrdenResponse.setStatus_order(updateVentaMarket.getEstatus());
	    	estatusOrdenResponse.setMessage("El estatus de la orden se actualizo correctamente");
		}else {
			estatusOrdenResponse.setStatus_order(-1);
			estatusOrdenResponse.setMessage("El numero de venta no se encuentra registrado");
		}		
		
		return estatusOrdenResponse;
	}

}
