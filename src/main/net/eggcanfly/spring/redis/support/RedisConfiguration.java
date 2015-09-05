package net.eggcanfly.spring.redis.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@PropertySource(value="classpath:redis.properties", ignoreResourceNotFound=true)
@Configuration
public class RedisConfiguration {

	protected int port;
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		
		factory.setUsePool(false);
		
		return factory;
	}
	
	@Bean
	public RedisTemplate redisTemplate(){
		
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(jedisConnectionFactory());
		
		return template;
	}
	
}
