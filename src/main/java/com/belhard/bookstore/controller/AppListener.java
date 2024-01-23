package com.belhard.bookstore.controller;

import com.belhard.bookstore.AppContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log4j2
@WebListener
public class AppListener implements ServletContextListener {
    private static AnnotationConfigApplicationContext context;

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Context init event");
        context = new AnnotationConfigApplicationContext(AppContext.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Context destroy event");
        if (context != null){
            context.close();
        }
    }
}
