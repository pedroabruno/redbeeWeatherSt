package com.weather.statuslocation.clients;

import com.weather.statuslocation.exceptions.BusinessException;
import com.weather.statuslocation.resources.CiudadDetalle;

public interface WeatherClient {

	CiudadDetalle getDetalleCiudad(String ciudad);
}
