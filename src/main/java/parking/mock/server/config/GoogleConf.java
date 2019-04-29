package parking.mock.server.config;

import feign.Client;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import parking.mock.server.remote.GoogleClient;


@Configuration
@EnableConfigurationProperties(GoogleConf.class)
@ConfigurationProperties(prefix = "google")
public class GoogleConf extends BaseConf<GoogleClient> {
    @Bean
    public GoogleClient getClient() {
        return buildFor(GoogleClient.class);
    }
}
