package rsystems.Mirage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.JDAImpl;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;
import rsystems.Mirage.domain.RaiderIOResponse;
import rsystems.Mirage.objects.Dispatcher;
import rsystems.Mirage.objects.PlayerInfoRequest;
import rsystems.Mirage.service.PlayerService;
import rsystems.Mirage.tasks.RaiderIORequestTask;

import javax.security.auth.login.LoginException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MirageApplication {

	public static String prefix = Config.get("prefix");
	public static JDAImpl jda = null;
	public static PlayerService playerService;
	public static Dispatcher dispatcher;
	public static RestTemplate restTemplate;
	public static Map<String,PlayerInfoRequest> queue = new LinkedMap<>();


	public static RaiderIORequestTask task;

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

			ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
			executorService.scheduleWithFixedDelay(task = new RaiderIORequestTask(),10000,10000, TimeUnit.MILLISECONDS);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean
	CommandLineRunner run(PlayerService playerService){

		MirageApplication.playerService = playerService;

		return args -> {
		};

	}

}
