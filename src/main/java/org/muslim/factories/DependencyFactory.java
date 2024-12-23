
package org.muslim.factories;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.muslim.PrayerTimesRequestHandler;
import org.muslim.providers.AlAdhanPrayerTimeProvider;
import org.muslim.providers.PrayerTimeProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.protocols.jsoncore.JsonNodeParser;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.config.ConfigClient;

/**
 * The module containing all dependencies required by the {@link PrayerTimesRequestHandler}.
 */
public class DependencyFactory {

    private static final HttpClient HTTP_CLIENT = HttpClients.createMinimal();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private DependencyFactory() {
    }

    /**
     * @return an instance of ConfigClient
     */
    public static ConfigClient configClient() {
        return ConfigClient.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
                .httpClientBuilder(UrlConnectionHttpClient.builder())
                .build();
    }

    public static PrayerTimeProvider alAdhanPrayerTimeProvider() {
        return new AlAdhanPrayerTimeProvider(HTTP_CLIENT, OBJECT_MAPPER);
    }
}
