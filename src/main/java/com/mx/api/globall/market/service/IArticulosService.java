package com.mx.api.globall.market.service;

import java.io.IOException;
//import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpEntity;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.mx.api.globall.market.bean.ArticulosConsultaAux;
import com.mx.api.globall.market.bean.Articulosaux;
import com.mx.api.globall.market.bean.CalculoPoliticaPrecioIN;
import com.mx.api.globall.market.bean.ConsultaDetalleArticulos;
//import com.mx.globall.ws.beans.ResponseExecProc;
import com.mx.api.globall.market.model.Articulos;

public interface IArticulosService {
	List<Articulos> findAll();
	Articulos save(Articulosaux articulo,MultipartFile file) throws IOException;
	void delArticulo(String cveArticulo, Integer idSucursal);
	//Articulos consultaArticulo(String cveArticulo);
	Articulos update(Articulosaux articulo, MultipartFile file, Articulos articuloOri) throws IOException;
	List<Articulos> findAll(Integer sucursal);
	Articulos consultaArticulo(String cveArticulo, Integer idSucursal);
	String calculaPoliticaPrecio(CalculoPoliticaPrecioIN datos, Integer tipoPolitica) throws IOException;
	List<Articulos> findAllByIdSucursalAndClaveArticuloOrderByNombreAsc(Integer sucursal, String cveArticulo);
	List<Articulos> findAllByIdSucursalAndNombreContainsOrderByNombreAsc(Integer sucursal,String nombre);
	//List<Articulos>findAllByIdSucursalAndClaveArticuloContainsOrderByNombreAsc(Integer sucursal, String cveArticulo);*/
	List<Articulos>findAllByIdContainsOrderByNombreAsc(String cveArticulo,Integer sucursal);	
	/*List<Articulos>findAllByIdSucursalAndClaveArticuloContainsOrNombreContainsOrderByNombreAsc(Integer sucursal, Integer cveArticulo, String nombre);*/
	List<Articulos>findAllByIdSucursalAndClaveArticuloContainsOrNombreContainsOrderByNombreAsc(Integer sucursal, String cveArticulo, String nombre);
	
	//void save(HttpServletRequest request,MultipartFile file,Integer idSucursal,Integer idComercio,Integer idUsuario)throws SQLException, ClassNotFoundException, IOException, Exception;
	//ResponseExecProc agregaInventarioArticulos(Integer IdSucursal,Integer UsuarioCreacion);

	List<String>findArticulosTopVendidos(Integer sucursal);
	List<Articulos> findAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(Integer idSucursal);
	List<ArticulosConsultaAux> pvFindAllByIdSucursalAndNombreContainsOrderByNombreAsc(Integer sucursal, String nombre);
	ArticulosConsultaAux pvConsultaArticulo(String cveArticulo, Integer idSucursal);
	List<ArticulosConsultaAux> pvFindAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(Integer idSucursal);
	//Integer getEstatusCarga(Integer sucursal, Integer comercio);
	//void insertaEstatusCarga(Integer sucursal, Integer comercio, Integer idUsuario);
	//Integer getExistenciaArticuloSucursalAlmacen(Integer sucursal, String cveArticulo, Integer idAlmacen);
	
	List<Object> getArticulos();
	List<ConsultaDetalleArticulos> consultaDetalleArticulos();
	String sendArticulosJson();
	

}
