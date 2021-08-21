package rsystems.Mirage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RaiderIOResponse{

	@JsonProperty("last_crawled_at")
	private String lastCrawledAt;

	@JsonProperty("race")
	private String race;

	@JsonProperty("profile_url")
	private String profileUrl;

	@JsonProperty("thumbnail_url")
	private String thumbnailUrl;

	@JsonProperty("active_spec_role")
	private String activeSpecRole;

	@JsonProperty("guild")
	private Guild guild;

	@JsonProperty("covenant")
	private Covenant covenant;

	@JsonProperty("name")
	private String name;

	@JsonProperty("active_spec_name")
	private String activeSpecName;

	@JsonProperty("realm")
	private String realm;

	@JsonProperty("region")
	private String region;

	@JsonProperty("class")
	private String playerClass;

	@JsonProperty("gear")
	private Gear gear;

	public String getLastCrawledAt(){
		return lastCrawledAt;
	}

	public String getRace(){
		return race;
	}

	public String getProfileUrl(){
		return profileUrl;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	public String getActiveSpecRole(){
		return activeSpecRole;
	}

	public Guild getGuild(){
		return guild;
	}

	public Covenant getCovenant(){
		return covenant;
	}

	public String getName(){
		return name;
	}

	public String getActiveSpecName(){
		return activeSpecName;
	}

	public String getRealm(){
		return realm;
	}

	public String getRegion(){
		return region;
	}

	public String getPlayerClass(){
		return playerClass;
	}

	public Gear getGear(){
		return gear;
	}
}