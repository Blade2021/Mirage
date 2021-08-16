package rsystems.Mirage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.JDAImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.domain.Role;
import rsystems.Mirage.objects.Command;
import rsystems.Mirage.service.PlayerService;

import javax.security.auth.login.LoginException;

@SpringBootApplication
public class MirageApplication {

	public static String prefix = Config.get("prefix");
	public static JDAImpl jda = null;

	public static void main(String[] args) throws LoginException {
		SpringApplication.run(MirageApplication.class, args);

		JDA api = JDABuilder.createDefault(Config.get("token"))
				.enableIntents(GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_PRESENCES)
				.setMemberCachePolicy(MemberCachePolicy.ALL)
				.enableCache(CacheFlag.ACTIVITY)
				.setChunkingFilter(ChunkingFilter.ALL)
				.build();


		try {
			// Wait for discord jda to completely load
			api.awaitReady();
			jda = (JDAImpl) api;

		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

	@Bean
	CommandLineRunner run(PlayerService playerService){
		return args -> {
			playerService.saveRole(new Role(null, "DPS"));
			playerService.saveRole(new Role(null, "RPDS"));
			playerService.saveRole(new Role(null, "Tank"));
			playerService.saveRole(new Role(null, "Heal"));

			playerService.savePlayer(new Player(null, Long.valueOf("337211225735823361"), "Red","Dark Iron", "Priest","Holy",212,null));
			playerService.savePlayer(new Player(null, Long.valueOf("337211225735823361"), "Blue","Dark Iron", "Warrior","Fury",220,null));
			playerService.savePlayer(new Player(null, Long.valueOf("337211225735823361"), "Green","Dark Iron", "Paladin","Holy",187,null));
			playerService.savePlayer(new Player(null, Long.valueOf("349994608165519363"), "Moo","Dark Iron", "Monk","Restro",223,null));
			playerService.savePlayer(new Player(null, Long.valueOf("349994608165519363"), "Chicken","Dark Iron", "Priest","Destruction",231,null));
			playerService.savePlayer(new Player(null, Long.valueOf("349994608165519363"), "Pig","Dark Iron", "Shaman","Enhancement",202,null));

			playerService.addRoleToUser("Red","Heal");
			playerService.addRoleToUser("Chicken","Heal");

			Player tempPlayer;

			tempPlayer = playerService.getPlayer("Green");
			tempPlayer.setCharacterItemLevel(113);
			playerService.savePlayer(tempPlayer);

			tempPlayer = playerService.getPlayer("Chicken");
			tempPlayer.setCharacterClass("Paladin");
			playerService.savePlayer(tempPlayer);

			tempPlayer = playerService.getPlayer("Pig");
			tempPlayer.setCharacterName("Piglett");
			playerService.savePlayer(tempPlayer);

		};

	}

}
