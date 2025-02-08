package com.mx.api.globall.market.service;

//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
//import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.NumberToTextConverter;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
//import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
//import com.microsoft.sqlserver.jdbc.SQLServerBulkCopyOptions;
import com.mx.api.globall.market.bean.ArticulosConsultaAux;
import com.mx.api.globall.market.bean.ArticulosIdAux;
import com.mx.api.globall.market.bean.Articulosaux;
import com.mx.api.globall.market.bean.CalculoPoliticaPrecioIN;
import com.mx.api.globall.market.bean.CalculoPoliticaPrecioOUT;
import com.mx.api.globall.market.bean.ConsultaDetalleArticulos;
//import com.mx.globall.ws.beans.ResponseExecProc;
import com.mx.api.globall.market.model.Articulos;
import com.mx.api.globall.market.model.ArticulosId;
//import com.mx.global.model.ControlCargaMasivaArticulos;
//import com.mx.global.model.ArticulosTmp;
import com.mx.api.globall.market.model.Parametros;
import com.mx.api.globall.market.repository.IArticulosRepository;
//import com.mx.global.repository.IControlCargaMasivaArticulosRepository;
import com.mx.api.globall.market.utils.CalculaPoliticaPrecios;
//import com.mx.globall.ws.utils.FilesUtils;

@Service
public class ArticulosServiceImpl implements IArticulosService{
	private static final Logger logger = LoggerFactory.getLogger(ArticulosServiceImpl.class);
	@Autowired
	private IArticulosRepository articulosRepo;
	@Autowired
	private IParametrosService paramService;
	@Value("${spring.datasource.url}")
    private String urlJdbc;
	
	@Value("${spring.datasource.username}")
    private String usrJdbc;
	
	@Value("${spring.datasource.password}")
    private String passJdbc;
	
	/*@Autowired
	private IControlCargaMasivaArticulosRepository controlCargaRepo;*/
	
	@Override
	public List<Articulos> findAll(Integer sucursal) {
		//List<Articulos> lstArt = articulosRepo.findAllByIdSucursalOrderByNombreAsc(sucursal);//
		List<Articulos> lstArt = articulosRepo.findAllByArticulosIdOrderByNombreAsc(sucursal);
		
		return lstArt;
	}
	@Override
	public void delArticulo(String cveArticulo, Integer idSucursal) {
		Optional<Articulos> obj = articulosRepo.findById(new ArticulosId(cveArticulo, idSucursal));//findById(cveArticulo);
		articulosRepo.delete(obj.get());
	}
	@Override
	public Articulos consultaArticulo(String cveArticulo, Integer idSucursal) {
		Articulos objResp = null;
		/*Optional<Articulos> lstArt = articulosRepo.findByClaveArticuloAndIdSucursal(cveArticulo,idSucursal);*/ 
		Optional<Articulos> lstArt = articulosRepo.findById(new ArticulosId(cveArticulo, idSucursal));
		if(lstArt.isPresent()) {
			objResp= lstArt.get();
		}
		return objResp;
	}
	/*@Override
	public Articulos consultaArticulo(String cveArticulo) {
		Articulos objResp = null;
		Optional<Articulos> lstArt = articulosRepo.findById(cveArticulo);
		if(!lstArt.isEmpty()) {
			objResp= lstArt.get();
		}
		return objResp;
	}*/
	@Transactional
	@Override
	public Articulos save(Articulosaux articulo, MultipartFile file) throws IOException {
		byte[] btes = file.getBytes();
		Articulos art = new Articulos();
		ArticulosId artId = new ArticulosId();
		artId.setClaveArticulo(articulo.getClaveArticulo());
		art.setThumbnail(btes);
		art.setNombre(articulo.getNombre());
		art.setPrecioPublico(articulo.getPrecioPublico());
		art.setPorcDescuento(articulo.getPorcDescuento());
		art.setCosto(articulo.getCosto());
		art.setPrecioVenta(articulo.getPrecioVenta());
		art.setMargen(articulo.getMargen());
		art.setUtilidad(articulo.getUtilidad());
		art.setPorcentaIVA(articulo.getPorcentaIVA());
		art.setPorcentajeIEPS(articulo.getPorcentajeIEPS());
		art.setReceta(articulo.getReceta());
		art.setCantidadUMM(articulo.getCantidadUMM());
		art.setIdDepartamento(articulo.getIdDepartamento());
		art.setIdFabricante(articulo.getIdFabricante());
		art.setIdCategoria(articulo.getIdCategoria());
		art.setIdMedidaSAT(articulo.getIdMedidaSAT());
		art.setIdPoliticaPrecio(articulo.getIdPoliticaPrecio());
		art.setIdFamilia(articulo.getIdFamilia());
		art.setIdGrupoSSA(articulo.getIdGrupoSSA());
		artId.setIdSucursal(articulo.getIdSucursal());
		art.setEstatus(articulo.getEstatus());
		art.setUsuarioCreacion(articulo.getUsuarioCreacion());
		art.setFechaCreacion(new Date());
		art.setUsuarioModificacion(articulo.getUsuarioModificacion());
		art.setFechaModificacion(articulo.getFechaModificacion());
		art.setCveProdServicio(articulo.getCveProdServicio());
		art.setArticulosId(artId);
		art.setPrecioPublicoDelivery(articulo.getPrecioPublico());
		//art.setDescuentoDelivery(articulo.getPorcDescuento());
		art.setDescuentoDelivery(new BigDecimal(0));
		art.setPrecioVentaDelivery(articulo.getPrecioVenta());
		Articulos artSave = articulosRepo.save(art);
		return artSave;
	}

