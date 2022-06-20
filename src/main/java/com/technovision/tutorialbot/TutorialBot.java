package com.technovision.tutorialbot;

import com.technovision.tutorialbot.listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

/**
 * JDA-5.0.0 Discord Bot Tutorial Series.
 * This is the main class where we initialize our bot.
 *
 * @author TechnoVision <a href="https://www.youtube.com/TechnoVisionTV">...</a>
 */
public class TutorialBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException occurs when bot token is invalid.
     */
    public TutorialBot() throws LoginException {
        // Load environment variables
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        // Build shard manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("TechnoVisionTV"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);
        shardManager = builder.build();

        // Register listeners
        shardManager.addEventListener(new EventListener());
    }

    /**
     * Retrieves the bot environment variables.
     * @return the DotEnv instance for the bot.
     */
    public Dotenv getConfig() { return config; }

    /**
     * Retrieves the bot shard manager.
     * @return the ShardManager instance for the bot.
     */
    public ShardManager getShardManager() { return shardManager; }

    /**
     * Main method where we start our bot.
     */
    public static void main(String[] args) {
        try {
            TutorialBot bot = new TutorialBot();
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid!");
        }
    }
}
