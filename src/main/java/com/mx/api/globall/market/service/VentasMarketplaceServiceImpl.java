package com.mx.api.globall.market.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mx.api.globall.market.bean.ConsultaVentasBeepResponse;
import com.mx.api.globall.market.bean.EstatusOrdenIn;
import com.mx.api.globall.market.bean.EstatusOrdenResponse;
import com.mx.api.globall.market.bean.ProductosVentas;
import com.mx.api.globall.market.model.VentaMarketplace;
import com.mx.api.globall.market.repository.ICatalogoPlataformasMarketplaceRepository;
import com.mx.api.globall.market.repository.IVentaMarketplaceRepository;
import com.mx.api.globall.market.utils.EstatusMarketplace;
import com.mx.api.globall.market.model.CatalogoPlataformasMarketplace;
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
	
	@Autowired
	IClienteMarketplaceService iClienteMarketplaceService;
	
	@Autowired
	ICatalogoPlataformasMarketplaceRepository plataformasRepository;
	
	@Value("${app.general.nombre.marketplace.beep}")
    private String nombreMarketplaceBeep;

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
			
			DetalleVentaMarketplace detVta = new DetalleVentaMarketplace();
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

	
	
	@Override
	public List<Object> findVentasByIdMarketplace(Integer idMarketplace) {		
		return iVentaMarketplaceRepository.findVentasByIdMarketplace(idMarketplace);
	}
	
	public List<ConsultaVentasBeepResponse> consultaVentasByNombreMarketplace() {
		List<ConsultaVentasBeepResponse> lstVentas = new ArrayList<ConsultaVentasBeepResponse>();
		CatalogoPlataformasMarketplace catalogoPlataformasMarketplace = plataformasRepository.findIdMarketplaceByNombreMarketplace("Bepp");
		
		if(catalogoPlataformasMarketplace != null) {
			List<Object> lstObject = findVentasByIdMarketplace(catalogoPlataformasMarketplace.getIdMarketplace());
			ConsultaVentasBeepResponse cnVentas = new ConsultaVentasBeepResponse();
			
			List<ProductosVentas> lstProd = new ArrayList<ProductosVentas>();
			int idVen = 0;
			for (Object ob : lstObject) {
				Object[] obj = (Object[]) ob;
				cnVentas = new ConsultaVentasBeepResponse();
				int idSucursal = obj[0] != null ? (Integer) obj[0] : 0;
				int IdVenta = obj[1] != null ? (Integer) obj[1] : 0;
				if(idVen != IdVenta) {
					cnVentas.setIdSucursal(idSucursal);
					cnVentas.setIdVenta(IdVenta);
					cnVentas.setIdCliente(obj[2] != null ? (Integer) obj[2] : 0);
					cnVentas.setTotalVenta(obj[3] != null ? (BigDecimal) obj[3] : new BigDecimal(0));
					
					lstProd = new ArrayList<ProductosVentas>();
					List<Object> lstObjProd = findDetalleVentasByIdSucursalAndIdVenta(idSucursal, IdVenta);
					for (Object objProd : lstObjProd) {
						Object[] objP = (Object[]) objProd;
						ProductosVentas proVen = new ProductosVentas();						
						proVen.setIdVenta(objP[0] != null ? (Integer) objP[0] : 0);
						proVen.setSku((objP[1] != null ? (String) objP[1] : ""));
						proVen.setNombre((objP[2] != null ? (String) objP[2] : ""));
						proVen.setPrecioVenta(objP[3] != null ? (BigDecimal) objP[3] : new BigDecimal(0));
						proVen.setCantidad(objP[4] != null ? (Integer) objP[4] : 0);
						lstProd.add(proVen);
					}
					cnVentas.setProductos(lstProd);
					lstVentas.add(cnVentas);
				}
				idVen = IdVenta;			
			}
		}	
		
		return lstVentas;
	}

	@Override
	public List<Object> findDetalleVentasByIdSucursalAndIdVenta(Integer idSucursal, Integer idVenta) {		
		return iVentaMarketplaceRepository.findDetalleVentasByIdSucursalAndIdVenta(idSucursal, idVenta);
	}

}
