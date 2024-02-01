package com.belhard.bookstore.web.controller.cart;

import com.belhard.bookstore.data.dto.order.OrderDto;
import com.belhard.bookstore.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartCommand {
    private final OrderService orderService;
    @GetMapping
    public String execute(Model model) {
        List<OrderDto> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "cart";
    }
}
