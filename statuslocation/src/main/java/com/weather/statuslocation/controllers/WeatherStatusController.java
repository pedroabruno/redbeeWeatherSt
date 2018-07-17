package com.weather.statuslocation.controllers;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.statuslocation.exceptions.BusinessException;
import com.weather.statuslocation.resources.ResponseDetalleCiudad;
import com.weather.statuslocation.resources.ResponseListaCiudadDetalle;
import com.weather.statuslocation.services.WeatherService;

@RestController
public class WeatherStatusController {
	
	final static Logger logger = Logger.getLogger(WeatherStatusController.class);
	
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
		logger.info("comienza getDetalleCiudad");
		ResponseDetalleCiudad respuesta = new ResponseDetalleCiudad();
		try{
			respuesta.setCiudadDetalle(weatherService.getDetalleClima(ciudad));
		}catch(BusinessException e){
			logger.error(e.getMessage());
		}
		logger.info("se envia respuesta getDetalleCiudad");
		return respuesta;
	}
	
	@RequestMapping(value = "/boards/{usuario}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseListaCiudadDetalle getListaCiudades(@PathVariable String usuario) {
		logger.info("comienza getListaCiudades");
		ResponseListaCiudadDetalle respuesta = new ResponseListaCiudadDetalle();
		try{
			respuesta.setListaCiudades(weatherService.getListaCiudades(usuario));
		}catch(BusinessException e){
			logger.error(e.getMessage());
		}
		logger.info("se envia respuesta getListaCiudades");
		return respuesta;
	}
}
