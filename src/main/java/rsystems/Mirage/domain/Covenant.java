package rsystems.Mirage.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Covenant{

	@JsonProperty("name")
	private String name;

	@JsonProperty("renown_level")
	private int renownLevel;

	@JsonProperty("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getRenownLevel(){
		return renownLevel;
	}

	public int getId(){
		return id;
	}
}