package com.belhard.bookstore.controller.cart;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.service.order.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller("cart")
public class CartCommand implements Command {
    private final OrderService orderService;
    public String execute(HttpServletRequest req) {
        List<OrderDto> orders = orderService.findAll();
        req.setAttribute("orders", orders);
        return "jsp/cart/cart.jsp";
    }
}
