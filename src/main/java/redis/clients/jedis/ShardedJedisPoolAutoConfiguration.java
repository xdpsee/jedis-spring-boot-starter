package redis.clients.jedis;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.util.Hashing;

import static java.util.stream.Collectors.toList;

@Configuration
@ConditionalOnClass(ShardedJedisPool.class)
@EnableConfigurationProperties({ShardProperties.class, JedisPoolProperties.class})
public class ShardedJedisPoolAutoConfiguration {

    @Autowired
    private ShardProperties shardProperties;

    @Autowired
    private JedisPoolProperties jedisPoolProperties;

    @Bean
    @ConditionalOnMissingBean
    public ShardedJedisPool shardedJedisPool() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        BeanUtils.copyProperties(jedisPoolProperties, poolConfig);

        try {
            return new ShardedJedisPool(poolConfig
                , convertShard(shardProperties.getShard())
                , StringUtils.isEmpty(shardProperties.getHashingClass())
                ? (Hashing)(ShardProperties.DEFAULT_HASHING_CLAZZ
                .newInstance())
                : (Hashing)Class.forName(shardProperties.getHashingClass()).newInstance()
                , StringUtils.isEmpty(shardProperties.getKeyTagPattern())
                ? null
                : Pattern.compile(shardProperties.getKeyTagPattern()));
        } catch (Exception e) {
            throw new BeanCreationException("ShardedJedisPool create error", e);
        }

    }

    private List<JedisShardInfo> convertShard(List<ShardProperties.ShardInfo> shardInfos) {

        return shardInfos.stream().map(info ->
            new JedisShardInfo(info.getHost()
                , info.getPort()
                , info.getConnectionTimeout()
                , info.getSoTimeout()
                , info.getWeight()
                , info.isSsl()))
            .collect(toList());

    }
}


