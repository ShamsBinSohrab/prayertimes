package org.muslim.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.muslim.models.Request;
import org.muslim.models.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * API Docs: https://aladhan.com/prayer-times-api
 */
public class AlAdhanPrayerTimeProvider implements PrayerTimeProvider {

    private static final HttpHost HOST = HttpHost.create("https://api.aladhan.com");

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AlAdhanPrayerTimeProvider(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public Response getPrayerTime(Request request) {
        try {
            HttpEntity httpEntity = httpClient.execute(HOST, new HttpGet(buildUri(request))).getEntity();
            return objectMapper.readValue(httpEntity.getContent(), Response.class);
        } catch (URISyntaxException | IllegalArgumentException | IOException ex) {
            return new Response(500, ex.getMessage());
        }
    }

    private URI buildUri(Request request) throws URISyntaxException {
        URIBuilder builder = new URIBuilder()
                .setPathSegments("v1", "timingsByCity", request.date().toString())
                .setParameter("city", request.city())
                .setParameter("country", request.country());
        if (request.method() != null) {
            builder.setParameter("method", request.method());
        }
        if (request.adjustment() != null) {
            builder.setParameter("adjustment", request.adjustment());
        }
        return builder.build();
    }
}
