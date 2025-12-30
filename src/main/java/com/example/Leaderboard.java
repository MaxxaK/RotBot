package com.example;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Leaderboard extends ListenerAdapter {

    private final AuraRanking auraRanking;

    public Leaderboard(AuraRanking auraRanking){
        this.auraRanking = auraRanking;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!leaderboard")) {
            String guildID = event.getGuild().getId();
            EmbedBuilder leaderboard = new EmbedBuilder();
            leaderboard.setTitle("Highest Aura in " + event.getGuild().getName());
            leaderboard.setColor(0x1F8B4C);

            System.out.println("Fetching points for guild: " + guildID);
            Map<String, Integer> userPoints = auraRanking.getUserPoints(guildID);
            System.out.println("Points for guild: " + userPoints);

            String topUsers = userPoints.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(entry -> {
                    String userID = entry.getKey();
                    String userName = event.getJDA().getUserById(userID) != null
                            ? event.getJDA().getUserById(entry.getKey()).getEffectiveName()
                            : "Unknown User";

                        if (userName.equals("Unknown User")) {
                            try {
                                userName = event.getJDA().retrieveUserById(userID).submit().get().getEffectiveName();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } 
                        }
                    return "**" + userName + ": " + entry.getValue() + " aura points**";
                })
                .collect(Collectors.joining("\n"));

            leaderboard.setDescription(topUsers.isEmpty() ? "Nobody has aura! Y'all are losers!!!" : topUsers);
            event.getMessage().replyEmbeds(leaderboard.build()).queue();
            System.out.println("Leaderboard for " + event.getGuild().getName() + ":\n" + topUsers);
        } else if (event.getMessage().getContentRaw().equalsIgnoreCase("!auralevel")){
            String guildID = event.getGuild().getId();
            String authorID = event.getAuthor().getId();
            EmbedBuilder leaderboard = new EmbedBuilder();
            leaderboard.setTitle("Your Aura Level in " + event.getGuild().getName());
            leaderboard.setColor(0x1F8B4C);

            Map<String, Integer> userPoints = auraRanking.getUserPoints(guildID);
            Integer points = userPoints.getOrDefault(authorID, 0);

            String topUsers = "**" + event.getAuthor().getEffectiveName() + ": " + points + " Aura Points**";

            leaderboard.setDescription(topUsers);
            event.getMessage().replyEmbeds(leaderboard.build()).queue();
            System.out.println("Points for user " + event.getAuthor().getName() + ": " + points);
        }
    }

}


