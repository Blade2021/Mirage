package rsystems.Mirage.commands.playerRelated;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import rsystems.Mirage.objects.Command;
import rsystems.Mirage.service.RaiderIO;

public class Register extends Command {


    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        String[] args = content.split("\\s+");
        if(args.length >= 2){
            RaiderIO.requestInfo(args[0],args[1],sender.getIdLong());
            reply(event,"I've added your character to the queue.  Please wait a moment before trying to pull character data.");
        }
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String[] getAliases(){
        return new String[] {"update"};
    }

}
