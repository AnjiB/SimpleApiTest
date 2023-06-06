package com.simple.api.core.contract;

import com.simple.api.core.modal.RestRequest;

/****
 * 
 * @author anjiboddupally
 *
 */
public interface ApiClient {
	
	ApiResponse send(RestRequest request) throws Exception;
}
