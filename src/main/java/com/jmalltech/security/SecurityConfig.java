package com.jmalltech.security;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtFilterRegistration() {
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtTokenFilter(redissonClient));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
