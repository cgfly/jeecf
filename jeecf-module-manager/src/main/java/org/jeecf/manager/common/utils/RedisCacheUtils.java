package org.jeecf.manager.common.utils;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.manager.config.RedisObjectSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;

/**
 * redis 缓存工具
 * 
 * @author jianyiming
 *
 */
public class RedisCacheUtils {

    public static final String[] CACHE_TYPE = {"SYS"};

    private static final int DEFAULT_TIMEOUT = 24 * 60 * 60;

    private static final StringRedisSerializer STRING_REDIS_SERIALIZER = new StringRedisSerializer();

    private static final RedisObjectSerializer REDIS_OBJECT_SERIALIZER = new RedisObjectSerializer();

    public static void setSysCache(String key, String value) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.setex(STRING_REDIS_SERIALIZER.serialize(CACHE_TYPE[0] + SplitCharEnum.UNDERLINE.getName() + key), DEFAULT_TIMEOUT, STRING_REDIS_SERIALIZER.serialize(value));
        jedis.close();
    }

    public static String getSysCache(String key) {
        String result = null;
        Jedis jedis = RedisUtils.getJedis();
        byte[] bytes = jedis.get(STRING_REDIS_SERIALIZER.serialize(CACHE_TYPE[0] + SplitCharEnum.UNDERLINE.getName() + key));
        if (bytes != null) {
            result = STRING_REDIS_SERIALIZER.deserialize(bytes);
            jedis.expire(STRING_REDIS_SERIALIZER.serialize(CACHE_TYPE[0] + SplitCharEnum.UNDERLINE.getName() + key), DEFAULT_TIMEOUT);
        }
        jedis.close();
        return result;
    }

    public static void setCache(String key, Object value) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.set(STRING_REDIS_SERIALIZER.serialize(key), REDIS_OBJECT_SERIALIZER.serialize(value));
        jedis.close();
    }

    public static void setCache(String key, Object value, int timeout) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.setex(STRING_REDIS_SERIALIZER.serialize(key), timeout, REDIS_OBJECT_SERIALIZER.serialize(value));
        jedis.close();
    }

    public static Object getCache(String key) {
        Jedis jedis = RedisUtils.getJedis();
        byte[] bytes = jedis.get(STRING_REDIS_SERIALIZER.serialize(key));
        jedis.close();
        if (bytes != null) {
            return REDIS_OBJECT_SERIALIZER.deserialize(bytes);
        }
        return null;
    }

    public static void delCache(String key) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.del(STRING_REDIS_SERIALIZER.serialize(key));
        jedis.close();
    }

}
