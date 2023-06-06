package com.simple.api.core.util;

import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.simple.api.core.modal.RestResponse;

import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

/****
 * 
 * @author anjiboddupally
 *
 */

@Slf4j
public class TransformUtil {
	
	private TransformUtil() {};
	
	private final static ObjectMapper objMapper = new ObjectMapper();
	
	private final static XmlMapper xmlMapper = new XmlMapper();
	
	public static RestResponse transform(Response response) {
		
		return RestResponse.builder().body(response.getBody().asString())
				.cookies(response.cookies())
				.headers(response.headers().asList().stream().collect(Collectors.toMap(Header::getName, Header::getValue, (v1, v2) -> v1 + "," + v2)))
				.responseCode(response.getStatusCode())
				.build();
		
	}
	
	
	
	public static String convertPojoToJson(Object object) throws JsonProcessingException {
		
		return objMapper.writeValueAsString(object);
	}
	
	public static String convertPojoToXml(Object object) throws JsonProcessingException {
		
		return xmlMapper.writeValueAsString(object);
	}
	
	public static <T> T convertJsonToPojo(String jsonString, Class<T> type) {
		try {
			return objMapper.readValue(jsonString, type);
		} catch (JsonMappingException e) {
			log.error("Mapping Exception while mapping {}" , jsonString);
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			log.error("Parsing Exception whiling parsing {}" , jsonString);
			e.printStackTrace();
		}
		
		// check for non null wherever this method is being called
		return null;
	}
}
