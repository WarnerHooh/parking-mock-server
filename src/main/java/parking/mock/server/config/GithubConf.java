package parking.mock.server.config;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import parking.mock.server.logger.FeignLogger;
import parking.mock.server.remote.GithubClient;


@Configuration
@EnableConfigurationProperties(GithubConf.class)
@ConfigurationProperties(prefix = "github")
public class GithubConf {
    private String host;

    @Bean
    public GithubClient getGithubClient() {
        return Feign.builder()
                .logLevel(Logger.Level.FULL)
                .logger(new FeignLogger())
                .decoder(new JacksonDecoder())
                .target(GithubClient.class, host);
    }

    public GithubConf setHost(String host) {
        this.host = host;
        return this;
    }
}
