
package com.hlsofttech.redis;

import com.bjucloud.redis.client.RedisConnectionFactory;
import com.bjucloud.redis.client.RedisTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @Description: bjuCloud Redis工具类设置注入
 * @author: yuwenshuo
 * @since: 2019/4/29 8:48
 */


@Configuration
public class BjucloudRedisConfig {

    @Value("${spring.redis.zkHost}")
    public String zkHost;

    @Value("${spring.redis.redisGroupName}")
    private String redisGroupName;

    @Value("${spring.redis.pool.maxTotal}")
    private int maxTotal;

    @Value("${spring.redis.pool.maxIdle}")
    private int maxIdle;

    @Value("${spring.redis.pool.maxWaitMillis}")
    private long maxWaitMillis;

    @Value("${spring.redis.pool.testOnBorrow}")
    private boolean testOnBorrow;


    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);

        RedisConnectionFactory redisConnectionFactory = new RedisConnectionFactory();
        redisConnectionFactory.setZkHost(zkHost);
        redisConnectionFactory.setRedisGroupName(redisGroupName);
        redisConnectionFactory.setPoolConfig(jedisPoolConfig);

        redisTemplate.setRedisConnectionFactory(redisConnectionFactory);
        try {
            redisTemplate.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redisTemplate;
    }

}

