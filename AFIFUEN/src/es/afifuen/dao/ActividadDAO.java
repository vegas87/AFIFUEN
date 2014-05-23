package es.afifuen.dao;

import java.io.Serializable;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import es.afifuen.config.PMF;
import es.afifuen.dto.ActividadDTO;

public class ActividadDAO implements Serializable {

	private static final long serialVersionUID = 2422235916417127148L;
	
	public ActividadDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static List<ActividadDTO> getActividades() {
		
		List<ActividadDTO> actividades = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			
			Query q = pm.newQuery(ActividadDTO.class);
			q.setFilter("idestado == 1");
			q.setOrdering("fechaAlta desc");
			actividades = (List<ActividadDTO>) q.execute();
		} finally {
            pm.close();
		}
		return actividades;
	}
	
	public static void guardarActividad(ActividadDTO actividad) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(actividad);
		} finally {
            pm.close();
		}
	}
	
	public static void borrarActividad(ActividadDTO actividad) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			actividad.setIdestado(0);
			pm.makePersistent(actividad);
		} finally {
			pm.close();
		}
	}
}
