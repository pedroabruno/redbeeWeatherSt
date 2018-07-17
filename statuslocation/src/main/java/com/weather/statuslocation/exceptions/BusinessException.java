package com.weather.statuslocation.exceptions;

public class BusinessException extends Exception{

	private static final long serialVersionUID = 7099402294961698857L;
	
	String mensaje;
	
	public BusinessException(String mensaje){
		this.mensaje=mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