	@Override
	@Transactional
	public Articulos update(Articulosaux articulo, MultipartFile file, Articulos art) throws IOException {
		if(!articulo.getClaveArticulo().equals(art.getArticulosId().getClaveArticulo())) {
			art.getArticulosId().setClaveArticulo(articulo.getClaveArticulo());
			art.setFechaCreacion(new Date());
			art.setUsuarioCreacion(articulo.getUsuarioCreacion());
		}else {
			art.setUsuarioModificacion(articulo.getUsuarioModificacion());
			art.setFechaModificacion(articulo.getFechaModificacion());
		}
		byte[] btes = file.getBytes();
		art.setThumbnail(file.getSize()<=0 ? art.getThumbnail() : btes);
		art.setNombre(articulo.getNombre());
		art.setPrecioPublico(articulo.getPrecioPublico());
		art.setPorcDescuento(articulo.getPorcDescuento());
		art.setCosto(articulo.getCosto());
		art.setPrecioVenta(articulo.getPrecioVenta());
		art.setMargen(articulo.getMargen());
		art.setUtilidad(articulo.getUtilidad());
		art.setPorcentaIVA(articulo.getPorcentaIVA());
		art.setPorcentajeIEPS(articulo.getPorcentajeIEPS());
		art.setReceta(articulo.getReceta());
		art.setCantidadUMM(articulo.getCantidadUMM());
		art.setIdDepartamento(articulo.getIdDepartamento());
		art.setIdFabricante(articulo.getIdFabricante());
		art.setIdCategoria(articulo.getIdCategoria());
		art.setIdMedidaSAT(articulo.getIdMedidaSAT());
		art.setIdPoliticaPrecio(articulo.getIdPoliticaPrecio());
		art.setIdFamilia(articulo.getIdFamilia());
		art.setIdGrupoSSA(articulo.getIdGrupoSSA());
		art.getArticulosId().setIdSucursal(articulo.getIdSucursal());
		art.setEstatus(articulo.getEstatus());
		art.setCveProdServicio(articulo.getCveProdServicio());
		//if(0 == art.getPrecioPublicoDelivery().compareTo(new BigDecimal(0))) {
			art.setPrecioPublicoDelivery(articulo.getPrecioPublico());
			art.setDescuentoDelivery(new BigDecimal(0));
			art.setPrecioVentaDelivery(articulo.getPrecioVenta());
		//}
		Articulos artSave = articulosRepo.save(art);
		return artSave;
	}
	@Override
	public List<Articulos> findAll() {
		return articulosRepo.findAllByOrderByNombreAsc();
	}
	@Override
	public String calculaPoliticaPrecio(CalculoPoliticaPrecioIN datos, Integer tipoPolitica) throws IOException {
		String jsonResponse = "";
    	CalculoPoliticaPrecioOUT response = null;
    	switch (tipoPolitica) {
		case 1:
			if(datos.getConIva()) {
				Parametros paramVal = paramService.findByIdParametro(1);//Iva 
				datos.setPorcentajeIVA(Float.parseFloat(paramVal.getValor()));
			}
			response = CalculaPoliticaPrecios.Fijo(datos);
			break;
		case 2:
			if(datos.getConIva()) {
				Parametros paramVal = paramService.findByIdParametro(1);//Iva 
				datos.setPorcentajeIVA(Float.parseFloat(paramVal.getValor()));
			}
			response = CalculaPoliticaPrecios.Descuento(datos);
			break;
		case 3:
			if(datos.getConIva()) {
				Parametros paramVal = paramService.findByIdParametro(1);//Iva 
				datos.setPorcentajeIVA(Float.parseFloat(paramVal.getValor()));
			}
			response = CalculaPoliticaPrecios.Margen(datos);
			break;
		case 4:
			if(datos.getConIva()) {
				Parametros paramVal = paramService.findByIdParametro(1);//Iva 
				datos.setPorcentajeIVA(Float.parseFloat(paramVal.getValor()));
			}
			response = CalculaPoliticaPrecios.Utilidad(datos);
			break;
		default:
			response = new CalculoPoliticaPrecioOUT((float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0);
			break;
		}
    	System.out.println(response);
    	jsonResponse = new ObjectMapper().writeValueAsString(response);
    	return jsonResponse;
	}
	@Override
	public List<Articulos> findAllByIdSucursalAndClaveArticuloOrderByNombreAsc(Integer sucursal, String cveArticulo) {
		/*return articulosRepo.findAllByIdSucursalAndClaveArticuloOrderByNombreAsc(sucursal, cveArticulo);*/
		return articulosRepo.findAllByArticulosIdOrderByNombreAsc(new ArticulosId(cveArticulo,sucursal));
	}
	@Override
	public List<Articulos> findAllByIdSucursalAndNombreContainsOrderByNombreAsc(Integer sucursal, String nombre) {
		//return articulosRepo.findAllByIdSucursalAndNombreContainsOrderByNombreAsc(sucursal,nombre);//
		List<Articulos> lstArt = articulosRepo.findAllByArticulosIdAndNombreContainsOrderByNombreAsc(sucursal,nombre);
		return lstArt;
	}

	@Override
	/*public List<Articulos> findAllByIdSucursalAndClaveArticuloContainsOrderByNombreAsc(Integer sucursal,*/
	public List<Articulos> findAllByIdContainsOrderByNombreAsc(
			String cveArticulo,Integer sucursal) {		
		/*return articulosRepo.findAllByIdSucursalAndClaveArticuloContainsOrderByNombreAsc(sucursal, cveArticulo);*/
		return articulosRepo.findAllByArticulosIdContainsOrderByNombreAsc(sucursal,cveArticulo);
		
	}
	@Override
	public List<Articulos> findAllByIdSucursalAndClaveArticuloContainsOrNombreContainsOrderByNombreAsc(
			Integer sucursal, String cveArticulo, String nombre) {
		//return articulosRepo.findAllByIdSucursalAndClaveArticuloContainsOrNombreContainsOrderByNombreAsc(sucursal, cveArticulo, nombre);
		return articulosRepo.findAllByArticulosIdContainsOrNombreContainsOrderByNombreAsc(sucursal, cveArticulo, nombre);
	}
	
	//@Override
	//public void save(HttpServletRequest request,MultipartFile file, Integer idSucursal, Integer idComercio, Integer idUsuario) throws Exception {
		//try {
		      //List<ArticulosTmp> tutorials = getArticulosExcelTmp(file.getInputStream(),idSucursal,idUsuario);
		      //articulosRepo.saveAll(tutorials);
			//setDB(file.getInputStream(),idSucursal,idUsuario);
			//
		//insertaEstatusCarga(idSucursal, idComercio, idUsuario); 
		//String nameFile = convetirXlsCsv(request,file.getInputStream(),idSucursal);
		//setBulk(request,nameFile);
		    //} catch (IOException e) {
		     // throw new RuntimeException("Error al insertar datos de archivo: " + e.getMessage());
		    //}
	//}
	
	/*public List<ArticulosTmp> getArticulosExcelTmp(InputStream is, Integer idSucursal, Integer idUsuario) {
		String sheetName = "Articulos";

	    try {
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheet(sheetName);
	      Iterator<Row> rows = sheet.iterator();

	      List<ArticulosTmp> articulos = new ArrayList<ArticulosTmp>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        //Encabezados
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        ArticulosTmp objArticulos = new ArticulosTmp();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();
	          
	          switch (cellIdx) {
	          case 0:
	        	  String cveProd = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setClaveArticulo(cveProd);
	            break;
	          case 1:
	        	  objArticulos.setNombre(currentCell.getStringCellValue());
	            break;
	          case 2:
	        	  Double pp = currentCell.getNumericCellValue();
	        	  objArticulos.setPrecioPublico(pp==null?new BigDecimal(0):new BigDecimal(pp));
	            break;
	          case 3:
	        	  Double pd = currentCell.getNumericCellValue();
	        	  objArticulos.setPorcDescuento(pd==null?new BigDecimal(0):new BigDecimal(pd));
	            break;
	          case 4:
	        	  Double costo = currentCell.getNumericCellValue();
	        	  objArticulos.setCosto(costo==null?new BigDecimal(0):new BigDecimal(costo));
	            break;
	          case 5:
	        	  Double pv = currentCell.getNumericCellValue();
	        	  objArticulos.setPrecioVenta(pv==null?new BigDecimal(0):new BigDecimal(pv));
	            break;
	          case 6:
	        	  Double margen = currentCell.getNumericCellValue();
	        	  objArticulos.setMargen(margen==null?new BigDecimal(0):new BigDecimal(margen));
	            break;
	          case 7:
	        	  Double utilidad = currentCell.getNumericCellValue();
	        	  objArticulos.setUtilidad(utilidad==null?new BigDecimal(0):new BigDecimal(utilidad));
	            break;
	          case 8:
	        	  String porcIva = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setPorcentaIVA(porcIva.isEmpty()?new BigDecimal(0):new BigDecimal(porcIva));
	            break;
	          case 9:
	        	  String porcIeps = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setPorcentajeIEPS(porcIeps.isEmpty()?new BigDecimal(0):new BigDecimal(porcIeps));
	            break;
	          case 10:
	        	  String receta = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setReceta(receta.isEmpty()?(short)0:Short.parseShort(receta));
	            break;
	          case 11:
	        	  String cantidadUmm = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setCantidadUMM(cantidadUmm.isEmpty()?0:Integer.parseInt(cantidadUmm));
	            break;
	          case 12:
	        	  String idDep = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setIdDepartamento(idDep.isEmpty()?0:Integer.parseInt(idDep));
	            break;
	          case 13:
	        	  String idFab = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setIdFabricante(idFab.isEmpty()?0:Integer.parseInt(idFab));
	            break;
	          case 14:
	        	  String idCateg = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setIdCategoria(idCateg.isEmpty()?0:Integer.parseInt(idCateg));
	            break;
	          case 15:
	        	  String idMedidaSat = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setIdMedidaSAT(idMedidaSat.isEmpty()?0:Integer.parseInt(idMedidaSat));
	            break;
	          case 16:
	        	  String idPolitica = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setIdPoliticaPrecio(idPolitica.isEmpty()?0:Integer.parseInt(idPolitica));
	            break;
	          case 17:
	        	  String idGrupossa = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setIdGrupoSSA(idGrupossa.isEmpty()?0:Integer.parseInt(idGrupossa));
	            break;
	          case 18:
	        	  String idFam = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	        	  objArticulos.setIdFamilia(idFam.isEmpty()?0:Integer.parseInt(idFam));
	            break;
	          default:
	            break;
	          }
	          
	          cellIdx++;
	          
	        }

	        objArticulos.setUsuarioCreacion(idUsuario);
	        objArticulos.setEstatus(Short.parseShort("1"));
	        objArticulos.setIdSucursal(idSucursal);
	        articulos.add(objArticulos);
	      }

	      workbook.close();

	      return articulos;
	    } catch (IOException e) {
	      throw new RuntimeException("Error en tratamiento de datos del archivo: " + e.getMessage());
	    }
	  }*/
	
	/*public void setDB(InputStream is, Integer idSucursal, Integer idUsuario) throws SQLException {
        int batchSize = 2500;
 
        Connection con = primaryDataSource.getConnection();
 
        try {
            long start = System.currentTimeMillis();

            Workbook workbook = new XSSFWorkbook(is);
 
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
 
            con.setAutoCommit(false);
  
            String sql = "INSERT INTO tbc_GTC_ArticulosTmp (ClaveArticulo, Nombre, PrecioPublico, IdSucursal) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);    
             
            int count = 0;
             
            rowIterator.next();
             
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
 
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
 
                    int columnIndex = currentCell.getColumnIndex();
 
                    switch (columnIndex) {
	                case 0:
	  	        	  String cveProd = NumberToTextConverter.toText(currentCell.getNumericCellValue());
	  	        	  statement.setString(1, cveProd);
	  	            break;
	  	          	case 1:
	  	          		statement.setString(2, currentCell.getStringCellValue());
	  	            break;
	  	          	case 2:
	  	        	  Double pp = currentCell.getNumericCellValue();
	  	        	  statement.setBigDecimal(3, pp==null?new BigDecimal(0):new BigDecimal(pp));
	  	            break;
                    }
 
                }
                statement.setInt(4, idSucursal);
                 
                statement.addBatch();
                 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }              
 
            }
 
            workbook.close();
            statement.executeBatch();
            con.commit();
            con.close();
             
            long end = System.currentTimeMillis();
            System.out.printf("Importación correcta in %d ms\n", (end - start));
             
        } catch (IOException ex1) {
            System.out.println("Error en lectura de archivo");
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            System.out.println("Error en Base de Datos");
            ex2.printStackTrace();
        }
 
    }*/
	
	/*@SuppressWarnings("resource")
	public void setBulk(HttpServletRequest request, String name) throws SQLException, ClassNotFoundException, IOException {
		String pathSeparador = System.getProperty("file.separator");
		ServletContext servletContext = request.getSession().getServletContext();
		String dirFile = servletContext.getRealPath(pathSeparador);
		String folderName = "files";
		File fileAP = new File(dirFile + pathSeparador + folderName + pathSeparador + name);

		try {
		long startTime = System.currentTimeMillis();
		SQLServerBulkCSVFileRecord fileRecord = null;  

		fileRecord = new SQLServerBulkCSVFileRecord(fileAP.getPath(), true); 
		fileRecord.addColumnMetadata(1, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(2, null, java.sql.Types.VARCHAR, 15, 0);  
		fileRecord.addColumnMetadata(3, null, java.sql.Types.VARCHAR, 250, 0); 
		fileRecord.addColumnMetadata(4, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(5, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(6, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(7, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(8, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(9, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(10, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(11, null, java.sql.Types.DECIMAL, 18, 2);
		fileRecord.addColumnMetadata(12, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(13, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(14, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(15, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(16, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(17, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(18, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(19, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(20, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(21, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(22, null, java.sql.Types.INTEGER, 11, 0);
		fileRecord.addColumnMetadata(23, null, java.sql.Types.INTEGER, 11, 0);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		Connection connection = DriverManager.getConnection(urlJdbc, usrJdbc, passJdbc);
		
		SQLServerBulkCopyOptions copyOptions = new SQLServerBulkCopyOptions();  
		copyOptions.setBatchSize(100000); 
		copyOptions.setBulkCopyTimeout(0);
		copyOptions.setTableLock(true);  

		SQLServerBulkCopy bulkCopy =  new SQLServerBulkCopy(connection);
		bulkCopy.setBulkCopyOptions(copyOptions);  
		bulkCopy.setDestinationTableName("tbc_GTC_LoadTempArticulos");
		bulkCopy.writeToServer(fileRecord);

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Bulk tiempo de ejecución: "+totalTime + "ms");
		FilesUtils.borrarArchivo(fileAP.getPath());
		connection.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
			//e.printStackTrace();
			throw e;
		}
	}
	
    @SuppressWarnings("resource")
	public String convetirXlsCsv(HttpServletRequest request, InputStream is, Integer idSucursal) throws Exception {

    	String pathSeparador = System.getProperty("file.separator");
		ServletContext servletContext = request.getSession().getServletContext();
		String dirFile = servletContext.getRealPath(pathSeparador);
		String folderName = "files";
		File fileAP = new File(dirFile + pathSeparador + folderName + pathSeparador + "InventarioGlobal2"+Math.random()+".csv");

        File outputFile = new File(fileAP.getPath());

        StringBuffer data = new StringBuffer();
        int numReg = 0;
        try {
        	Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
 
            Iterator<Row> rowIterator = sheet.iterator();
            //int numReg = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                data.append( numReg + ",");
                
                while (cellIterator.hasNext()) {
 
                    Cell cell = cellIterator.next();
 
                    switch (cell.getCellType()) {
                    case BOOLEAN:
                        data.append(cell.getBooleanCellValue() + ",");
                        break;
 
                    case NUMERIC:
                    	String valStr = NumberToTextConverter.toText(cell.getNumericCellValue());
                    	valStr = valStr.trim().isEmpty() ? "0" : valStr;
                    	data.append(valStr.replace(",", "").replace("'", "").trim() + ",");
                        break;
 
                    case STRING:
                        data.append(cell.getStringCellValue().replace(",", "").replace("'", "").trim() + ",");
                        break;
                    case BLANK:
                        data.append(" " + ",");
                        break;
 
                    default:
                        data.append(cell + ",");
                    }
                }
                data.append( idSucursal + ",");
                data.append('\n');
                numReg++;
            }
 
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(data.toString().getBytes());
            fos.close();
 
        } catch (Exception e) {
        	String error = "Error en registro: " + numReg+ " -->"+e.getMessage();
        	logger.error(error);
        	throw new Exception(error); 
        }
        System.out.println("Conversion de Excel a CSV correcta:"+outputFile.getName());
        return outputFile.getName();
    }

	@SuppressWarnings("unused")
	@Override
	public ResponseExecProc agregaInventarioArticulos(Integer IdSucursal, Integer UsuarioCreacion) {
		ResponseExecProc response = new ResponseExecProc();
		try {
			int responseSP = articulosRepo.agregaInventarioArticulos(IdSucursal, UsuarioCreacion);
			response.setRespuesta(1);
        	response.setMessage("Se ha procesado el archivo exitosamente");
			return response;
		} catch (Exception e) {
			if (e.getCause().getCause() instanceof SQLException) {
	            SQLException es = (SQLException) e.getCause().getCause();
	            return new ResponseExecProc(-1, es.getMessage());
	        }else {
	        	return new ResponseExecProc(-1, "Error en proceso de ejecución de alta inventario artículos");
	        }
		}
	}*/
	@Override
	public List<ArticulosConsultaAux> pvFindAllByIdSucursalAndNombreContainsOrderByNombreAsc(Integer sucursal, String nombre) {
		List<Object> lstArt = articulosRepo.pvFindAllByArticulosIdAndNombreContainsOrderByNombreAsc(sucursal,nombre);
		List<ArticulosConsultaAux> lstResp =parseConsultaBusqueda(lstArt, sucursal);
		return lstResp;
	}
	public List<ArticulosConsultaAux> parseConsultaBusqueda(List<Object> objConsulta, Integer idSucursal) {
		List<ArticulosConsultaAux> lstResponse = new ArrayList<ArticulosConsultaAux>();
		for(Object object : objConsulta){
			Object[] obj = (Object[])object;
			ArticulosConsultaAux art = new ArticulosConsultaAux();
			ArticulosIdAux artId = new ArticulosIdAux();
		    artId.setClaveArticulo(obj[0] != null ? (String)obj[0] : "");
			byte[] thumbnail = obj[1] != null ? (byte[])obj[1] : null;
			art.setThumbnail(thumbnail);
			String base64Encoded = "";
        	if(art.getThumbnail() != null) {
        		base64Encoded = Base64.getMimeEncoder().encodeToString(art.getThumbnail());
        	}
        	art.setThumbnailAux(base64Encoded);
        	BigDecimal pventa = obj[6] != null ? (BigDecimal)obj[6] : new BigDecimal(0);
        	art.setPrecioVenta(pventa);
			art.setNombre(obj[2] != null ? (String)obj[2] : "");
			artId.setIdSucursal(obj[20] != null ? (Integer)obj[20] : 0);
			//art.setExistencias(obj[28] != null ? (Integer)obj[28] : 0);
			art.setPrecioPublicoDelivery(obj[28] != null ? (BigDecimal)obj[28] : new BigDecimal(0));
			art.setDescuentoDelivery(obj[29] != null ? (BigDecimal)obj[29] : new BigDecimal(0));
			art.setPrecioVentaDelivery(obj[30] != null ? (BigDecimal)obj[30] : new BigDecimal(0));
			art.setExistencias(obj[31] != null ? (Integer)obj[31] : 0);
			/*Object objArt = articulosRepo.pvFindByArticulosExistencias(idSucursal, artId.getClaveArticulo(), idAlmacen);
			Integer obj2 = (Integer)objArt;
			art.setExistencias(obj2 != null ? obj2 : 0);*/
			art.setArticulosId(artId);
			lstResponse.add(art);
		}
		return lstResponse;
	}
	@Override
	public ArticulosConsultaAux pvConsultaArticulo(String cveArticulo, Integer idSucursal) {
		ArticulosConsultaAux objResp = new ArticulosConsultaAux();
		Object objArt = articulosRepo.pvFindByArticulosIdOrderByNombreAsc(idSucursal, cveArticulo);
		if(objArt == null) {
			objResp.setArticulosId(new ArticulosIdAux());
		}else {
			objResp = parseConsultaBusquedaCve(objArt, idSucursal, cveArticulo);
		}
		return objResp;
	}
	public ArticulosConsultaAux parseConsultaBusquedaCve(Object objConsulta, Integer idSucursal, String cveArticulo) {
		Object[] obj = (Object[])objConsulta;
		ArticulosConsultaAux art = new ArticulosConsultaAux();
		ArticulosIdAux artId = new ArticulosIdAux();
	    artId.setClaveArticulo(obj[0] != null ? (String)obj[0] : "");
		byte[] thumbnail = obj[1] != null ? (byte[])obj[1] : null;
		art.setThumbnail(thumbnail);
		String base64Encoded = "";
    	if(art.getThumbnail() != null) {
    		base64Encoded = Base64.getMimeEncoder().encodeToString(art.getThumbnail());
    	}
    	art.setThumbnailAux(base64Encoded);
    	BigDecimal pventa = obj[6] != null ? (BigDecimal)obj[6] : new BigDecimal(0);
    	art.setPrecioVenta(pventa);
		art.setNombre(obj[2] != null ? (String)obj[2] : "");
		artId.setIdSucursal(obj[20] != null ? (Integer)obj[20] : 0);
		//art.setExistencias(obj[28] != null ? (Integer)obj[28] : 0);
		art.setPrecioPublicoDelivery(obj[28] != null ? (BigDecimal)obj[28] : new BigDecimal(0));
		art.setDescuentoDelivery(obj[29] != null ? (BigDecimal)obj[29] : new BigDecimal(0));
		art.setPrecioVentaDelivery(obj[30] != null ? (BigDecimal)obj[30] : new BigDecimal(0));
		art.setExistencias(obj[31] != null ? (Integer)obj[31] : 0);
		/*Object objArt = articulosRepo.pvFindByArticulosExistencias(idSucursal, cveArticulo, idAlmacen);
		Integer obj2 = (Integer)objArt;
		art.setExistencias(obj2 != null ? obj2 : 0);*/
		art.setArticulosId(artId);
		return art;
	}
	
	@Override
	public List<String> findArticulosTopVendidos(Integer sucursal) {
		List<Object> lstResponse = articulosRepo.findArticulosTopVendidos(sucursal);
		List<String> lstStringArt = parseConsultaTop(lstResponse);
		return lstStringArt;
	}
	
	public List<String> parseConsultaTop(List<Object> objConsulta) {
		List<String> lstResponse = new ArrayList<String>();
		for(Object object : objConsulta){
			Object[] obj = (Object[])object;
		    String cveArt = (obj[0] != null ? (String)obj[0] : "");
			lstResponse.add(cveArt);
		}
		return lstResponse;
	}
	
	@Override
	public List<Articulos> findAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(Integer idSucursal) {
		List<String> lstCvesArt = findArticulosTopVendidos(idSucursal);
		List<Articulos> lstArt =articulosRepo.findAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(idSucursal,lstCvesArt);
		return lstArt;
	}
	@Override
	
	public List<ArticulosConsultaAux> pvFindAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(Integer idSucursal) {
		
		Locale locale = new Locale("mx", "MX");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		Date fecha1 = new Date();
		String date1 = dateFormat.format(fecha1);
		logger.info("Inicio: "+ date1);
		List<Object> lstArt =articulosRepo.pvFindAllByArticulosIdIdSucursalAndArticulosIdClaveArticuloIn(idSucursal);
		List<ArticulosConsultaAux> lstResp =parseConsultaBusqueda(lstArt, idSucursal);
		Date fecha2 = new Date();
		String date2 = dateFormat.format(fecha2);
		logger.info("Fin: "+ date2);
		long diferencia=fecha2.getTime()-fecha1.getTime();
		long segundos = TimeUnit.MILLISECONDS.toSeconds(diferencia); 
		logger.info("Diferencia consulta articulos top: "+ segundos+" seg.");
		return lstResp;
	}
	/*@Override
	public Integer getEstatusCarga(Integer sucursal, Integer comercio) {
		int estatus=articulosRepo.getEstatusCarga(sucursal, comercio);
		return estatus;
	}
	@Override
	public void insertaEstatusCarga(Integer sucursal, Integer comercio, Integer idUsuario) {
		ControlCargaMasivaArticulos data = new ControlCargaMasivaArticulos();
		data.setIdSucursal(sucursal);
		data.setIdComercio(comercio);
		data.setEstatus(2);
		data.setUsuarioCreacion(idUsuario);
		data.setFechaCreacion(new Date());
		controlCargaRepo.save(data);
	}
	@Override
	public Integer getExistenciaArticuloSucursalAlmacen(Integer sucursal, String cveArticulo, Integer idAlmacen) {
		int existencia = articulosRepo.getExistenciaArticuloSucursalAlmacen(sucursal, cveArticulo, idAlmacen);
		return existencia;
	}*/
	@Override
	public List<Object> getArticulos() {
		return articulosRepo.getArticulos();
	}
	@Override
	public List<ConsultaDetalleArticulos> consultaDetalleArticulos() {
		List<Object> lstObj = getArticulos();
		List<ConsultaDetalleArticulos>lstDetA = parseConsultaArticulos(lstObj);
		return lstDetA;
	}
	
	
	public List<ConsultaDetalleArticulos>parseConsultaArticulos(List<Object> lstObjArticulos){
		List<ConsultaDetalleArticulos> lstArt = new ArrayList<ConsultaDetalleArticulos>();
		
		for(Object object : lstObjArticulos) {
			Object[] obj = (Object[])object;
			ConsultaDetalleArticulos detA = new ConsultaDetalleArticulos();
			detA.setIdSucursal(obj[0] != null ? (Integer)obj[0] : 0);
			detA.setClaveArticulo(obj[1] != null ? (String)obj[1] : "");
			detA.setNombre(obj[2] != null ? (String)obj[2] : "");
			detA.setPrecioPublico(obj[3] != null ? (BigDecimal)obj[3] : new BigDecimal(0));
			detA.setPrecioVenta(obj[4] != null ? (BigDecimal)obj[4] : new BigDecimal(0));
			detA.setExistencias(obj[5] != null ? (Integer)obj[5] : 0);
			
			lstArt.add(detA);
		}
		
		return lstArt;
	}
	
	public JsonArray returnItemSend(List<Object> lstConsulta) {
		JsonArray jArrayReturn = new JsonArray();
		
		for(Object objConsulta : lstConsulta) {
			JsonObject itemsObject = new JsonObject();
			Object[] obj = (Object[])objConsulta;
			itemsObject.addProperty("id_sucursal", obj[0].toString());
			itemsObject.addProperty("clave_articulo", obj[1].toString());
			itemsObject.addProperty("nombre", obj[2].toString());
			itemsObject.addProperty("precio_publico", obj[3].toString());
			itemsObject.addProperty("precio_venta", obj[4].toString());
			itemsObject.addProperty("existencias", obj[5].toString());
		
			jArrayReturn.add(itemsObject);
		}
		
		return jArrayReturn;
	}
	@Override
	public String sendArticulosJson() {
		
		List<Object> lstConsulta = getArticulos();
		JsonObject ordenObject = new JsonObject();
		JsonArray jArray = new JsonArray(); 
		jArray = returnItemSend(lstConsulta);
		
		ordenObject.add("records",  jArray);
		String dataSend = ordenObject.toString();
		
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> request = new HttpEntity<Object>(dataSend, headers);
        //String response = target.request().post(request).readEntity(String.class);
		return dataSend;
	}
}