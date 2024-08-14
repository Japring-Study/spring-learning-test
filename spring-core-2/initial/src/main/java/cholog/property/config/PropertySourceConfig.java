package cholog.property.config;

import cholog.property.GoogleDriveRestClient;
import cholog.property.GoogleMapsRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:ext-api.properties")
public class PropertySourceConfig {

    private final Environment env;

    public PropertySourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public GoogleMapsRestClient googleMapsRestClient() {
        String endpoint = env.getProperty("google.api.endpoint");
        return new GoogleMapsRestClient(endpoint);
    }

    @Value("${google.api.endpoint}")
    private String googleApiEndpoint;

    @Bean
    public GoogleDriveRestClient googleDriveRestClient() {
        return new GoogleDriveRestClient(googleApiEndpoint);
    }
}
