package com.simple.api.core.enums;

/****
 * 
 * @author anjiboddupally
 *
 */
public enum HeaderName {

	CONTENT_TYPE("Content-Type");
	
	private String name;
	
	HeaderName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
