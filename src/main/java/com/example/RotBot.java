package com.example;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class RotBot extends ListenerAdapter {
    public static void main(String[] args) {
        String token = "<your_bot_token_here>"; // Replace with your bot token

        AuraRanking aura = new AuraRanking();
        Leaderboard scores = new Leaderboard(aura);

        try {
            JDABuilder.createDefault(token)
                    .setActivity(Activity.playing("Rizzing Up The Huzz"))
                    .addEventListeners(new RotBot(), aura, scores) // Use App as the event listener
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String content = event.getMessage().getContentRaw();
            if (content.startsWith("!uzz ")) {
                String word = content.substring(5).trim();
                if(HuzzMapOps.getUzzLibrary().containsValue(word)){
                    event.getChannel().sendMessage("Usage: !uzz <word>").queue();
                    return;
                }
                String result = TranslateHuzz.translateUzzToRegular(word);
                event.getChannel().sendMessage(word + ": " + result).queue();
            } else if (content.startsWith("!reverseuzz ")) {
                String word = content.substring(12).trim();
                if(HuzzMapOps.getUzzLibrary().containsKey(word)){
                    event.getChannel().sendMessage("Usage: !reverseuzz <definition>").queue();
                    return;
                }
                String result = TranslateHuzz.translateRegularToUzz(word);
                event.getChannel().sendMessage(word + ": " + result).queue();
            } else if (content.startsWith("!transentence ")) {
                String sentence = content.substring(14).trim();
                String result = TranslateHuzz.translateSentence(sentence);
                event.getChannel().sendMessage(result).queue();
            } else if (content.startsWith("!adduzz ")){
                if(!HuzzMapOps.searchBanned(content.substring(8))){
                    event.getChannel().sendMessage("Word has been banned! How un-sigma of you trying to add it!").queue();
                    return;
                }
                String[] command = content.split(" ", 3);
                if (command.length < 3) {
                    event.getChannel().sendMessage("Usage: !adduzz <word> <definition>").queue();
                    return;
                }
                String uzzWord = command[1];
                String def = command[2];
                HuzzMapOps.addUzzWord(uzzWord, def);
                event.getChannel().sendMessage(uzzWord + " has been added to the database and means " + def).queue();
            } else if (content.startsWith("!removeuzz")){
                String[] command = content.split(" ");
                if (command.length < 2) {
                    event.getChannel().sendMessage("Usage: !removeuzz <word>").queue();
                    return;
                }
                String key = command[1];
                HuzzMapOps.removeUzzWord(key);
                event.getChannel().sendMessage(key + " has been removed from the database").queue();
            } else if (content.toLowerCase().startsWith("!banuzz ")) {
                HuzzMapOps.banUzz(content.substring(8));
                event.getChannel().sendMessage(content.substring(8) + " has been banned and cannot be added to uzz translations").queue();
            } else if (content.toLowerCase().startsWith("!unbanuzz ")) {
                if(HuzzMapOps.unbanUzz(content.substring(10)))
                    event.getChannel().sendMessage(content.substring(10) + " has been unbanned.").queue();
                else
                    event.getChannel().sendMessage(content.substring(10) + " wasn't even banned. bozo.").queue();
            } else if (content.toLowerCase().startsWith("hello rotbot") || content.toLowerCase().startsWith("hi rotbot")){
                int n = (int)(Math.random() * 10 + 1);
                if(n < 6)
                    event.getChannel().sendMessage("Hello skibidi rizzler " + event.getAuthor().getAsMention() + "!").queue();
                else
                    event.getChannel().sendMessage("Hello sigma " + event.getAuthor().getAsMention() + "!").queue();
            } else if (content.toLowerCase().startsWith("!helpmerot")){
                EmbedBuilder helper = new EmbedBuilder();
                //String mention = event.getAuthor().getAsMention();
                String name = event.getAuthor().getEffectiveName();
                helper.setTitle("So " + name + ", you wanna learn how to rizz huzz and hawk tuahs? I got you covered!");
                helper.setDescription("");
                helper.addField("**!uzz <word>**", "Translates an \"uzz\" word (huzz, chuzz, bruzz, etc.) into their meaning.", false);
                helper.addField("**!reverseuzz <word(s)**>", "Translates a regular word (bros, hoes) into an \"uzz\" word (huzz, chuzz, bruzz, etc.).", false);
                helper.addField("**!transentence <word(s)>**", "Translates all the \"uzz\" words in a sentence into their definitions.", false);
                helper.addField("**!adduzz <word> <definition>**", "Adds an \"uzz\" word (huzz, chuzz, bruzz, etc.) to the database", false);
                helper.addField("**!removeuzz <word>**", "Removes the \"uzz\" word (huzz, chuzz, bruzz, etc.) from the database", false);
                helper.addField("**!banuzz <word> <definition>**", "Prevents an \"uzz\" word (hitluzz, etc.) from being added to the database", false);
                helper.addField("**!unbanuzz <word>**", "Removes the ban of an \"uzz\" word (huzz, chuzz, bruzz, etc.) from the database", false);
                helper.addField("**!auralevel**", "Shows your individual aura level. Aura is earned 1 point per message sent, and 3 points per RotBot command used.", false);
                helper.addField("**!leaderboard**", "Shows the top 5 users with the highest aura in the server", false);
                helper.setColor(0x32a85c);
                helper.setFooter("Buy The Dev A Coffee!");

                event.getMessage().replyEmbeds(helper.build()).queue();
            } else if (content.toLowerCase().contains("massive") || content.toLowerCase().contains("fade")){
                EmbedBuilder massive = new EmbedBuilder();
                massive.setImage(TenorGifService.searchGif("low taper fade"));
                massive.setFooter("Via Tenor");
                massive.setColor(0x1dc5db);
                event.getMessage().replyEmbeds(massive.build()).queue();
            } else if (content.contains("6") || content.contains("7")){
                EmbedBuilder sixseven = new EmbedBuilder();
                sixseven.setImage(TenorGifService.searchGif("6 7" ));
                sixseven.setFooter("Via Tenor");
                sixseven.setColor(0xffee00);
                event.getMessage().replyEmbeds(sixseven.build()).queue();
            }
        }
    }
}
