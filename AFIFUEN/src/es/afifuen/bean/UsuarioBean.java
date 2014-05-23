package es.afifuen.bean;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import es.afifuen.dao.UsuarioDAO;
import es.afifuen.dto.UsuarioDTO;
import es.afifuen.util.Utils;

@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 7341347391110681755L;
	private static final Logger log = Logger.getLogger(UsuarioBean.class.getName());
	
	private String usuario;
	private String password;
	private UsuarioDTO usuarioDTO;
	
	public void login() {
		log.info("Inicio UsuarioBean.login");
		try {
			if (!Utils.isNullOrBlank(usuario) && !Utils.isNullOrBlank(password)) {
				
				// Se carga la información del usuario
				usuarioDTO = UsuarioDAO.getUsuarioByCodigo(usuario);
				
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe introducir usuario y contraseña.", null));
				return;
			}
			
			// Si no existe el usuario se muestra un error
			if (usuarioDTO == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe ningún usuario con ese código.", null));
				return;
			}
				
			// Si las contraseñas no coinciden se muestra un error
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
	        byte byteData[] = md.digest();
	        StringBuffer sb = new StringBuffer();
			for (byte b : byteData) {
				sb.append(String.format("%02x", b & 0xff));
			}
			String passCodificada = sb.toString();
			if (!usuarioDTO.getPassword().equals(passCodificada)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta.", null));
				// Se vacía el usuario para que aparezca el login
				usuarioDTO = null;
			}
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioDTO);
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al iniciar sesión.", e);
		}
		log.info("Fin UsuarioBean.login");
	}
	
	public void logout() {
		log.info("Inicio UsuarioBean.logout");
		try {

			// Se elimina la información del usuario
			usuarioDTO = null;
			usuario = null;
			password = null;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
			
		} catch (Exception e) {
			Utils.addMensajeError("Se ha producido un error al cerrar sesión.", e);
		}
		log.info("Fin UsuarioBean.logout");
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
}
