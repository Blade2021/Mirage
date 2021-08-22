package rsystems.Mirage.commands.playerRelated;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.objects.Command;
import rsystems.Mirage.service.RaiderIO;

public class Update extends Command {
    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        Player player;

        if(MirageApplication.playerService.getPlayer(content) != null){
            player = MirageApplication.playerService.getPlayer(content);
            if(player.getDUID().equals(sender.getIdLong())){
                RaiderIO.requestInfo(player.getCharacterName(),player.getRealmName().replace(" ",""),sender.getIdLong());
                reply(event, "I've added your character to the update queue.");
            } else {
                reply(event, "That character is not registered to you");
            }
        } else {
            reply(event, "I could not find that character.  Sorry.");
        }
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public Integer getRequiredArgSize() {
        return 1;
    }
}
