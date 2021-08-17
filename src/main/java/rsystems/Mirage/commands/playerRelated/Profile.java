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

public class Profile extends Command {


    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        Player player = null;
        player = MirageApplication.playerService.getPlayer(content);

        if(player != null){
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Character Profile")
                    .setDescription(player.getCharacterName())
                    .setThumbnail("https://wow.zamimg.com/images/wow/icons/large/inv_helm_robe_raidpriest_k_01.jpg")
                    .addField("Class",player.getCharacterClass(),true)
                    .addBlankField(true)
                    .addField("Spec",player.getCharacterSpec(),true)
                    .addField("iLVL",Integer.toString(player.getCharacterItemLevel()),false);

            StringBuilder sb = new StringBuilder();

            if(player.getRoles().size() >= 1){
                for(Role role:player.getRoles()){
                    sb.append(role.getName()).append("\n");
                }
            }

            eb.addField("Role(s):",sb.toString(),false);

            switch (player.getCharacterClass().toLowerCase()){
                case "priest":
                    eb.setColor(Color.WHITE);
                    eb.setThumbnail("https://static.wikia.nocookie.net/wowpedia/images/5/50/Priest_Crest.png");
                    break;
                case "paladin":
                    eb.setColor(Color.PINK);
                    break;
                case "warrior":
                    eb.setColor(Color.GRAY);
                    eb.setThumbnail("https://static.wikia.nocookie.net/wowpedia/images/4/4f/Warrior_Crest.png");
                    break;

            }

            reply(event,eb.build());
            eb.clear();
        }
    }

    @Override
    public String getHelp() {
        return null;
    }
}
