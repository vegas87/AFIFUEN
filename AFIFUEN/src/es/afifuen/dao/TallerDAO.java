package es.afifuen.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import es.afifuen.config.PMF;
import es.afifuen.dto.TallerDTO;

public class TallerDAO implements Serializable {

	private static final long serialVersionUID = 2422235916417127148L;
	
	public TallerDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static List<TallerDTO> getTalleres() {
		
		List<TallerDTO> talleres = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			
			Query q = pm.newQuery(TallerDTO.class);
			q.setFilter("idestado == 1 && tipoActividad == 0");
			q.setOrdering("fechaAlta desc");
			talleres = (List<TallerDTO>) q.execute();
		} finally {
            pm.close();
		}
		return talleres;
	}
	
	@SuppressWarnings("unchecked")
	public static List<TallerDTO> getProximosTalleres() {
		
		List<TallerDTO> talleres = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			
			Query q = pm.newQuery(TallerDTO.class);
			q.setFilter("idestado == 1 && tipoActividad == 0 && fecha > fechaActual");
			q.declareParameters("java.util.Date fechaActual");
			talleres = (List<TallerDTO>) q.execute(new Date());
		} finally {
            pm.close();
		}
		return talleres;
	}
	
	public static void guardarTaller(TallerDTO taller) {
		PersistenceManager pm = null;
		try {
			taller.setTipoActividad(TallerDTO.ACTIVIDAD_TALLER);
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(taller);
		} finally {
            pm.close();
		}
	}
	
	public static void borrarTaller(TallerDTO taller) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			taller.setIdestado(0);
			pm.makePersistent(taller);
		} finally {
			pm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<TallerDTO> getCursos() {
		
		List<TallerDTO> cursos = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			
			Query q = pm.newQuery(TallerDTO.class);
			q.setFilter("idestado == 1 && tipoActividad == 1");
			q.setOrdering("fechaAlta desc");
			cursos = (List<TallerDTO>) q.execute();
		} finally {
            pm.close();
		}
		return cursos;
	}
	
	@SuppressWarnings("unchecked")
	public static List<TallerDTO> getProximosCursos() {
		
		List<TallerDTO> cursos = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			
			Query q = pm.newQuery(TallerDTO.class);
			q.setFilter("idestado == 1 && tipoActividad == 1 && fecha > fechaActual");
			q.declareParameters("java.util.Date fechaActual");
			cursos = (List<TallerDTO>) q.execute(new Date());
		} finally {
            pm.close();
		}
		return cursos;
	}
	
	public static void guardarCurso(TallerDTO curso) {
		PersistenceManager pm = null;
		try {
			curso.setTipoActividad(TallerDTO.ACTIVIDAD_CURSO);
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(curso);
		} finally {
            pm.close();
		}
	}
	
	public static void borrarCurso(TallerDTO curso) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			curso.setIdestado(0);
			pm.makePersistent(curso);
		} finally {
			pm.close();
		}
	}
}
