package com.technovision.tutorialbot.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Listens for events and responds with our custom code.
 *
 * @author TechnoVision
 */
public class EventListener extends ListenerAdapter {

    /**
     * Event fires when an emoji reaction is added to a message.
     */
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String jumpLink = event.getJumpUrl();
        String emoji = event.getReaction().getReactionEmote().getAsReactionCode();
        String channel = event.getChannel().getAsMention();

        String message = user.getAsTag() + " reacted to a [message]("+jumpLink+") with " + emoji + " in the " + channel + " channel!";
        event.getGuild().getDefaultChannel().sendMessage(message).queue();
    }

    /**
     * Event fires when a message is sent in discord.
     * Warning: Will require "Guild Messages" gateway intent after August 2022!
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // WILL NOT WORK WITHOUT GATEWAY INTENT!
        String message = event.getMessage().getContentRaw();
        if (message.contains("ping")) {
            event.getChannel().sendMessage("pong").queue();
        }
    }

    /**
     * Event fires when a new member joins a guild
     * Warning: Will not work without "Guild Members" gateway intent!
     */
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        // WILL NOT WORK WITHOUT GATEWAY INTENT!
        String avatar = event.getUser().getEffectiveAvatarUrl();
        System.out.println(avatar);
    }

    /**
     * Event fires when a role is added to a guild member.
     * Warning: Will not work without "Guild Presences" gateway intent!
     */
    @Override
    public void onGuildMemberRoleAdd(@NotNull GuildMemberRoleAddEvent event) {
        // WILL NOT WORK WITHOUT GATEWAY INTENT!
        event.getGuild().getDefaultChannel().sendMessage("Somebody got a new role!").queue();
    }
}
