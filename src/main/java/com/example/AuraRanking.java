package com.example;

import java.util.HashMap;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AuraRanking extends ListenerAdapter {

    private final HashMap<String, HashMap<String, Integer>> serverUserPoints = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(!event.getAuthor().isBot()){

            String guildID = event.getGuild().getId();
            String authorID = event.getAuthor().getId();

            serverUserPoints.putIfAbsent(guildID, new HashMap<>());
            HashMap<String, Integer> userPoints = serverUserPoints.get(guildID);
            if(event.getMessage().getContentRaw().startsWith("!") && (!event.getMessage().getContentRaw().contains("auralevel") && !event.getMessage().getContentRaw().contains("leaderboard"))){
                userPoints.put(authorID, userPoints.getOrDefault(authorID, 0) + 3);
                System.out.println("bot command used. 3 points awarded");
                return;
            }
            int currentPoints = userPoints.getOrDefault(authorID, 0) + 1;
            userPoints.put(authorID, currentPoints);
            System.out.println("Awarding points to user " + event.getAuthor().getName());
            System.out.println("User " + event.getAuthor().getName() + " now has " + currentPoints + " points in guild " + event.getGuild().getName());
        }
    }

    public HashMap<String, Integer> getUserPoints(String guildId) {
        return serverUserPoints.getOrDefault(guildId, new HashMap<>());
    }
}
