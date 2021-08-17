package rsystems.Mirage.service;

import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;

public class VerifyUser {

    public static boolean verifyDiscordUser(Long discordID, Player player){
        return player.getDUID().equals(discordID);
    }

    public static boolean verifyDiscordUser(Long discordID, String characterName){

        Player player = null;
        player = MirageApplication.playerService.getPlayer(characterName);

        if(player != null){
            return player.getDUID().equals(discordID);
        } else {
            return false;
        }
    }

}
