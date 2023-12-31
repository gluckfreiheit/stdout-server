package site.stdout.stdout.rss.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

	private final LettuceConnectionFactory lettuceConnectionFactory;

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		RedisSerializer stringRedisSerializer = new StringRedisSerializer();
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setValueSerializer(stringRedisSerializer);

		return redisTemplate;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		cacheConfiguration.entryTtl(Duration.ofDays(1L));
		return RedisCacheManager.builder(lettuceConnectionFactory)
				.cacheDefaults(cacheConfiguration)
				.build();
	}
}
