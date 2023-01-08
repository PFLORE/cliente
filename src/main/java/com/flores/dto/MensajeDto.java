package com.flores.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("hiding")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class MensajeDto<Object> {
	
	private String mensaje;
	private Object respuesta;
	
	public MensajeDto() {}
	
	public MensajeDto(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public MensajeDto(String mensaje, Object respuesta) {
		this.mensaje = mensaje;
		this.respuesta = respuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}
}
