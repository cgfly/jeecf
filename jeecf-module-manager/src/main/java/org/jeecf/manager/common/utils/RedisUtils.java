package org.jeecf.manager.common.utils;

import org.jeecf.manager.common.properties.RedisProperties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接池
 * 
 * @author jianyiming
 *
 */
public class RedisUtils {

    private final static JedisPool JEDIS_POOL = initJedisPool();

    /**
     * 初始化redis 连接池
     * 
     * @return
     */
    private synchronized static JedisPool initJedisPool() {
        if (JEDIS_POOL == null) {
            RedisProperties redisProperties = SpringContextUtils.getBean("redisProperties", RedisProperties.class);
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置最大连接数
            config.setMaxTotal(redisProperties.getMaxTotal());
            // 设置最大空闲连接数
            config.setMaxIdle(redisProperties.getMaxIdle());
            // 等待可用连接的最大时间
            config.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
            config.setTestWhileIdle(redisProperties.getTestWhileIdle());
            config.setTimeBetweenEvictionRunsMillis(redisProperties.getTimeBetweenEvictionRunsMillis());
            config.setMinEvictableIdleTimeMillis(redisProperties.getMinEvictableIdleTimeMillis());
            return new JedisPool(config, redisProperties.getHost(), redisProperties.getPort());
        }
        return JEDIS_POOL;
    }

    /**
     * 
     * 获取Jedis实例       每次用完要将连接返回给连接池 jedis.close();      
     * 
     */
    public static Jedis getJedis() {
        if (JEDIS_POOL != null) {
            Jedis resource = JEDIS_POOL.getResource();
            return resource;
        }
        return null;
    }
}
