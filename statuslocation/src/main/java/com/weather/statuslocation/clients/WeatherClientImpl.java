package com.weather.statuslocation.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.weather.statuslocation.controllers.WeatherStatusController;
import com.weather.statuslocation.exceptions.BusinessException;
import com.weather.statuslocation.resources.CiudadDetalle;

@Service
public class WeatherClientImpl implements WeatherClient {
	
	final static Logger logger = Logger.getLogger(WeatherClientImpl.class);
	
	@Override
	public CiudadDetalle getDetalleCiudad(String ciudad) {		
		CiudadDetalle detalleCiudad = new CiudadDetalle();
		String urlApi = "https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22CIUDAD%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		try {			
			String ciudadSinEspacios = ciudad.replaceAll("\\s","%20");
			urlApi = urlApi.replaceAll("CIUDAD",ciudadSinEspacios);
			URL url = new URL(urlApi);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			StringBuilder sb = new StringBuilder();
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			    while ((line = br.readLine()) != null) {
			    	sb.append(line);
			    }
			
			JSONObject json = new JSONObject(sb.toString());
			
			map(json,ciudad,detalleCiudad);
			conn.disconnect();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (JSONException e) {
			logger.error(e.getMessage());
		}
		
		return detalleCiudad;
	}
	
	private void map(JSONObject json , String ciudad, CiudadDetalle detalleCiudad) throws JSONException{
		detalleCiudad.setNombre(ciudad);
		detalleCiudad.setCodigo(json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").getString("code"));
		detalleCiudad.setTemperatura(json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").getString("temp"));
		detalleCiudad.setCondicion(json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").getString("text"));

	}
}
