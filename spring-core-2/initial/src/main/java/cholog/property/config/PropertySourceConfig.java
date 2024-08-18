package cholog.property.config;

import cholog.property.GoogleDriveRestClient;
import cholog.property.GoogleMapsRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기 => Configuration
// TODO: ext-api.properties 파일을 활용하기 위한 설정 추가하기 =>
@Configuration
@PropertySource("classpath:ext-api.properties")
public class PropertySourceConfig {

    private final Environment env;

    public PropertySourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public GoogleMapsRestClient googleMapsRestClient() {
        return new GoogleMapsRestClient(env.getProperty("google.api.endpoint"));
    }

    @Bean
    public GoogleDriveRestClient googleDriveRestClient(@Value("${google.api.endpoint}") String endpoint) {
        return new GoogleDriveRestClient(endpoint);
    }
}
