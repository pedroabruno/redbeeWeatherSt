package com.weather.statuslocation.db;

import java.util.List;

import com.weather.statuslocation.exceptions.BusinessException;

public interface DBService {

	List<String> getUsuarios() throws BusinessException;
	
	List<String> getCiudades(String usuario) throws BusinessException;
	
	List<String> getListaCiudades() throws BusinessException;

}
