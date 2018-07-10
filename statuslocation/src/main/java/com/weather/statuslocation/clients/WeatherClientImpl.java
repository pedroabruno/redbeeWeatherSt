package com.weather.statuslocation.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.weather.statuslocation.resources.CiudadDetalle;

@Service
public class WeatherClientImpl implements WeatherClient {
	
	public CiudadDetalle getDetalleCiudad(String ciudad){
		
		CiudadDetalle detalleCiudad = new CiudadDetalle();
		
		try {
			
			String ciudadSinEspacios = ciudad.replaceAll("\\s","%20");
			
			URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+ciudadSinEspacios+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
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

			map(json,detalleCiudad);
			
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return detalleCiudad;
	}
	
	private void map(JSONObject json , CiudadDetalle detalleCiudad) throws JSONException{
		detalleCiudad.setViento(json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("wind").getString("chill"));;
		detalleCiudad.setHumedad(json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").getString("humidity"));;
		detalleCiudad.setNombre(json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getString("title"));;

	}
}
