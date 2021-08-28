package rsystems.Mirage.commands.playerRelated;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.objects.Command;
import rsystems.Mirage.objects.PlayerInfoRequest;

public class Register extends Command {

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        String[] args = content.split("\\s+");
        PlayerInfoRequest request = new PlayerInfoRequest(args[0],args[1],sender.getIdLong());
        MirageApplication.queue.putIfAbsent(request.getChracterName(), request);
        reply(event,"I've added your character to the queue.  Please wait a moment before trying to pull character data.");

    }

    @Override
    public String getHelp() {
        return "`{prefix}{command} [Character Name] [Realm Name]`\n" +
                "Register a character to the BoT with YOUR discord ID.";
    }

    @Override
    public Integer getRequiredArgSize() {
        return 2;
    }

    @Override
    public String[] getAliases(){
        return new String[] {};
    }

}
