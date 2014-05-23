package es.afifuen.util;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.google.cloud.sql.jdbc.internal.Util;

public class Utils {

	public static void addMensajeError(String mensaje, Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, e.getMessage()));
		Logger.getLogger(Util.class.getName()).severe(mensaje);
	}
	
	public static boolean isNullOrBlank(String s) {
		return s == null || "".equals(s);
	}
	
	public static boolean isNullOrBlank(Long l) {
		return l == null || (new Long(-1)).equals(l);
	}
}
