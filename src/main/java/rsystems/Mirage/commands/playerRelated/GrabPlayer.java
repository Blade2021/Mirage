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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        if(player != null) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(String.format("%s - %s", player.getCharacterName(), player.getRealmName()))
                    .addField("Race", player.getRace(), true)
                    .addField("Class", player.getPlayerClass(), true)
                    .addField("Covenant", player.getCovenantName(), true)
                    .addField("Guild", player.getGuildName(), true)
                    .addField("Current Spec", player.getCurrentSpecName(), true)
                    .addField("iLVL", player.getCharacterItemLevel().toString(), true);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy H:m:s");
            String dateTime = player.getLastUpdated().substring(0,player.getLastUpdated().indexOf("."));

            LocalDateTime date = LocalDateTime.parse(dateTime,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String lastCrawled = formatter.format(date);

            eb.setFooter("Last Crawled Datetime: " + lastCrawled);


            StringBuilder sb = new StringBuilder();
            for (Role role : player.getRoles()) {
                sb.append(role.getName()).append("\n");
            }

            eb.addField("Role(s)", sb.toString(), true)
                    .setThumbnail(player.getThumbnailURL());

            switch (player.getPlayerClass().toLowerCase()) {
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
        } else {
            reply(event, "Sorry that character is not registered yet.\n" +
                    "If you are the owner, Use !register to register it for tracking");
        }
    }

    @Override
    public String getHelp() {
        return "`{prefix}{command} [Character Name]`\n" +
                "Get information about a character IF it is registered.";
    }

    @Override
    public Integer getRequiredArgSize() {
        return 1;
    }
}
