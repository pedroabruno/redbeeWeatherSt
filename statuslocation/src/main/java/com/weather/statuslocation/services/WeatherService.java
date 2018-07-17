package com.weather.statuslocation.services;

import java.util.List;

import com.weather.statuslocation.exceptions.BusinessException;
import com.weather.statuslocation.resources.CiudadDetalle;

public interface WeatherService {

	CiudadDetalle getDetalleClima(String ciudad) throws BusinessException;
	
	List<CiudadDetalle> getListaCiudades(String usuario) throws BusinessException;
	
	List<CiudadDetalle> getListaCiudadDetalle() throws BusinessException;
}
