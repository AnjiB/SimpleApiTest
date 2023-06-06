package com.simple.api.core.modal;

import java.util.Map;

import com.simple.api.core.enums.RequestMethod;

import io.restassured.http.ContentType;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

/****
 * 
 * @author anjiboddupally
 *
 */

@Data
@Builder
public class RestRequest {

	private String body;
	
	private Map<String, String> headers;
	
	private Map<String, String> cookies;
	
	private Map<String, String> queryParams;
	
	private String path;
	
	private RequestMethod method;
	
	@Default
	private ContentType contentType = ContentType.JSON;

}
