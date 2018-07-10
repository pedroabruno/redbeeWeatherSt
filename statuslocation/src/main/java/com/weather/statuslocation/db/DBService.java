package com.weather.statuslocation.db;

import java.util.List;

public interface DBService {

	List<String> getUsuarios();
	
	List<String> getCiudades(String usuario);
	
	List<String> getListaCiudades();

}
