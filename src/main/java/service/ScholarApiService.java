package service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ScholarApiService {

    // ... (API_KEY and BASE_URL remain the same)
    private static final String API_KEY = "4825f2f5994c363fd811bb8add5d7a1e26413f9e62ee7ceff1b6163241e2ac94";
    private static final String BASE_URL = "https://serpapi.com/search.json";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String searchAuthorByName(String authorName) throws IOException, InterruptedException {
        String encodedQuery = URLEncoder.encode("author:\"" + authorName + "\"", StandardCharsets.UTF_8);
        String url = String.format("%s?engine=google_scholar&q=%s&api_key=%s",
                BASE_URL, encodedQuery, API_KEY);
        return sendRequest(url);
    }

    public String searchAuthorById(String authorId) throws IOException, InterruptedException {
        String url = String.format("%s?engine=google_scholar_author&author_id=%s&api_key=%s",
                BASE_URL, authorId, API_KEY);
        return sendRequest(url);
    }

    /**
     * NEW METHOD: Searches for articles related to a general topic.
     * @param topic The topic to search for (e.g., "machine learning").
     * @return The raw JSON string with a list of articles.
     * @throws IOException if a network error occurs.
     * @throws InterruptedException if the operation is interrupted.
     */
    public String searchByTopic(String topic) throws IOException, InterruptedException {
        String encodedQuery = URLEncoder.encode(topic, StandardCharsets.UTF_8);
        String url = String.format("%s?engine=google_scholar&q=%s&api_key=%s",
                BASE_URL, encodedQuery, API_KEY);
        return sendRequest(url);
    }

    private String sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new IOException("API request failed with status code: " + response.statusCode() +
                    " and message: " + response.body());
        }
    }
}