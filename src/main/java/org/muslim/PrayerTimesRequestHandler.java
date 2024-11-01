package org.muslim;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.http.client.HttpClient;
import org.muslim.factories.DependencyFactory;
import org.muslim.models.Request;
import org.muslim.models.Response;
import org.muslim.providers.PrayerTimeProvider;
import software.amazon.awssdk.services.config.ConfigClient;

/**
 * Lambda function entry point. You can change to use other pojo type or implement
 * a different RequestHandler.
 *
 * @see <a href=https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html>Lambda Java Handler</a> for more information
 */
public class PrayerTimesRequestHandler implements RequestHandler<Request, Response> {

    private final PrayerTimeProvider prayerTimeProvider;

    public PrayerTimesRequestHandler() {
        this.prayerTimeProvider = DependencyFactory.alAdhanPrayerTimeProvider();
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        return prayerTimeProvider.getPrayerTime(request);
    }
}
