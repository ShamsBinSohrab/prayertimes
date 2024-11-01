package org.muslim.providers;

import org.muslim.models.Request;
import org.muslim.models.Response;

public interface PrayerTimeProvider {

    Response getPrayerTime(Request request);
}
