package rsystems.Mirage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gear{

	@JsonProperty("item_level_equipped")
	private int itemLevelEquipped;

	@JsonProperty("item_level_total")
	private int itemLevelTotal;

	public int getItemLevelEquipped(){
		return itemLevelEquipped;
	}

	public int getItemLevelTotal() {
		return itemLevelTotal;
	}
}