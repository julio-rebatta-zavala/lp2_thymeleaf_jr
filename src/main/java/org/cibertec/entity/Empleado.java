package org.cibertec.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Empleado implements Serializable {

	private static final long serialVersionUID = 150392824562015309L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(length = 45)
	@NotBlank(message = "El nombre es requerido")
	@Size(min = 3, max = 25, message = "El nombre debe tener como mínimo 3 caractares y como máximo 25 caracteres")
	private String nombre;
	
	@Column(length = 45)
	@NotBlank(message = "El apellido es requerido")
	@Size(min = 3, max = 25, message = "El nombre debe tener como mínimo 3 caractares y como máximo 25 caracteres")
	private String apellido;
	
	@ManyToOne
	@JoinColumn(
            name = "area", // specifies the name of the foreign key column in the database
            referencedColumnName = "id" // primary key of the user who owns this MESSAGE
    )
	@NotNull
	private Area area;
	
	@Column(name = "estado", length = 1)
	private String estado;

	public Empleado() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", area=" + area + "]";
	}

}
