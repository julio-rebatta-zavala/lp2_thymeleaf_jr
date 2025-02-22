package org.cibertec.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Area implements Serializable {

	private static final long serialVersionUID = 4476473688883481556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Debe ingresar la descripción")
	@Size(max = 15, message = "La descripción no puede tener mas de 15 caracteres")
	private String descripcion;
	private String estado;

	public Area() {
		super();
	}

	public Area(String descripcion, String estado) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}

}
