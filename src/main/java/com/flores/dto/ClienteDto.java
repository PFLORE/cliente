package com.flores.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.flores.util.Utilidades;

public class ClienteDto {

	private String id;
	
	@NotBlank(message = "es requerido")
	private String nombres;
	
	@NotBlank(message = "es requerido")
	private String apellidos;
	
	@NotBlank(message = "es requerido")
	private String tipoDocumento;
	
	@NotBlank(message = "es requerido")
//	@Size(max = 11, message = "como máximo acepta 11 caracteres")
	@Pattern(regexp = "^[0-9]{8,11}$", 
		message = "debe tener una longitud de 8 a 11 caracteres y solo números")
	private String numeroDocumento;
	
	@Min(0)
	@Max(150)
	private Integer edad;
	
	public ClienteDto() {
	}
	
	public ClienteDto(String id, String nombres, Integer edad) {
		this.id = id;
		this.nombres = nombres;
		this.edad = edad;
	}

	public String getId() {
		String idTmp = null;
		try {
			idTmp = (id != null) 
					? (Utilidades.esNumerico(id)) 
							? Utilidades.Encriptar(id) 
									: Utilidades.Desencriptar(id) 
							: null;
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

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
