package com.simple.api.core.modal;

import static com.simple.api.core.util.TransformUtil.convertJsonToPojo;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

/****
 * 
 * @author anjiboddupally
 *
 */

@Data
@Builder
public class RestResponse {

	private String body;
	
	private Map<String, String> headers;
	
	private Map<String, String> cookies;
	
	private Integer responseCode;
	
	public <T> T getResponseBodyAs(Class<T> type) {
		
		return convertJsonToPojo(this.getBody(), type);
	}
}
