package org.muslim;

import org.junit.jupiter.api.Test;
import org.muslim.models.Request;
import org.muslim.models.Response;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PrayerTimesRequestHandlerTest {

    private final PrayerTimesRequestHandler requestHandler = new PrayerTimesRequestHandler();

    @Test
    public void handleRequest() {
        Response response = requestHandler.handleRequest(new Request("Dhaka", "Bangladesh", LocalDate.now()), null);

        assertEquals(200, response.code());
        assertEquals("OK", response.status());
        assertNotNull(response.data());
        assertNotNull(response.data().timings());
        assertNotNull(response.data().timings().fajr());
        assertNotNull(response.data().timings().dhuhr());
        assertNotNull(response.data().timings().asr());
        assertNotNull(response.data().timings().maghrib());
        assertNotNull(response.data().timings().isha());
    }
}
