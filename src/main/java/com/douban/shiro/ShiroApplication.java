package com.douban.shiro;

import com.douban.shiro.bind.CurrentUserResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
public class ShiroApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(new CurrentUserResolver());
	}

}
