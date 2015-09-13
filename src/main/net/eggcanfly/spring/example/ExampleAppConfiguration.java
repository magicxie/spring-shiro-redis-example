package net.eggcanfly.spring.example;

import net.eggcanfly.spring.redis.support.EnableRedis;
import net.eggcanfly.spring.shiro.support.EnableShiro;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedis
@EnableShiro
@EnableRedisHttpSession
public class ExampleAppConfiguration {

}
