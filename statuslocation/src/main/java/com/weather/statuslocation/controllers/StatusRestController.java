package com.weather.statuslocation.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.statuslocation.resources.ResponseDetalleCiudad;
import com.weather.statuslocation.resources.ResponseListaCiudadDetalle;
import com.weather.statuslocation.services.WeatherService;

@RestController
public class StatusRestController {
	
	@Autowired
	private WeatherService weatherService;

	@ModelAttribute
	public void setHeader(HttpServletResponse response) {
	    response.setHeader("Vary", "Accept");
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
	}   
	
	@RequestMapping(value = "/boards/{usuario}/{ciudad}", method = RequestMethod.GET, produces = "application/json")
	public ResponseDetalleCiudad getDetalleCiudad(@PathVariable String usuario, @PathVariable String ciudad) {
		
		ResponseDetalleCiudad respuesta = new ResponseDetalleCiudad();
		respuesta.setCiudadDetalle(weatherService.getDetalleClima(ciudad));

		return respuesta;
	}
	
	@RequestMapping(value = "/boards/{usuario}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseListaCiudadDetalle getListaCiudades(@PathVariable String usuario) {
		ResponseListaCiudadDetalle respuesta = new ResponseListaCiudadDetalle();
		respuesta.setListaCiudades(weatherService.getListaCiudades(usuario));
		return respuesta;
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseListaCiudadDetalle getListaCiudadDetalle() {
		ResponseListaCiudadDetalle respuesta = new ResponseListaCiudadDetalle();
		respuesta.setListaCiudades(weatherService.getListaCiudadDetalle());
		return respuesta;
	}
}
