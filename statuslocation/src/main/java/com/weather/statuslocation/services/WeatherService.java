package com.weather.statuslocation.services;

import java.util.List;

import com.weather.statuslocation.resources.CiudadDetalle;

public interface WeatherService {

	CiudadDetalle getDetalleClima(String ciudad);
	
	List<CiudadDetalle> getListaCiudades(String usuario);
	
	List<CiudadDetalle> getListaCiudadDetalle();
}
