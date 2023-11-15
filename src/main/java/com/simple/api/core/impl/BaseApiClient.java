package com.simple.api.core.impl;

import static com.simple.api.core.util.ApiUtil.getConfig;
import static com.simple.api.core.util.TransformUtil.fromOkHttpResponse;
import static com.simple.api.core.util.TransformUtil.fromRestAssuredResponse;
import static io.restassured.RestAssured.given;
import static java.lang.Long.valueOf;
import static java.lang.System.getProperty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;
import com.simple.api.core.contract.ApiClient;
import com.simple.api.core.contract.ApiResponse;
import com.simple.api.core.enums.RequestMethod;
import com.simple.api.core.modal.RestRequest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

/****
 * 
 * @author anjiboddupally
 *
 */
@Slf4j
public abstract class BaseApiClient implements ApiClient {

	private URI baseURI;

	private long waitTime = valueOf(getProperty("api.wait.time", "30"));

	private OkHttpClient client = new OkHttpClient();

	public BaseApiClient(String urlString) {
		setBaseUri(urlString);	
	}
	
	public void setBaseUri(@NonNull String urlString) {
		
		try {
			baseURI = new URI(urlString);
		} catch (URISyntaxException e) {
			log.error("Request url {} is malformed", urlString);
			throw new IllegalArgumentException("Request url is malformed");
		}
	}
	
	public URI getBaseUri() {
		return this.baseURI;
	}

	
	/***
	 * 
	 * RestAssured Implementation
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ApiResponse send(RestRequest request) throws Exception {

		Response response = given().spec(getSpecBuilder(request))
				.request(Method.valueOf(request.getMethod().toString()));

		return new ApiResponseImpl(fromRestAssuredResponse(response));

	}
	
	
	
	/***
	 * OK Http Implementation
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */

	public ApiResponse send2(RestRequest request) throws Exception {

		String urlBString = request.getPath() != null ? getBaseUri().toString() + request.getPath() : getBaseUri().toString();
		
		HttpUrl.Builder urlBuilder = HttpUrl.parse(urlBString).newBuilder();

		if (request.getQueryParams() != null && !request.getQueryParams().isEmpty()) {
			request.getQueryParams().entrySet().forEach(y -> urlBuilder.addQueryParameter(y.getKey(), y.getValue()));
		}

		Builder okReq = new Request.Builder().url(urlBuilder.build().toString());

		if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
			request.getHeaders().entrySet().forEach(x -> okReq.addHeader(x.getKey(), x.getValue()));
		}

		if (request.getCookies() != null && !request.getCookies().isEmpty()) {
			String cookieString = Joiner.on(";").withKeyValueSeparator("=").join(request.getCookies());

			okReq.addHeader("Cookie", cookieString);
		}

		if (request.getBody() != null) {

			@SuppressWarnings("deprecation")
			RequestBody body = RequestBody.create(MediaType.parse(request.getContentType().getAcceptHeader()),
					request.getBody());

			if (request.getMethod().equals(RequestMethod.POST))
				okReq.post(body);

			if (request.getMethod().equals(RequestMethod.PUT))
				okReq.put(body);

			if (request.getMethod().equals(RequestMethod.PATCH))
				okReq.patch(body);

		} else {

			if (request.getMethod().equals(RequestMethod.DELETE))
				okReq.delete();

			if (request.getMethod().equals(RequestMethod.GET))
				okReq.get();

		}

		
		okhttp3.Response okResponse = client.newCall(okReq.build()).execute();
		
		return new ApiResponseImpl(fromOkHttpResponse(okResponse)); 
		
	}

	private RequestSpecification getSpecBuilder(RestRequest request) throws Exception {

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

		requestSpecBuilder.setBaseUri(getBaseUri());

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
		
		if(request.getFilters() != null && !request.getFilters().isEmpty())
			requestSpecBuilder.addFilters(request.getFilters());
		
		if(Boolean.valueOf(System.getProperty("logRestAssured", "false")))
			requestSpecBuilder.addFilter(new AllureRestAssured());

		requestSpecBuilder.setConfig(getConfig(waitTime));

		return requestSpecBuilder.build();

	}

}
