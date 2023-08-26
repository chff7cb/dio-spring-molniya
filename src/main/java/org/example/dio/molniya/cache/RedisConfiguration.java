package org.example.dio.molniya.cache;

import org.example.dio.molniya.domain.ConsultaCEP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Configuration
public class RedisConfiguration {
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, ConsultaCEP> cacheTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ConsultaCEP> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public ValueOperations<String, ConsultaCEP> cacheOps(RedisTemplate<String, ConsultaCEP> template) {
        return template.opsForValue();
    }
}
