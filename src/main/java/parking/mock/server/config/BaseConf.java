package parking.mock.server.config;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import parking.mock.server.logger.FeignLogger;

@Setter
@Getter
public class BaseConf<T> {
    private String host;
    @Autowired
    private Client sslClient;

    T buildFor(Class<T> client) {
        return Feign.builder()
                .logLevel(Logger.Level.FULL)
                .logger(new FeignLogger())
                .client(sslClient)
                .decoder(new JacksonDecoder())
                .target(client, host);
    }
}
