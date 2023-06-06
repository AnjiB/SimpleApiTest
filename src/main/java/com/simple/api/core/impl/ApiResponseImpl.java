package com.simple.api.core.impl;

import java.util.Map;

import com.simple.api.core.contract.ApiResponse;
import com.simple.api.core.modal.RestResponse;

/****
 * 
 * @author anjiboddupally
 *
 */
public class ApiResponseImpl implements ApiResponse {
	
	private RestResponse response;
	
	public ApiResponseImpl(RestResponse response) {
	
		this.response = response;
	}

	public String getResponse() {
		return response.getBody();
	}

	public Integer getStatusCode() {
		return response.getResponseCode();
	}

	public Map<String, String> getCookies() {
		return response.getCookies();
	}

	public Map<String, String> getHeaders() {
		return response.getHeaders();
	}
	
	public <T> T getResponseBodyAs(Class<T> type) {
		return response.getResponseBodyAs(type);
	}

}
