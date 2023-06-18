package com.simple.api.core.modal;

import java.util.List;
import java.util.Map;

import com.simple.api.core.enums.RequestMethod;

import io.restassured.filter.Filter;
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
	
	// RestAssured specific. In other frameworks, it could be Interceptors or something else.
	private List<Filter> filters;
	
	@Default
	private ContentType contentType = ContentType.JSON;

}
