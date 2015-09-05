package net.eggcanfly.spring.example;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import net.eggcanfly.spring.redis.support.EnableRedis;
import net.eggcanfly.spring.shiro.support.EnableShiro;

@EnableRedis
@EnableShiro
@EnableRedisHttpSession
public class ExampleAppConfiguration {

}
