package rsystems.Mirage.commands.playerRelated;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.domain.Role;
import rsystems.Mirage.objects.Command;

import java.util.List;

public class ListToons extends Command {
    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {

        StringBuilder sb = new StringBuilder();
        int index = 0;
        int characterNameSize = 15;
        int characterRealmSize = 12;
        int characterClassSize = 12;
        int characterSpecSize = 15;
        int characterRolesSize = 12;
        int characterItemLevelSize = 4;

        sb.append("```add").append("\n");
        sb.append("|     M I R A G E      |").append("\n");
        sb.append("|# | Character     | Realm      | iLVL| Class       | Spec           | Role(s)    ").append("\n");
        sb.append("| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - |").append("\n");

        List<Player> players = MirageApplication.playerService.getPlayersByUID(sender.getIdLong());
        for(Player player:players){
            index++;

            String name;
            if(player.getCharacterName().length()>characterNameSize){
                name = player.getCharacterName().substring(0,characterNameSize-1);
            } else {
                name = String.format("%" + (-characterNameSize) + "s",player.getCharacterName());
            }

            String realm;
            if(player.getRealmName().length()>characterRealmSize){
                realm = player.getRealmName().substring(0,characterRealmSize-1);
            } else {
                realm = String.format("%" + (-characterRealmSize) + "s",player.getRealmName());
            }

            String itemLevelString;
            if(player.getCharacterItemLevel().toString().length()>characterItemLevelSize){
                itemLevelString = player.getRealmName().substring(0,characterItemLevelSize-1);
            } else {
                itemLevelString = String.format("%" + (-characterItemLevelSize) + "s",player.getCharacterItemLevel().toString());
            }

            String playerClass;
            if(player.getPlayerClass().length()>characterClassSize){
                playerClass = player.getPlayerClass().substring(0,characterClassSize-1);
            } else {
                playerClass = String.format("%" + (-characterClassSize) + "s",player.getPlayerClass());
            }

            String playerSpec;
            if(player.getCurrentSpecName().length()>characterSpecSize){
                playerSpec = player.getCurrentSpecName().substring(0,characterSpecSize-1);
            } else {
                playerSpec = String.format("%" + (-characterSpecSize) + "s",player.getCurrentSpecName());
            }

            StringBuilder playerRoles = new StringBuilder();
            String playerRolesFinal = null;
            if(player.getRoles().size() != 0) {
                for (Role role : player.getRoles()) {
                    playerRoles.append(role.getName()).append(" ");
                }
                if (playerRoles.toString().length() > characterRolesSize) {
                    playerRolesFinal = playerRoles.substring(0, characterRolesSize - 1);
                } else {
                    playerRolesFinal = String.format("%" + (-characterRolesSize) + "s", playerRoles);
                }
            }

            sb.append("|").append(index).append(" ");
            sb.append("|").append(name);

            sb.append("|").append(realm);
            sb.append("| ").append(itemLevelString);

            sb.append("| ").append(playerClass);
            sb.append("| ").append(playerSpec).append("|");
            if(playerRolesFinal != null) {
                sb.append(playerRolesFinal);
            }

            sb.append("\n");

        }

        sb.append("```");

        reply(event,sb.toString());
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public Integer getRequiredArgSize() {
        return 0;
    }
}
