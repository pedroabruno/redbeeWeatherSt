package com.weather.statuslocation.resources;

public class CiudadDetalle {
	
	private String nombre;
	
	private String pais;

	private String viento;
	
	private String humedad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getViento() {
		return viento;
	}
	public void setViento(String viento) {
		this.viento = viento;
	}
	public String getHumedad() {
		return humedad;
	}
	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}
}
