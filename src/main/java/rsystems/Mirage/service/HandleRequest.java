package rsystems.Mirage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.domain.RaiderIOResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HandleRequest {

    public static void requestInfo(String characterName, String realm, Long uid) throws IOException, InterruptedException {

        System.out.println("Started request for " + characterName);

        final String finalCharacterName = characterName.replace(" ", "");
        final String finalRealmName = realm.replace(" ", "");

        URI uri;

        try {

            uri = URI.create(String.format("https://raider.io/api/v1/characters/profile?region=us&realm=%s&name=%s&fields=covenant,guild,gear", finalRealmName, finalCharacterName));

        } catch(IllegalArgumentException e){
            System.out.println("Malformed URL detected.  Removing request from queue");
            MirageApplication.queue.remove(characterName);
            return;
        }
        // Create the client
        HttpClient client = HttpClient.newHttpClient();

        // Create the HTTP Request
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(uri)
                .build();


        // Send the Request
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if(response.statusCode() == 200) {

                        try {

                            RaiderIOResponse IOResponse = HandleRequest.responseObject(response.body());

                            Player player;
                            if (MirageApplication.playerService.getPlayer(IOResponse.getName()) == null) {
                                System.out.println("No player found, Registering new player");
                                player = new Player();
                                player.setDUID(uid);
                            } else {
                                System.out.println("Updating player: " + IOResponse.getName());
                                player = MirageApplication.playerService.getPlayer(IOResponse.getName());
                            }
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

                    }
                });
    }

    public static RaiderIOResponse responseObject(String httpResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(httpResponse, RaiderIOResponse.class);
    }
}
