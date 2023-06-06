package com.simple.videogame.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/****
 * 
 * @author anjiboddupally
 *
 */

@Data
@NoArgsConstructor
public class VideoGame {

	private Integer id;

	private String name;

	private String releaseDate;

	private Integer reviewScore;

	private String category;

	private String rating;

}
