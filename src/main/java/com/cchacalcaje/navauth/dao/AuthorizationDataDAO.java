package com.cchacalcaje.navauth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cchacalcaje.navauth.bean.Additional;
import com.cchacalcaje.navauth.bean.AuthorizationData;
import com.cchacalcaje.navauth.connection.SqlServerConnection;
import com.cchacalcaje.navauth.response.AuthorizationResponse;

/**
 * Métodos de autorización que interactuan con la BD.
 * @author Cristhian Chacalcaje
 *
 */
public class AuthorizationDataDAO {
	
	/**
	 * Método que autoriza o rechaza la actualización.
	 * @param _authorizationData Autorización enviada por el ws.
	 * @return Respuesta de la autorización.
	 */
	public AuthorizationResponse authUpdates(AuthorizationData _authorizationData) {
		AuthorizationData authorizationData = (AuthorizationData) getAuthorizationData(_authorizationData.getNo()).getData();
		switch (authorizationData.getStatus()) {
			case 1:
				return new AuthorizationResponse(false, "El registro ya fue autorizado.", _authorizationData);
			case 2:
				return new AuthorizationResponse(false, "El registro ya fue rechazado.", _authorizationData);
		}
		if(_authorizationData.getStatus() == 1) {
			AuthorizationResponse authorizationResponse = new AuthorizationResponse();
			switch(_authorizationData.getTableID()){
			case 18:
				switch(_authorizationData.getFieldID()) {
				case 20:
					authorizationResponse = authCustomer_CreditLimit(_authorizationData.getRecordPrimaryKey(),
							Double.parseDouble(_authorizationData.getNewValue()));
					break;
				}
				break;
			}
			if(!authorizationResponse.isSuccess()) {
				return authorizationResponse;
			}			
		}
		return updateAuthorizationData(_authorizationData);
	}
	
	/**
	 * Actualiza el límite de crédito del cliente.
	 * @param _no Código del cliente.
	 * @param _creditLimit Nuevo límite de crédito.
	 * @return Respuesta de la autorización.
	 */
	AuthorizationResponse authCustomer_CreditLimit(String _no, double _creditLimit) {				
		try {
			Connection connection = SqlServerConnection.getInstance().getConnection();
			String query = "{CALL usp_authCustomer_creditLimit(?, ?)}";
			CallableStatement statement = connection.prepareCall(query);
			statement.setString(1, _no);
			statement.setDouble(2, _creditLimit);
			statement.execute();
			connection.close();
			return new AuthorizationResponse(true, "", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return new AuthorizationResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * 
	 * @param _authorizationData
	 * @return Respuesta de la autorización.
	 */
	AuthorizationResponse updateAuthorizationData(AuthorizationData _authorizationData) {
		try {
			Connection connection = SqlServerConnection.getInstance().getConnection();
			String query = "{CALL usp_updateAuthorizationData(?, ?, ?, ?, ?)}";
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, _authorizationData.getNo());
			statement.setString(2, _authorizationData.getApproverUserID());
			statement.setString(3, _authorizationData.getApproverUserName());
			statement.setInt(4, _authorizationData.getStatus());
			statement.setString(5, _authorizationData.getMessage());
			statement.execute();
			connection.close();
			switch(_authorizationData.getStatus()) {
				case 1:
					return new AuthorizationResponse(true, "Se autorizó la modificación del registro.", null);
				case 2:
					return new AuthorizationResponse(true, "Se rechazó la modificación del registro.", null);				
			}
			return new AuthorizationResponse(true, "", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return new AuthorizationResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * Obtiene la autorización por el id.
	 * @param _no ID de la autorización.
	 * @return Respuesta de la autorización.
	 */
	public AuthorizationResponse getAuthorizationData(int _no) {		
		try{
			Connection connection = SqlServerConnection.getInstance().getConnection();
			String query = "{CALL usp_getAuthorizationData(?)}";
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, _no);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				AuthorizationData authorizationData = new AuthorizationData(
						rs.getInt("No"),
						rs.getString("Table Caption"), 
						rs.getString("Record Primary Key"), 
						rs.getString("Record Description"), 
						rs.getString("Field Caption"), 
						rs.getString("Old Value"), 
						rs.getString("New Value"), 
						rs.getDate("Posting Date"), 
						rs.getString("Requester User ID"), 
						rs.getString("Requester User Name"), 
						rs.getString("Approver User ID"), 
						rs.getString("Approver User Name"), 
						rs.getInt("Status"), 
						rs.getString("Message"), 
						rs.getDate("Authorized Date"), 
						rs.getInt("Table ID"), 
						rs.getInt("Field ID"),
						getAdditionals(rs.getString("Record Primary Key")));						
				return new AuthorizationResponse(true, "", authorizationData);
			}
			return new AuthorizationResponse(false, "No se encontró el registro.", null);
		}catch(SQLException e){
			e.printStackTrace();
			return new AuthorizationResponse(false, e.getMessage(), null);
		}
	}
	
	/**
	 * Obtiene los campos adicionales agregados a la autorización.
	 * @param _primaryKey ID de la autorización.
	 * @return Lista de campos adicionales.
	 */
	ArrayList<Additional> getAdditionals(String _primaryKey){
		ArrayList<Additional> lAdditional = new ArrayList<>();
		try{
			Connection connection = SqlServerConnection.getInstance().getConnection();
			String query = "{CALL usp_getAdditionals(?)}";
			CallableStatement statement = connection.prepareCall(query);
			statement.setString(1, _primaryKey);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				lAdditional.add(new Additional(rs.getString("Text1"), rs.getString("Text2")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lAdditional;
	}
}
