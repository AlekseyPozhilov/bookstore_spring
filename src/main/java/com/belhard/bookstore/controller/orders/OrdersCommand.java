package com.belhard.bookstore.controller.orders;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.service.order.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller("orders")
public class OrdersCommand implements Command {
    private final OrderService orderService;

    @Override
    public String execute(HttpServletRequest req) {
        List<OrderDto> orders = orderService.findAll();
        req.setAttribute("orders", orders);

        return "jsp/orders/orders.jsp";
    }
}
