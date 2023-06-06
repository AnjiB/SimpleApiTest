package com.simple.api.core.enums;

/****
 * 
 * @author anjiboddupally
 *
 */

public enum HeaderValue {

	APPLICATION_JSON("application/json"),
	
	APPLICATION_XML("application/xml");
	
	private String value;
	
	
	HeaderValue(String value) {
		this.value = value;
	}
	
	
	public String getValue() {
		return this.value;
	}
}
