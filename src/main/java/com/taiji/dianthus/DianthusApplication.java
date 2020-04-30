package com.taiji.dianthus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@ComponentScan("com.taiji.dianthus.*")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class DianthusApplication implements WebMvcConfigurer  {



//    @Bean
//    public static SpringUtil getSpringUtil() {
//        return new SpringUtil();
//    }

    public static void main(String[] args) {
        SpringApplication.run(DianthusApplication.class, args);

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
      //  registry.addViewController("/index").setViewName("index");
    }
}
