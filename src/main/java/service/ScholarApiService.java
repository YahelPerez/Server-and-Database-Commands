package service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * Service class responsible for all communication with the SerpApi Google Scholar API.
 * This implementation uses Java 11's built-in HttpClient for making network requests.
 */
public class ScholarApiService {

    /**
     * IMPORTANT: This is a placeholder for your personal SerpApi key.
     */
    private static final String API_KEY = "4825f2f5994c363fd811bb8add5d7a1e26413f9e62ee7ceff1b6163241e2ac94";

    /**
     * The base URL for all SerpApi search requests.
     */
    private static final String BASE_URL = "https://serpapi.com/search.json";

    /**
     * A single, reusable HttpClient instance. It's more efficient to create it
     * once and reuse it for multiple requests.
     */
    private final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Searches for academic articles by a specific author using the SerpApi Google Scholar engine.
     *
     * @param authorName The name of the author to search for (e.g., "Geoffrey Hinton").
     * @return The raw JSON string response from the API upon a successful request.
     * @throws IOException if a network error occurs or if the API returns a non-successful status code.
     * @throws InterruptedException if the sending thread is interrupted.
     */
    public String searchAuthor(String authorName) throws IOException, InterruptedException {
        // Build the specific query string for Google Scholar to search by author.
        // We encode it to ensure special characters and spaces are handled correctly in the URL.
        String encodedQuery = URLEncoder.encode("author:\"" + authorName + "\"", StandardCharsets.UTF_8);

        // Construct the final, complete URL for the API request.
        String url = String.format("%s?engine=google_scholar&q=%s&api_key=%s",
                BASE_URL,
                encodedQuery,
                API_KEY);

        // Build the HTTP GET request using Java's HttpRequest builder.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)) // Set the URL for the request.
                .GET()               // Specify that this is a GET request.
                .build();            // Create the immutable request object.

        // Send the request synchronously and receive the response.
        // The response body will be handled as a String.
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the HTTP status code indicates success (typically 200 OK).
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            // If the request was successful, return the JSON payload from the response body.
            return response.body();
        } else {
            // If the request failed, throw an exception with the status code and error message from the body.
            throw new IOException("API request failed with status code: " + response.statusCode() +
                    " and message: " + response.body());
        }
    }
}