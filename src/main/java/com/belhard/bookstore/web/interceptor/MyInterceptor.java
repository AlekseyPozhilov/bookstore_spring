package com.belhard.bookstore.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Log4j2
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull Object handler) {
        log.info("Interceptor-PRE: {}", req.getRequestURI());
        log.info("method: {}", req.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull Object handler, ModelAndView modelAndView) {
        log.info("Interceptor-POST: {}", req.getRequestURI());
        log.info("method: {}", req.getMethod());
    }

    @Override
    public void afterCompletion(HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull Object handler, Exception exception) {
        log.info("Interceptor-AFTER: {}", req.getRequestURI());
        log.info("method: {}", req.getMethod());
    }
}
