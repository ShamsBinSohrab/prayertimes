package org.muslim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.muslim.models.Request;
import org.muslim.models.Response;

import java.time.LocalDate;

public class PrayerTimesRequestHandlerTest {

    private final PrayerTimesRequestHandler requestHandler = new PrayerTimesRequestHandler();

    @Test
    public void handleRequest_shouldReturnConstantValue() {
        Response response = requestHandler.handleRequest(new Request("Dhaka", LocalDate.MIN), null);
        assertEquals(200, response.code());
    }
}
