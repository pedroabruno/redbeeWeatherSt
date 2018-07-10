package com.weather.statuslocation.resources;

import java.util.ArrayList;
import java.util.List;

public class ResponseListaCiudadDetalle {

	private List<CiudadDetalle> listaCiudades = new ArrayList<CiudadDetalle>();

	public List<CiudadDetalle> getListaCiudades() {
		return listaCiudades;
	}

	public void setListaCiudades(List<CiudadDetalle> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}
	
}
