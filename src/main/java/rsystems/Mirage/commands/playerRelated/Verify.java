package rsystems.Mirage.commands.playerRelated;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.objects.Command;
import rsystems.Mirage.service.VerifyUser;

public class Verify extends Command {

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

        if(VerifyUser.verifyDiscordUser(sender.getIdLong(), content)){
            reply(event,"Verified");
        } else {
            reply(event, "Unverified");
        }

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        if(VerifyUser.verifyDiscordUser(sender.getIdLong(), MirageApplication.playerService.getPlayer(content))){
            reply(event,"Verified");
        } else {
            reply(event, "Unverified");
        }
    }

    @Override
    public String getHelp() {
        return null;
    }
}
