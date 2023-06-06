package com.simple.api.core.impl;

import static com.simple.api.core.util.ApiUtil.getConfig;
import static com.simple.api.core.util.TransformUtil.transform;
import static io.restassured.RestAssured.given;
import static java.lang.Long.valueOf;
import static java.lang.System.getProperty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.simple.api.core.contract.ApiClient;
import com.simple.api.core.contract.ApiResponse;
import com.simple.api.core.modal.RestRequest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

/****
 * 
 * @author anjiboddupally
 *
 */
@Slf4j
public abstract class BaseApiClient implements ApiClient {

	private URI baseURI;
	
	private long waitTime = valueOf(getProperty("api.wait.time", "30"));

	public BaseApiClient(String urlString) {

		try {
			baseURI = new URI(urlString);
		} catch (URISyntaxException e) {
			log.error("Request url {} is malformed", urlString);
			throw new IllegalArgumentException("Request url is malformed");
		}
	}

	public ApiResponse send(RestRequest request) throws Exception {
		
		Response response = given().spec(getSpecBuilder(request)).request(Method.valueOf(request.getMethod().toString()));
		
		return new ApiResponseImpl(transform(response));
		
		
	}

	private RequestSpecification getSpecBuilder(RestRequest request) throws Exception {

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

		requestSpecBuilder.setBaseUri(baseURI);

		if (request.getContentType() != null) {
			requestSpecBuilder.setContentType(request.getContentType());
		}

		if (StringUtils.isNotEmpty(request.getPath()))
			requestSpecBuilder.setBasePath(request.getPath());

		if (request.getQueryParams() != null && !request.getQueryParams().isEmpty())
			requestSpecBuilder.addQueryParams(request.getQueryParams());

		if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
			requestSpecBuilder.addHeaders(request.getHeaders());
		}

		if (request.getCookies() != null && !request.getCookies().isEmpty())
			requestSpecBuilder.addCookies(request.getCookies());

		if (!Objects.isNull(request.getBody()))
			requestSpecBuilder.setBody(request.getBody());
		
		requestSpecBuilder.setConfig(getConfig(waitTime));

		return requestSpecBuilder.build();

	}

}
