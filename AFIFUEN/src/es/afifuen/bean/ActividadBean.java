package es.afifuen.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import es.afifuen.dao.ActividadDAO;
import es.afifuen.dto.ActividadDTO;
import es.afifuen.util.Utils;

@ManagedBean(name="actividadBean")
@SessionScoped
public class ActividadBean implements Serializable {

	private static final long serialVersionUID = -8716806576195538766L;
	private static final Logger log = Logger.getLogger(ActividadBean.class.getName());

	public String cargarActividades() {
		log.info("Inicio ActividadBean.cargarActividades");
		try {
			
			List<ActividadDTO> actividadesBBDD = ActividadDAO.getActividades();
			List<ActividadDTO> actividades = new ArrayList<ActividadDTO>();
			actividades.addAll(actividadesBBDD);
			
			// Se guardan las actividades en sesión
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaActividades", actividades);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al cargar las actividades", e);
			return null;
		}
		log.info("Fin ActividadBean.cargarActividades");
		return "ACTIVIDADES";
	}
	
	public void mostrarNuevaActividad() {
		log.info("Inicio ActividadBean.mostrarNuevaActividad");
		try {
			
			ActividadDTO actividad = new ActividadDTO();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actividad", actividad);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al inicializar la nueva actividad", e);
		}
		log.info("Fin ActividadBean.mostrarNuevaActividad");
	}
	
	public void guardarActividad() {
		log.info("Inicio ActividadBean.guardarActividad");
		try {
			
			ActividadDTO actividad = (ActividadDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("actividad");
			if (actividad != null && validarCamposActividad(actividad)) {
				
				ActividadDAO.guardarActividad(actividad);
				
				// Se recarga el listado de actividades
				cargarActividades();
				
			}
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al guardar la actividad", e);
		}
		log.info("Fin ActividadBean.guardarActividad");
	}
	
	private boolean validarCamposActividad(ActividadDTO actividad) {
		boolean resultado = true;
		
		if (Utils.isNullOrBlank(actividad.getNombre())) {
			resultado = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio.", null));
		}
		if (Utils.isNullOrBlank(actividad.getDescripcion())) {
			resultado = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La descripción es obligatoria.", null));
		}
		
		return resultado;
	}
	
	public void borrarActividad() {
		log.info("Inicio ActividadBean.borrarActividad");
		try {
			
			ActividadDTO actividad = (ActividadDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("actividad");
			if (actividad != null) {
				ActividadDAO.borrarActividad(actividad);
				
				// Se recarga el listado de actividades
				cargarActividades();
			}
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al borrar la actividad", e);
		}
		log.info("Fin ActividadBean.borrarActividad");
	}
	
}
