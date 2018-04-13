package redis.clients.jedis;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jedis.pool")
public class ShardProperties {
    public static final Class DEFAULT_HASHING_CLAZZ = redis.clients.util.MurmurHash.class;

    private List<ShardInfo> shard = new ArrayList<>();

    private String hashingClass;

    private String keyTagPattern;

    @Data
    public static class ShardInfo {

        private String host;

        private int port;

        private int weight;

        private boolean ssl;

        private int soTimeout;

        private int connectionTimeout;

    }
}

