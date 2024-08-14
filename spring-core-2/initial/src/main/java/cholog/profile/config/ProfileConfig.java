package cholog.profile.config;

import cholog.profile.InmemoryMessageRepository;
import cholog.profile.JdbcMessageRepository;
import cholog.profile.MessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean("dataSource")
    @Profile("dev")
    public MessageRepository inMemoryMessageRepository() {
        return new InmemoryMessageRepository();
    }

    @Bean("dataSource")
    @Profile("prod")
    public MessageRepository jdbcMessageRepository() {
        return new JdbcMessageRepository();
    }

}
