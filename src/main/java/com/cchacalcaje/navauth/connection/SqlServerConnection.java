package com.cchacalcaje.navauth.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Cristhian Chacalcaje
 * Conexión al servidor de base de datos MSSQL.
 * 
 */
public class SqlServerConnection {	
	
	private final String CONNECTOR_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	private final String DATABASE = "MSDNavSantillana";
	private final String DATABASE_HOST = "jdbc:sqlserver://SPESQLNAV01:1766;databaseName=" + DATABASE;
//	private final String DATABASE = "MSDNavSantillana_TEST";
//	private final String DATABASE_HOST = "jdbc:sqlserver://SPENAVTEST2016;databaseName=" + DATABASE;
//	private final String DATABASE = "NORMA_PERU_90";	//	
//	private final String DATABASE_HOST = "jdbc:sqlserver://10.137.192.63:1766;databaseName=" + DATABASE;
	private final String USERNAME = "supernav";
	private final String PASSWORD = "a123456A";
	private String MESSAGE_ERROR = "";		
	
	private static SqlServerConnection INSTANCE;
	private SqlServerConnection() {}
	public static SqlServerConnection getInstance() {
		if(INSTANCE == null)
			INSTANCE = new SqlServerConnection();
		return INSTANCE;
	}
	
	/**
	 * Obtener a la conexión a la base de datos.
	 * @return Conexión a la base de datos.
	 */
	public Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName(CONNECTOR_DRIVER).newInstance();			
		}catch(ClassNotFoundException ex){
			MESSAGE_ERROR = "No se encontró el controlador.";
			System.out.println(ex.getMessage());
			return connection;
		}catch(InstantiationException ex){
			System.out.println(ex.getMessage());
			MESSAGE_ERROR = "No se puede crear una instancia del controlador.";
			return connection;
		}catch (IllegalAccessException ex) {
			System.out.println(ex.getMessage());
			MESSAGE_ERROR = "No se puede accesar al controlador";
			return connection;
		}
		
		try {
			connection = DriverManager.getConnection(DATABASE_HOST, USERNAME, PASSWORD);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			MESSAGE_ERROR = "No se puede tener Acceso a la DB";
		}
		return connection;	
	}
	
	/**
	 * 
	 * @return Existencia de un error.
	 */
	public boolean hasError() {
		if (this.MESSAGE_ERROR.length() > 0)
			return true;
		return false;
	}

	/**
	 * 
	 * @return Mensaje de error.
	 */
	public String getError() {
		return this.MESSAGE_ERROR;
	}

	/**
	 * Limpiar error.
	 */
	public void clearError() {
		this.MESSAGE_ERROR = "";
	}
}
