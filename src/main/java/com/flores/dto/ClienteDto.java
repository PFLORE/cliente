package com.flores.dto;

import com.flores.util.Utilidades;

public class ClienteDto {

	private String id;
	private String nombres;
	private String apellidos;
	private String numeroDocumento;
	private Integer edad;
	
	public ClienteDto() {
	}

	public String getId() {
		String idTmp = null;
		try {
			idTmp = (id != null) ? (Utilidades.esNumerico(id)) ? Utilidades.Encriptar(id) : Utilidades.Desencriptar(id) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idTmp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
}
