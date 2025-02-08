
package com.mx.api.globall.market.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import com.mx.global.beans.UsuarioFunciones;
//import com.mx.global.beans.UsuarioModulosFunciones;

public class Utiles {
    public static String obtenerFechaYHoraActual() {
        String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    
	/*public static UsuarioModulosFunciones getFuncionesModulo(List<UsuarioModulosFunciones> lst, Integer idModulo) {
    	UsuarioModulosFunciones objUMF = new UsuarioModulosFunciones();
    	for (UsuarioModulosFunciones usuarioModulosFunciones : lst) {
			if(usuarioModulosFunciones.getIdModulo() == idModulo) {
				objUMF =usuarioModulosFunciones;
				break;
			}
		}
    	return objUMF;
    }*/
	/*public static String getDescModulo(List<UsuarioModulosFunciones> lst, Integer idModulo) {
    	String descripcion = "";
    	for (UsuarioModulosFunciones usuarioModulosFunciones : lst) {
			if(usuarioModulosFunciones.getIdModulo() == idModulo) {
				descripcion =usuarioModulosFunciones.getModulo();
				break;
			}
		}
    	return descripcion;
    }*/
	public static Date parseDate(String date, String format) throws ParseException
	{
	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    return formatter.parse(date);
	}
	public static String StringDateToString(Date d, String formatDate){
		String strDate = new SimpleDateFormat(formatDate).format(d);
		return strDate;
	}
	
	/*public static Boolean getFuncionesPermiso(List<UsuarioModulosFunciones> lst, Integer idFuncion) {
    	boolean isAccessFuncion = false;
    	for (UsuarioModulosFunciones usuarioModulosFunciones : lst) {
			for(UsuarioFunciones func : usuarioModulosFunciones.getLstUsuarioFunciones()) {
				if(func.getIdFuncion() == idFuncion) {
					isAccessFuncion = true;
					break;
				}
			}
		}
    	return isAccessFuncion;
    }*/
}
