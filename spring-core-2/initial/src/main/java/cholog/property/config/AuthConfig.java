package cholog.property.config;

import cholog.property.JwtTokenKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthConfig {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Bean
    public JwtTokenKeyProvider jwtTokenKeyProvider() {
        return new JwtTokenKeyProvider(secretKey);
    }
}
