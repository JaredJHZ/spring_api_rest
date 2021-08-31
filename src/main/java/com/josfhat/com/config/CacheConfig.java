package com.josfhat.com.config;

import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {
//		@Bean
//		public CacheManager getManager() {
//			return new ConcurrentMapCacheManager("users");
//		}
		
		@Bean
		public CacheManager
		cacheManager(RedissonClient redissonClient)
		{
		Map<String, CacheConfig>config=new HashMap<>();
		config.put("testMap",new CacheConfig());
		return new RedissonSpringCacheManager(redissonClient);
		}

		
		@Bean(destroyMethod ="shutdown")
		public RedissonClient redisson() {
			try {
				Config config= new Config();
				config.useSingleServer().setAddress("redis://127.0.0.1:6379");
				return Redisson.create(config);
			}catch(Exception ex) {
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println("xd");
				System.out.println(ex.getMessage());
				return null;
			}

		}
		
		

}
