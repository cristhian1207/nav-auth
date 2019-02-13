package com.cchacalcaje.navauth.request;

import java.util.ArrayList;

import com.cchacalcaje.navauth.bean.Additional;

/**
 * Objeto que recibe el web service.
 * @author Cristhian Chacalcaje
 *
 */
public class EmailRequest {
	int authID;
	String tableName;
	String fieldName;
	String authorizerEmail;
	String authorizerName;
	String authorizerID;
	String userName;
	String primaryKey;
	String recordDescription;
	String oldValue;
	String newValue;
	ArrayList<Additional> lAdditional = new ArrayList<>();
	public EmailRequest() {
	}
	public EmailRequest(int authID, String tableName, String fieldName, String authorizerEmail, String authorizerName,
			String authorizerID, String userName, String primaryKey, String recordDescription, String oldValue, String newValue) {
		this.authID = authID;
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.authorizerEmail = authorizerEmail;
		this.authorizerName = authorizerName;
		this.authorizerID = authorizerID;
		this.userName = userName;
		this.primaryKey = primaryKey;
		this.recordDescription = recordDescription;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}	
	public EmailRequest(int authID, String tableName, String fieldName, String authorizerEmail, String authorizerName,
			String authorizerID, String userName, String primaryKey, String recordDescription, String oldValue,
			String newValue, ArrayList<Additional> lAdditional) {
		this.authID = authID;
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.authorizerEmail = authorizerEmail;
		this.authorizerName = authorizerName;
		this.authorizerID = authorizerID;
		this.userName = userName;
		this.primaryKey = primaryKey;
		this.recordDescription = recordDescription;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.lAdditional = lAdditional;
	}
	public int getAuthID() {
		return authID;
	}
	public void setAuthID(int authID) {
		this.authID = authID;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getAuthorizerEmail() {
		return authorizerEmail;
	}
	public void setAuthorizerEmail(String authorizerEmail) {
		this.authorizerEmail = authorizerEmail;
	}
	public String getAuthorizerName() {
		return authorizerName;
	}
	public void setAuthorizerName(String authorizerName) {
		this.authorizerName = authorizerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthorizerID() {
		return authorizerID;
	}
	public void setAuthorizerID(String authorizerID) {
		this.authorizerID = authorizerID;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getRecordDescription() {
		return recordDescription;
	}
	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public ArrayList<Additional> getlAdditional() {
		return lAdditional;
	}
	public void setlAdditional(ArrayList<Additional> lAdditional) {
		this.lAdditional = lAdditional;
	}
}