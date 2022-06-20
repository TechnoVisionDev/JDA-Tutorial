package com.technovision.tutorialbot;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

/**
 * JDA-5.0.0 Discord Bot Tutorial Series.
 * This is the main class where we initialize our bot.
 *
 * @author TechnoVision <a href="https://www.youtube.com/TechnoVisionTV">...</a>
 */
public class TutorialBot {

    private final ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException occurs when bot token is invalid.
     */
    public TutorialBot() throws LoginException {
        String token = "YOUR_BOT_TOKEN";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("TechnoVisionTV"));
        shardManager = builder.build();
    }

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
