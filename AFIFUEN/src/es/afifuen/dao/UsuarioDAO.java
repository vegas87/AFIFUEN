package es.afifuen.dao;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import es.afifuen.config.PMF;
import es.afifuen.dto.UsuarioDTO;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = -2490832218544775315L;

	public UsuarioDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static UsuarioDTO getUsuarioByCodigo(String codigo) throws NoSuchAlgorithmException {
		
		UsuarioDTO result = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			
			// Se comprueba la existencia del usuario administrador y si no existe se crea
			if (UsuarioDTO.USUARIO_ADMIN.equals(codigo)) {
				Query q = pm.newQuery(UsuarioDTO.class);
				q.setFilter("codigo == " + UsuarioDTO.USUARIO_ADMIN);
				List<UsuarioDTO> usuarios = (List<UsuarioDTO>) q.execute();
				if (usuarios == null || usuarios.isEmpty()) {
					// Si no se ha encontrado el usuario administrador hay que crearlo
					MessageDigest md = MessageDigest.getInstance("MD5");
					UsuarioDTO usuario = new UsuarioDTO();
					usuario.setCodigo(UsuarioDTO.USUARIO_ADMIN);
					usuario.setNombre("Usuario");
					usuario.setApellidos("Administrador");
					usuario.setAdministrador(true);
					
					md.update(UsuarioDTO.USUARIO_ADMIN.getBytes());
			        byte byteData[] = md.digest();
			        StringBuffer sb = new StringBuffer();
					for (byte b : byteData) {
						sb.append(String.format("%02x", b & 0xff));
					}
					usuario.setPassword(sb.toString());
		            pm.makePersistent(usuario);
				}
				
			}
			
			Query q = pm.newQuery(UsuarioDTO.class);
			q.setFilter("codigo == pCodigo && idestado == 1");
			q.declareParameters("String pCodigo");
			List<UsuarioDTO> usuarios = (List<UsuarioDTO>) q.execute(codigo);
			if (usuarios != null && usuarios.size() > 0)
				result = usuarios.get(0);
		} finally {
            pm.close();
		}
		return result;
	}
}
