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

public class AddRole extends Command {
    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, PrivateMessageReceivedEvent event) {

    }

    @Override
    public void dispatch(User sender, MessageChannel channel, Message message, String content, GuildMessageReceivedEvent event) {
        String[] args = content.split("\\s+");
        if(args.length >= 2) {

            Player player;
            player = MirageApplication.playerService.getPlayer(args[0]);

            if(player.getDUID().equals(sender.getIdLong())){
                Role role = MirageApplication.playerService.getRole(args[1]);

                if(role != null){
                    MirageApplication.playerService.addRoleToUser(player.getCharacterName(),role.getName());
                }
            }
        }
    }

    @Override
    public String getHelp() {
        return "`{prefix}{command} [Character Name] [Role]`\n" +
                "Register your character as a certain role.\n" +
                "Available Roles: DPS, RDPS, Heal, Tank, Coach";
    }

    @Override
    public Integer getRequiredArgSize() {
        return 2;
    }
}
