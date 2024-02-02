package com.belhard.bookstore;

import com.belhard.bookstore.web.interceptor.MyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RequiredArgsConstructor
@SpringBootApplication
public class App implements WebMvcConfigurer {
    private final MyInterceptor myInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**");
    }
}
