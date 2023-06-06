package com.simple.videogame.client;

import static org.awaitility.Awaitility.await;

import org.awaitility.Duration;

import com.simple.api.core.contract.ApiResponse;
import com.simple.api.core.enums.RequestMethod;
import com.simple.api.core.impl.BaseApiClient;
import com.simple.api.core.modal.RestRequest;
import com.simple.api.core.util.ConfigLoader;

/****
 * 
 * @author anjiboddupally
 *
 */
public class VideGameTestClient extends BaseApiClient {

	private static final String VIDEO_GAME_BASE_URL_STRING = ConfigLoader.getInstance().getProps().get("VIDEO_GAME_DB");

	public VideGameTestClient() {
		super(VIDEO_GAME_BASE_URL_STRING);
	}

	public ApiResponse getAllVideoGames() throws Exception {

		RestRequest request = RestRequest.builder().method(RequestMethod.GET).path("/api/videogame").build();

		await().atLeast(Duration.ONE_HUNDRED_MILLISECONDS).atMost(Duration.FIVE_SECONDS).with()
				.pollInterval(Duration.ONE_HUNDRED_MILLISECONDS).until(() -> send(request).getStatusCode() == 200);

		return send(request);

	}

}
