package com.cchacalcaje.navauth.response;

/**
 * Repuesta que retorna el web service.
 * @author Cristhian Chacalcaje
 *
 */
public class AuthorizationResponse {
	boolean success;
	String message;
	Object data;
	public AuthorizationResponse() {
	}
	public AuthorizationResponse(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
