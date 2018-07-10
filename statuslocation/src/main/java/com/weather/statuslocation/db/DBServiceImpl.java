package com.weather.statuslocation.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DBServiceImpl implements DBService {

	@Override
	public List<String> getUsuarios(){
		
		String loginUrl = "jdbc:mysql://localhost:3306/weather";
		String loginUser = "root";
		String loginPass = "1234";
		
		List<String> usuarios =  new ArrayList<String>();

		try{
			Connection myConn = DriverManager.getConnection(loginUrl,loginUser,loginPass);
			Statement query = myConn.createStatement();
			ResultSet resultSet = query.executeQuery("SELECT * FROM weather.persona");
			
			while(resultSet.next()){
				usuarios.add(resultSet.getString("idPersona"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	@Override
	public List<String> getCiudades(String usuario){
		
		String loginUrl = "jdbc:mysql://localhost:3306/weather";
		String loginUser = "root";
		String loginPass = "1234";
		
		List<String> ciudades =  new ArrayList<String>();

		try{
			Connection myConn = DriverManager.getConnection(loginUrl,loginUser,loginPass);
			Statement query = myConn.createStatement();
			ResultSet resultSet = query.executeQuery("SELECT * FROM weather.relpersonaciudad where idPersona = '" + usuario +"'");
			while(resultSet.next()){
				ciudades.add(resultSet.getString("codCiudad"));	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ciudades;
	}	
	
	@Override
	public List<String> getListaCiudades(){
		
		String loginUrl = "jdbc:mysql://localhost:3306/weather";
		String loginUser = "root";
		String loginPass = "1234";
		
		List<String> ciudades =  new ArrayList<String>();

		try{
			Connection myConn = DriverManager.getConnection(loginUrl,loginUser,loginPass);
			Statement query = myConn.createStatement();
			ResultSet resultSet = query.executeQuery("SELECT DISTINCT codCiudad FROM weather.relpersonaciudad");
			while(resultSet.next()){
				ciudades.add(resultSet.getString("codCiudad"));	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ciudades;
	}
}
