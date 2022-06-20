package com.technovision.tutorialbot.listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
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
     * Will require "Guild Messages" gateway intent after August 2022!
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains("ping")) {
            event.getChannel().sendMessage("pong").queue();
        }
    }

    /**
     * Event fires when a new member joins a guild
     * Requires "Guild Members" gateway intent!
     */
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        Role role = event.getGuild().getRoleById(988342442430443540L);
        if (role != null) {
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
        }
    }

    /**
     * Event fires when a user updates their online status
     * Requires "Guild Presences" gateway intent AND cache enabled!
     */
    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
        // WILL NOT WORK WITHOUT USER CACHE (See Episode 5)
        User user = event.getUser();
        String message = user.getAsTag() + " updated their online status!";
        event.getGuild().getDefaultChannel().sendMessage(message).queue();
    }
}
