package com.belhard.bookstore.controller.cart;

import com.belhard.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller("cart")
public class CartCommand implements Command {
    public String execute(HttpServletRequest req) {
        return "jsp/cart/cart.jsp";
    }
}
