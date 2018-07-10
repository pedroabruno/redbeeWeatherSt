package com.weather.statuslocation.resources;

public class PersonaCiudad {
	
	private String Nombre;
	
	private CiudadDetalle ciudadDetalle;

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public CiudadDetalle getCiudadDetalle() {
		return ciudadDetalle;
	}

	public void setCiudadDetalle(CiudadDetalle ciudadDetalle) {
		this.ciudadDetalle = ciudadDetalle;
	}

}
