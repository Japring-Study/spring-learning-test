package cholog.configuration.config;

import cholog.configuration.AuthService;
import cholog.configuration.AuthenticationPrincipalArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
@Configuration
public class AppConfig {

    @Bean
    public AuthService authService() {
        return new AuthService();
    }

    // TODO: AuthenticationPrincipalArgumentResolver를 빈 등록하고 authService에 대한 의존성을 주입하기
    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver(authService());
    }

}
