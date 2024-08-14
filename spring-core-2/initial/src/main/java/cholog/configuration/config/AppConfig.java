package cholog.configuration.config;

import cholog.configuration.AuthService;
import cholog.configuration.AuthenticationPrincipalArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cholog.scan")
public class AppConfig {

    @Bean
    public AuthService authService() {
        // 실제 구현체가 없으면 빈이 등록되지 않는다.
        return new AuthService();
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver(AuthService authService) {
        return new AuthenticationPrincipalArgumentResolver(authService);
    }

}
