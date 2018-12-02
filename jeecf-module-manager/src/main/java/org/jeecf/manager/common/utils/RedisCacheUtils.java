package org.jeecf.manager.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.jeecf.manager.config.RedisObjectSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;
/**
 * redis 缓存工具
 * @author jianyiming
 *
 */
public class RedisCacheUtils {

	public static final String[] CACHE_TYPE = { "SYS" };
    
	private static StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

	private static RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();

	public static void setSysCache(String key, String value) {
		Jedis jedis = RedisUtils.getJedis();
		Map<byte[], byte[]> hash = new HashMap<>(10);
		hash.put(stringRedisSerializer.serialize(key), stringRedisSerializer.serialize(value));
		jedis.hmset(stringRedisSerializer.serialize(CACHE_TYPE[0]), hash);
		jedis.close();
	}

	public static String getSysCache(String field) {
		Jedis jedis = RedisUtils.getJedis();
		byte[] result = jedis.hget(stringRedisSerializer.serialize(CACHE_TYPE[0]),
				stringRedisSerializer.serialize(field));
		jedis.close();
		if (result != null) {
			return stringRedisSerializer.deserialize(result);
		}
		return null;
	}

	public static void setCache(String key, Object value) {
		Jedis jedis = RedisUtils.getJedis();
		jedis.set(stringRedisSerializer.serialize(key), redisObjectSerializer.serialize(value));
		jedis.close();
	}

	public static void setNx(String key, Object value, long timeout) {
		Jedis jedis = RedisUtils.getJedis();
		jedis.set(stringRedisSerializer.serialize(key), redisObjectSerializer.serialize(value),
				stringRedisSerializer.serialize("NX"), redisObjectSerializer.serialize("EX"), timeout);
		jedis.close();
	}

	public static void setNxIfAbsent(String key, Object value, long timeout) {
		Jedis jedis = RedisUtils.getJedis();
		byte[] keyByte = stringRedisSerializer.serialize(key);
		if (keyByte != null && !jedis.exists(keyByte)) {
			jedis.set(keyByte, redisObjectSerializer.serialize(value),
					stringRedisSerializer.serialize("NX"), redisObjectSerializer.serialize("EX"), timeout);
		}
		jedis.close();
	}

	public static Object getCache(String key) {
		Jedis jedis = RedisUtils.getJedis();
		byte[] bytes = jedis.get(stringRedisSerializer.serialize(key));
		jedis.close();
		if (bytes != null) {
			return redisObjectSerializer.deserialize(bytes);
		}
		return null;
	}

	public static void delCache(String key) {
		Jedis jedis = RedisUtils.getJedis();
		jedis.del(stringRedisSerializer.serialize(key));
		jedis.close();
	}

}
