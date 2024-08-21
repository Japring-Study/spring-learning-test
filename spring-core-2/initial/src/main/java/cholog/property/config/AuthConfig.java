package cholog.property.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import cholog.property.JwtTokenKeyProvider;

@Configuration
public class AuthConfig {
	@Value("${security.jwt.token.secret-key")
	String secretKey;

	public JwtTokenKeyProvider jwtTokenKeyProvider() {
		return new JwtTokenKeyProvider(secretKey);
	}
}
