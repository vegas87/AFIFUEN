package es.afifuen.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import es.afifuen.dao.TallerDAO;
import es.afifuen.dto.TallerDTO;
import es.afifuen.util.Utils;

@ManagedBean(name="tallerBean")
@SessionScoped
public class TallerBean implements Serializable {

	private static final long serialVersionUID = -8716806576195538766L;
	private static final Logger log = Logger.getLogger(TallerBean.class.getName());

	public String cargarTalleresYCursos() {
		log.info("Inicio TallerBean.cargarTalleresYCursos");
		try {
			
			List<TallerDTO> talleresBBDD = TallerDAO.getTalleres();
			List<TallerDTO> talleres = new ArrayList<TallerDTO>();
			talleres.addAll(talleresBBDD);
			
			List<TallerDTO> cursosBBDD = TallerDAO.getCursos();
			talleres.addAll(cursosBBDD);

			// Se guardan los talleres y cursos en sesión
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaTalleres", talleres);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al cargar los talleres y cursos", e);
			return null;
		}
		log.info("Fin TallerBean.cargarTalleresYCursos");
		return "TALLERES_CURSOS";
	}
	
	public String cargarTalleres() {
		log.info("Inicio TallerBean.cargarTalleres");
		try {
			
			List<TallerDTO> talleresBBDD = TallerDAO.getTalleres();
			List<TallerDTO> talleres = new ArrayList<TallerDTO>();
			talleres.addAll(talleresBBDD);
			
			// Se guardan los talleres en sesión
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaTalleres", talleres);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al cargar los talleres", e);
			return null;
		}
		log.info("Fin TallerBean.cargarTalleres");
		return "TALLERES";
	}
	
	public String cargarProximosTalleres() {
		log.info("Inicio TallerBean.cargarProximosTalleres");
		try {
			
			List<TallerDTO> talleresBBDD = TallerDAO.getProximosTalleres();
			List<TallerDTO> talleres = new ArrayList<TallerDTO>();
			talleres.addAll(talleresBBDD);
			
			// Se guardan los talleres en sesión
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaTalleres", talleres);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al cargar los próximos talleres", e);
			return null;
		}
		log.info("Fin TallerBean.cargarProximosTalleres");
		return "PROXIMOS_TALLERES";
	}
	
	public void mostrarNuevoTaller() {
		log.info("Inicio TallerBean.mostrarNuevoTaller");
		try {
			
			TallerDTO taller = new TallerDTO();
			taller.setTipoActividad(TallerDTO.ACTIVIDAD_TALLER);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller", taller);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al inicializar el nuevo taller", e);
		}
		log.info("Fin TallerBean.mostrarNuevoTaller");
	}
	
	public void guardarTaller() {
		log.info("Inicio TallerBean.guardarTaller");
		try {
			
			TallerDTO taller = (TallerDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("taller");
			if (taller != null && validarCamposTaller(taller)) {
				
				TallerDAO.guardarTaller(taller);
				
				// Se recarga el listado de talleres
				cargarTalleres();
				
			}
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al guardar el taller", e);
		}
		log.info("Fin TallerBean.guardarTaller");
	}
	
	private boolean validarCamposTaller(TallerDTO taller) {
		boolean resultado = true;
		
		if (Utils.isNullOrBlank(taller.getNombre())) {
			resultado = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio.", null));
		}
		if (Utils.isNullOrBlank(taller.getDescripcion())) {
			resultado = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La descripción es obligatoria.", null));
		}
		
		return resultado;
	}
	
	public void borrarTaller() {
		log.info("Inicio TallerBean.borrarTaller");
		try {
			
			TallerDTO taller = (TallerDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("taller");
			if (taller != null) {
				TallerDAO.borrarTaller(taller);
				
				// Se recarga el listado de talleres
				cargarTalleres();
			}
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al borrar el taller", e);
		}
		log.info("Fin TallerBean.borrarTaller");
	}
	
	public String cargarCursos() {
		log.info("Inicio TallerBean.cargarCursos");
		try {
			
			List<TallerDTO> cursosBBDD = TallerDAO.getCursos();
			List<TallerDTO> cursos = new ArrayList<TallerDTO>();
			cursos.addAll(cursosBBDD);
			
			// Se guardan los cursos en sesión
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaCursos", cursos);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al cargar los cursos", e);
			return null;
		}
		log.info("Fin TallerBean.cargarCursos");
		return "CURSOS";
	}
	
	public String cargarProximosCursos() {
		log.info("Inicio TallerBean.cargarProximosCursos");
		try {
			
			List<TallerDTO> cursosBBDD = TallerDAO.getProximosCursos();
			List<TallerDTO> cursos = new ArrayList<TallerDTO>();
			cursos.addAll(cursosBBDD);
			
			// Se guardan los cursos en sesión
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaCursos", cursos);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al cargar los próximos cursos", e);
			return null;
		}
		log.info("Fin TallerBean.cargarProximosCursos");
		return "PROXIMOS_CURSOS";
	}
	
	public void mostrarNuevoCurso() {
		log.info("Inicio TallerBean.mostrarNuevoCurso");
		try {
			
			TallerDTO curso = new TallerDTO();
			curso.setTipoActividad(TallerDTO.ACTIVIDAD_CURSO);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("curso", curso);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al inicializar el nuevo curso", e);
		}
		log.info("Fin TallerBean.mostrarNuevoCurso");
	}
	
	public void guardarCurso() {
		log.info("Inicio TallerBean.guardarCurso");
		try {
			
			TallerDTO curso = (TallerDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("curso");
			if (curso != null && validarCamposCurso(curso)) {
				
				TallerDAO.guardarCurso(curso);
				
				// Se recarga el listado de cursos
				cargarCursos();
				
			}
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al guardar el curso", e);
		}
		log.info("Fin TallerBean.guardarCurso");
	}
	
	private boolean validarCamposCurso(TallerDTO curso) {
		boolean resultado = true;
		
		if (Utils.isNullOrBlank(curso.getNombre())) {
			resultado = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio.", null));
		}
		if (Utils.isNullOrBlank(curso.getDescripcion())) {
			resultado = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La descripción es obligatoria.", null));
		}
		
		return resultado;
	}
	
	public void borrarCurso() {
		log.info("Inicio TallerBean.borrarCurso");
		try {
			
			TallerDTO curso = (TallerDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("curso");
			if (curso != null) {
				TallerDAO.borrarCurso(curso);
				
				// Se recarga el listado de cursos
				cargarCursos();
			}
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al borrar el curso", e);
		}
		log.info("Fin TallerBean.borrarCurso");
	}
	
}
