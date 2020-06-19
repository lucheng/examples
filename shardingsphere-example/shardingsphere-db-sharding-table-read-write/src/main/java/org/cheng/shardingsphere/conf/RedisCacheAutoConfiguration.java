package org.cheng.shardingsphere.conf;

import java.net.UnknownHostException;
import java.time.Duration;

import org.cheng.shardingsphere.cache.RedisUtils;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.cache.Cache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RedisCacheAutoConfiguration {

	public RedisCacheAutoConfiguration() {
		log.info("init redis cache");
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new ParameterNamesModule());
		om.registerModule(new Jdk8Module());
		om.registerModule(new JavaTimeModule());

		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.setDefaultSerializer(jackson2JsonRedisSerializer);
		return template;
	}

	/**
	 * 配置缓存管理器
	 *
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		// 生成一个默认配置，通过config对象即可对缓存进行自定义配置
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		// 设置缓存的默认过期时间，也是使用Duration设置
		redisCacheConfiguration = redisCacheConfiguration
				// 设置缓存有效期一小时
				.entryTtl(Duration.ofHours(1));
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	public RedisUtils<?> redisUtils(RedisTemplate<String, Object> redisTemplate, Cache<?, ?> cache) {
		RedisUtils<?> redisUtils = new RedisUtils<>(redisTemplate, redisTemplate, cache);
		log.info("RedisUtils [{}]", redisUtils);
		return redisUtils;
	}
}
