package com.belhard.bookstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Log4j2
@WebServlet("/bookstore")
public class FrontController extends HttpServlet {
    public static final String PATH = "bookstore?command=";
    public static final String REDIRECT = "redirect:";

    @Override
    public void init() {
        log.info("Init called");
        log.info("Init completed");
    }

    @Override
    public void destroy() {
        log.info("Destroy called");
        log.info("Destroy completed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        String page;
        try {
            String command = req.getParameter("command");
            Command controller = AppListener.getContext().getBean(command, Command.class);
            page = controller.execute(req);

            if (page.startsWith(REDIRECT)) {
                resp.sendRedirect(req.getContextPath() + "/" + page.substring(REDIRECT.length()));
            } else {
                req.getRequestDispatcher(page).forward(req, resp);
            }
        } catch (IOException | ServletException e) {
            log.error(e.getMessage(), e);
            Command command = AppListener.getContext().getBean("error", Command.class);
            command.execute(req);
        }
    }
}
