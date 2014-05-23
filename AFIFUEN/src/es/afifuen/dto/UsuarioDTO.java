package es.afifuen.dto;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import es.afifuen.util.Utils;


@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 234107529749009594L;
	
	public static final String USUARIO_ADMIN = "afifuen_admin";
	
	@PrimaryKey  
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String codigo;
	@Persistent
	private String nombre;
	@Persistent
	private String apellidos;
	@Persistent
	private Date fechaAlta = new Date();
	@Persistent
	private int idestado = 1;
	@Persistent
	private boolean administrador = false;
	@Persistent
	private String password;
	
	public String getNombreAsStr() {
		String strNombre = "";
		if (!Utils.isNullOrBlank(nombre))
			strNombre += nombre;
		if (!Utils.isNullOrBlank(apellidos))
			strNombre += " " + apellidos;
		return strNombre;
	}
	
	public UsuarioDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getIdestado() {
		return idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
