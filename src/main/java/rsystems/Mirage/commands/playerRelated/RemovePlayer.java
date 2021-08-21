package rsystems.Mirage.commands.playerRelated;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.objects.Command;

public class RemovePlayer extends Command {
    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        Player player;
        player = MirageApplication.playerService.getPlayer(content);

        if(player != null){
            boolean authorized = player.getDUID().equals(sender.getIdLong());

            if(authorized){
                MirageApplication.playerService.deletePlayerById(player.getId());
                reply(event,"Your character has been removed");
            } else {
                reply(event,"You are not authorized for that action");
            }
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

    @Override
    public String getName(){
        return "Remove";
    }
}
