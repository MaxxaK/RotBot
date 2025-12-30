package com.example;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TenorGifService {

    private static final String API_KEY = "<your_api_key_here>"; //replace with your api key
    

    public static String searchGif(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        OkHttpClient client = new OkHttpClient();
        final String url = String.format("", encodedQuery, API_KEY, 1);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                JsonArray results = jsonObject.getAsJsonArray("results");

                if (results.size() > 0) {
                    JsonObject firstResult = results.get(0).getAsJsonObject();
                    JsonObject mediaFormats = firstResult.getAsJsonObject("media_formats");  // Corrected key
                    if (mediaFormats.has("gif")) {
                        System.out.println(jsonResponse); 
                        return mediaFormats.getAsJsonObject("gif").get("url").getAsString();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No GIF found!";
    }
}
