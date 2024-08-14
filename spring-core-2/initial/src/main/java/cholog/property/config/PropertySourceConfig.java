package cholog.property.config;

import cholog.property.GoogleDriveRestClient;
import cholog.property.GoogleMapsRestClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    // TODO: ext-api.properties의 google.api.endpoint 값을 어노테이션을 사용해서 가져오기
    // TODO: 위 endpoint 값을 사용하여 GoogleMapsRestClient를 빈으로 등록하기
    public GoogleDriveRestClient googleDriveRestClient() {
        return new GoogleDriveRestClient("");
    }
}
