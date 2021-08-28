package rsystems.Mirage.objects;

public class PlayerInfoRequest {

    String chracterName;
    String realmName;
    Long duid;

    public PlayerInfoRequest(String chracterName, String realmName, Long duid) {
        this.chracterName = chracterName;
        this.realmName = realmName;
        this.duid = duid;
    }

    public String getChracterName() {
        return chracterName;
    }

    public String getRealmName() {
        return realmName;
    }

    public Long getDuid() {
        return duid;
    }
}
