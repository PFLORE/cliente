package com.flores.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nombres")
	@NotNull
	@Size(max = 500, message = "500 caracteres como máximo")
	private String nombres;
	
	@Column(name = "apellidos")
	@NotNull
	@Size(max = 600, message = "600 caracteres como máximo")
	private String apellidos;
	
	@Column(name = "tipo_documento")
	@NotNull
	@Size(max = 100, message = "100 caracteres como máximo")
	private String tipoDocumento;
	
	@Column(name = "numero_documento")
	@NotNull
	@Size(max = 11, message = "11 caracteres como máximo")
	private String numeroDocumento;
	
	@Column(name = "edad")
	private Integer edad;

	public Cliente() {
	}
	
	public Cliente(Integer id, String nombres, Integer edad) {
		this.id = id;
		this.nombres = nombres;
		this.edad = edad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
