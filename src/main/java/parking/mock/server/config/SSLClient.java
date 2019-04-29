package parking.mock.server.config;

import feign.Client;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSLClient {
    @Bean
    public Client getSSLClient() {
        return new Client.Default(null, null);
    }
}
