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
import rsystems.Mirage.objects.Dispatcher;
import rsystems.Mirage.service.PlayerService;
import rsystems.Mirage.service.PlayerServiceImpl;

import javax.security.auth.login.LoginException;

@SpringBootApplication
public class MirageApplication {

	public static String prefix = Config.get("prefix");
	public static JDAImpl jda = null;
	public static PlayerService playerService;
	public static Dispatcher dispatcher;

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

			api.addEventListener(dispatcher = new Dispatcher());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

	@Bean
	CommandLineRunner run(PlayerService playerService){

		MirageApplication.playerService = playerService;

		return args -> {
		};

	}

}
