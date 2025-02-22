package org.cibertec.dto;

import java.io.Serializable;

public class Opcion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;

	private String descripcion;

	public Opcion() {
		super();
	}

	public Opcion(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
