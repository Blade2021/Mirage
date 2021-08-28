package rsystems.Mirage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.domain.RaiderIOResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HandleRequest {

    public static void requestInfo(String characterName, String realm, Long uid) {

        System.out.println("Started request for " + characterName);

        String uri = String.format("https://raider.io/api/v1/characters/profile?region=us&realm=%s&name=%s&fields=covenant,guild,gear", realm, characterName);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(uri))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println(response.statusCode());
                    try {

                        RaiderIOResponse IOResponse = HandleRequest.responseObject(response.body());
                        System.out.println(IOResponse.getGear().toString());

                        Player player;
                        if(MirageApplication.playerService.getPlayer(IOResponse.getName()) == null) {
                            System.out.println("No player found, Registering new player");
                            player = new Player();
                            player.setDUID(uid);
                        } else {
                            System.out.println("Updating player: " + IOResponse.getName());
                            player = MirageApplication.playerService.getPlayer(IOResponse.getName());
                        }
                        System.out.println("Character Info");
                        player.setCharacterName(IOResponse.getName());
                        player.setPlayerClass(IOResponse.getPlayerClass());
                        player.setCovenantName(IOResponse.getCovenant().getName());
                        player.setGuildName(IOResponse.getGuild().getName());
                        player.setRealmName(IOResponse.getRealm());
                        player.setCharacterItemLevel(IOResponse.getGear().getItemLevelEquipped());
                        player.setLastUpdated(IOResponse.getLastCrawledAt());
                        player.setCurrentSpecName(IOResponse.getActiveSpecName());
                        player.setRegion(IOResponse.getRegion());
                        player.setRace(IOResponse.getRace());
                        player.setProfileURL(IOResponse.getProfileUrl());
                        player.setThumbnailURL(IOResponse.getThumbnailUrl());

                        MirageApplication.playerService.savePlayer(player);

                        MirageApplication.queue.remove(characterName);
                        System.out.println("New Queue Size: " + MirageApplication.queue.size());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return response;
                });
        //return IOResponse;
    }

    public static RaiderIOResponse responseObject(String httpResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(httpResponse, RaiderIOResponse.class);
    }
}
