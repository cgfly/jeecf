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

	public static final String[] CACHE_TYPE = { "SYS" };

	private static int DEFAULT_TIMEOUT = 24 * 60 * 60;

	private static StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

	private static RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();

	public static void setSysCache(String key, String value) {
		Jedis jedis = RedisUtils.getJedis();
		jedis.setex(stringRedisSerializer.serialize(CACHE_TYPE[0] + SplitCharEnum.UNDERLINE.getName() + key),
				DEFAULT_TIMEOUT, stringRedisSerializer.serialize(value));
		jedis.close();
	}

	public static String getSysCache(String key) {
		String result = null;
		Jedis jedis = RedisUtils.getJedis();
		byte[] bytes = jedis
				.get(stringRedisSerializer.serialize(CACHE_TYPE[0] + SplitCharEnum.UNDERLINE.getName() + key));
		if (bytes != null) {
			result = stringRedisSerializer.deserialize(bytes);
			jedis.expire(stringRedisSerializer.serialize(CACHE_TYPE[0] + SplitCharEnum.UNDERLINE.getName() + key),
					DEFAULT_TIMEOUT);
		}
		jedis.close();
		return result;
	}

	public static void setCache(String key, Object value) {
		Jedis jedis = RedisUtils.getJedis();
		jedis.set(stringRedisSerializer.serialize(key), redisObjectSerializer.serialize(value));
		jedis.close();
	}

	public static void setCache(String key, Object value, int timeout) {
		Jedis jedis = RedisUtils.getJedis();
		jedis.setex(stringRedisSerializer.serialize(key), timeout, redisObjectSerializer.serialize(value));
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
