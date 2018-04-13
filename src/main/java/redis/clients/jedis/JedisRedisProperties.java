package redis.clients.jedis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jedis.redis")
public class JedisRedisProperties {

    private String host = "127.0.0.1";

    private int port = 6379;

    private int timeout = 2000;

    private String password;

    private int database = 0;

    private String clientName;

    private boolean ssl;

}
