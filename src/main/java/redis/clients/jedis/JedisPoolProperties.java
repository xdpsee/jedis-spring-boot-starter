package redis.clients.jedis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jedis.pool")
public class JedisPoolProperties {

    private int maxIdle = 8;

    private int minIdle = 0;

    private int maxTotal = 8;

    private long maxWaitMillis = -1L;

    private boolean lifo = true;

    private boolean fairness = false;

    private long minEvictableIdleTimeMillis = 1800000L;

    private long evictorShutdownTimeoutMillis = 10000L;

    private long softMinEvictableIdleTimeMillis = -1L;

    private int numTestsPerEvictionRun = 3;

    private String evictionPolicyClassName = "org.apache.commons.pool2.impl.DefaultEvictionPolicy";

    private boolean testOnCreate = false;

    private boolean testOnBorrow = false;

    private boolean testOnReturn = false;

    private boolean testWhileIdle = false;

    private long timeBetweenEvictionRunsMillis = -1L;

    private boolean blockWhenExhausted = true;

    private boolean jmxEnabled = true;

    private String jmxNamePrefix = "pool";

    private String jmxNameBase;

}
