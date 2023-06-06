package com.simple.api.core.util;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;

/****
 * 
 * @author anjiboddupally
 *
 */
public class ApiUtil {

	private static final String CONNECTION_TIMEOUT = "http.connection.timeout";

	private static final String SOCKET_TIMEOUT = "http.socket.timeout";
	
	private ApiUtil() {
	};

	public static RestAssuredConfig getConfig(long timeout) {
		RestAssuredConfig restAssuredConfig = new RestAssuredConfig();
		restAssuredConfig.httpClient(HttpClientConfig.httpClientConfig().setParam(CONNECTION_TIMEOUT, timeout)
				.setParam(SOCKET_TIMEOUT, timeout));
		return restAssuredConfig;
	}

}
