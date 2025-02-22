package org.cibertec.dto;

import java.io.Serializable;

public class Reporte implements Serializable {

	private static final long serialVersionUID = 4652903875350448937L;

	private String formato;

	private String nombreReporte;

	public Reporte() {
		super();
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

}
