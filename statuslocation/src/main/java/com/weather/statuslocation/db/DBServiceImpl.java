package com.weather.statuslocation.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weather.statuslocation.exceptions.BusinessException;

@Service
public class DBServiceImpl implements DBService {

	@Value("${dbUrl}")
	private String dbUrl;
	
	@Value("${dbUser}")
	private String dbUser;
	
	@Value("${dbPass}")
	private String dbPass;
	
	@Override
	public List<String> getUsuarios() throws BusinessException{
				
		List<String> usuarios =  new ArrayList<String>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			Statement query = myConn.createStatement();
			ResultSet resultSet = query.executeQuery("SELECT * FROM weather.persona");
			
			while(resultSet.next()){
				usuarios.add(resultSet.getString("idPersona"));
			}
			
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		
		return usuarios;
	}
	
	@Override
	public List<String> getCiudades(String usuario) throws BusinessException{
		
		List<String> ciudades =  new ArrayList<String>();

		try{

			Connection myConn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			Statement query = myConn.createStatement();
			ResultSet resultSet = query.executeQuery("SELECT * FROM weather.relpersonaciudad where idPersona = '" + usuario +"'");
			while(resultSet.next()){
				ciudades.add(resultSet.getString("idCiudad"));	
			}
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		
		return ciudades;
	}	
	
	@Override
	public List<String> getListaCiudades() throws BusinessException{
		
		String loginUrl = "jdbc:mysql://localhost:3306/weather";
		String loginUser = "root";
		String loginPass = "1234";
		
		List<String> ciudades =  new ArrayList<String>();

		try{
			Connection myConn = DriverManager.getConnection(loginUrl,loginUser,loginPass);
			Statement query = myConn.createStatement();
			ResultSet resultSet = query.executeQuery("SELECT DISTINCT idCiudad FROM weather.relpersonaciudad");
			while(resultSet.next()){
				ciudades.add(resultSet.getString("idCiudad"));	
			}
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		
		return ciudades;
	}
}
