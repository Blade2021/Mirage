package rsystems.Mirage.commands.playerRelated;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.domain.Role;
import rsystems.Mirage.objects.Command;

import java.awt.*;

public class GrabPlayer extends Command {

    private static final String[] ALIASES = new String[] {"gp", "getPlayer", "get"};

    @Override
    public String[] getAliases(){
        return ALIASES;
    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {
    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        Player player = MirageApplication.playerService.getPlayer(content);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(player.getCharacterName())




                /*.setDescription(String.format("Race: %s     Class: %s       Realm: %s\n" +
                                "Guild: %s      Current Spec: %s        Covenant: %s",
                                player.getRace(),player.getPlayerClass(),player.getRealmName(),player.getGuildName(),player.getCurrentSpecName(),player.getCovenantName()));

                */
                .addField("Realm",player.getRealmName(),true)
                .addField("Race",player.getRace(),true)
                .addField("Class",player.getPlayerClass(),true)
                .addField("Guild",player.getGuildName(),true)
                .addField("Covenant",player.getCovenantName(),true)
                .addField("Current Spec",player.getCurrentSpecName(),true)
                .addField("iLVL",player.getCharacterItemLevel().toString(),true);

                StringBuilder sb = new StringBuilder();
                for(Role role:player.getRoles()){
                    sb.append(role.getName()).append("\n");
                }

                eb.addField("Role(s)",sb.toString(),false)
                .setThumbnail(player.getThumbnailURL());

        switch(player.getPlayerClass().toLowerCase()){
            case "priest":
                eb.setColor(Color.WHITE);
                break;
            case "paladin":
                eb.setColor(Color.decode("#F48CBA"));
                break;
            case "demon hunter":
                eb.setColor(Color.decode("#A330C9"));
                break;
            case "warrior":
                eb.setColor(Color.decode("#C69B6D"));
                break;
            case "druid":
                eb.setColor(Color.decode("#FF7C0A"));
                break;
            case "hunter":
                eb.setColor(Color.decode("#AAD372"));
                break;
            case "mage":
                eb.setColor(Color.decode("#3FC7EB"));
                break;
            case "monk":
                eb.setColor(Color.decode("#00FF98"));
                break;
            case "rogue":
                eb.setColor(Color.decode("#FFF468"));
                break;
            case "shaman":
                eb.setColor(Color.decode("#0070DD"));
                break;
            case "warlock":
                eb.setColor(Color.decode("#8788EE"));
                break;

        }

        reply(event, eb.build());
        eb.clear();
    }

    @Override
    public String getHelp() {
        return null;
    }
}
