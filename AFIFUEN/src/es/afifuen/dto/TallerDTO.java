package es.afifuen.dto;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class TallerDTO implements Serializable {

	private static final long serialVersionUID = 3552779197295282772L;

	public static final int ACTIVIDAD_TALLER = 0;
	public static final int ACTIVIDAD_CURSO = 1;
	
	@PrimaryKey  
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private int tipoActividad = ACTIVIDAD_TALLER;
	@Persistent
	private String nombre;
	@Persistent
	private String descripcion;
	@Persistent
	private Date fecha;
	@Persistent
	private int idestado = 1;
	@Persistent
	private int precio;
	@Persistent
	private int numeroAlumnos = 0;
	@Persistent
	private String profesor;
	@Persistent
	private Date fechaAlta = new Date();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getIdestado() {
		return idestado;
	}
	
	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public int getNumeroAlumnos() {
		return numeroAlumnos;
	}
	
	public void setNumeroAlumnos(int numeroAlumnos) {
		this.numeroAlumnos = numeroAlumnos;
	}
	
	public String getProfesor() {
		return profesor;
	}
	
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(int tipoActividad) {
		this.tipoActividad = tipoActividad;
	}
}
