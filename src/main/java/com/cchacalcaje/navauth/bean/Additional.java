package com.cchacalcaje.navauth.bean;

/**
 * Campos adicionales agregados.
 * @author Cristhian Chacalcaje
 *
 */
public class Additional {
	String key;
	String value;
	public Additional(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
