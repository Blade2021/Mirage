package rsystems.Mirage.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Guild{

	@JsonProperty("name")
	private String name;

	@JsonProperty("realm")
	private String realm;

	public String getName(){
		return name;
	}

	public String getRealm(){
		return realm;
	}
}