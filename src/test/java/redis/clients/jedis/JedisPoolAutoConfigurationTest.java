package redis.clients.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JedisPoolAutoConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
public class JedisPoolAutoConfigurationTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedisPool() {

        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set("hi", "hello,world");

            String value = jedis.get("hi");
            assertNotNull(value);
            assertEquals("hello,world", value);

        } finally {
            jedis.close();
        }

    }

}

