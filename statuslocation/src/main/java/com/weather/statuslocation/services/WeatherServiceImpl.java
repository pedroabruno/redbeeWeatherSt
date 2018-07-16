package com.weather.statuslocation.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.statuslocation.clients.WeatherClient;
import com.weather.statuslocation.db.DBService;
import com.weather.statuslocation.resources.CiudadDetalle;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private DBService dbService;
	
	@Autowired
	private WeatherClient weatherClient;
	
	@Override
	public CiudadDetalle getDetalleClima(String ciudad){
		return weatherClient.getDetalleCiudad(ciudad);
	}
	
	@Override
	public List<CiudadDetalle> getListaCiudades(String usuario){
		List<String> listaCiudades = dbService.getCiudades(usuario);
		return getListaCiudadDetalle(listaCiudades);
	}
	
	//borrar
	@Override
	public List<CiudadDetalle> getListaCiudadDetalle(){
		List<CiudadDetalle> listaCiudadesDetalle = new ArrayList<CiudadDetalle>();
		List<String> listaCiudades = dbService.getListaCiudades();
		for (String ciudad : listaCiudades) {
			CiudadDetalle ciudadDetalle = weatherClient.getDetalleCiudad(ciudad);
			listaCiudadesDetalle.add(ciudadDetalle);
		}
		return listaCiudadesDetalle;
	}
	
	private List<CiudadDetalle> getListaCiudadDetalle(List<String> listaCiudades){
		List<CiudadDetalle> listaCiudadesDetalle = new ArrayList<CiudadDetalle>();
		for (String ciudad : listaCiudades) {
			CiudadDetalle ciudadDetalle = weatherClient.getDetalleCiudad(ciudad);
			listaCiudadesDetalle.add(ciudadDetalle);
		}
		return listaCiudadesDetalle;
	}
	
}
