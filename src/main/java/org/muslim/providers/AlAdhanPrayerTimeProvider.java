package org.muslim.providers;

import org.apache.http.client.HttpClient;
import org.muslim.models.Request;
import org.muslim.models.Response;

public class AlAdhanPrayerTimeProvider implements PrayerTimeProvider {

    private final HttpClient httpClient;

    public AlAdhanPrayerTimeProvider(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Response getPrayerTime(Request request) {
        return new Response(200);
    }
}
