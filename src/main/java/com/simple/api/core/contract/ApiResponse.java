package com.simple.api.core.contract;

import java.util.Map;

/****
 * 
 * @author anjiboddupally
 *
 */
public interface ApiResponse {
	
	String getResponse();
	
	Integer getStatusCode();
	
	Map<String, String> getCookies();
	
	Map<String, String> getHeaders();
	
	<T> T getResponseBodyAs(Class<T> type);

}
