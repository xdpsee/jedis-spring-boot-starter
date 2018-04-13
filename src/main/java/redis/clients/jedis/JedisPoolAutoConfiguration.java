package redis.clients.jedis;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(JedisPool.class)
@EnableConfigurationProperties({JedisRedisProperties.class, JedisPoolProperties.class})
public class JedisPoolAutoConfiguration {

    @Autowired
    private JedisRedisProperties jedisRedisProperties;

    @Autowired
    private JedisPoolProperties jedisPoolProperties;

    @Bean
    @ConditionalOnMissingBean
    public JedisPool jedisPool() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        BeanUtils.copyProperties(jedisPoolProperties, poolConfig);

        return new JedisPool(poolConfig
            , jedisRedisProperties.getHost()
            , jedisRedisProperties.getPort()
            , jedisRedisProperties.getTimeout()
            , jedisRedisProperties.getPassword()
            , jedisRedisProperties.getDatabase()
            , jedisRedisProperties.getClientName()
            , jedisRedisProperties.isSsl());
    }

}


