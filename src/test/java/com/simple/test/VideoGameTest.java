package com.simple.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.simple.api.core.contract.ApiResponse;
import com.simple.videogame.client.VideGameTestClient;
import com.simple.videogame.dto.VideoGame;


/****
 * 
 * @author anjiboddupally
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VideoGameTest {
	
	private VideGameTestClient videoGameTest;
	
	
	@BeforeAll
	public void setUp() {
		videoGameTest = new VideGameTestClient();
	}

	@Test
	public void testAllVideoGames() throws Exception {
	
		ApiResponse response = videoGameTest.getAllVideoGames();
		
		VideoGame[] videoGames = response.getResponseBodyAs(VideoGame[].class);
		
		Assertions.assertThat(videoGames.length).isGreaterThan(0);
	}
}
