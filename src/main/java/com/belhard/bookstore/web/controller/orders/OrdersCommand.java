package com.belhard.bookstore.web.controller.orders;

import com.belhard.bookstore.data.dto.order.OrderDto;
import com.belhard.bookstore.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrdersCommand {
    private final OrderService orderService;

    @GetMapping("/getAll")
    public String getOrder(Model model) {
        List<OrderDto> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "orders";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrderForm(@PathVariable("id") Long id) {
        orderService.delete(id);
        return "redirect:/orders/getAll";
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") Long id, Model model) {
        OrderDto order = orderService.findById(id);
        if (order == null) {
            throw new RuntimeException("No order with id = " + id);
        }
        model.addAttribute("order", order);
        return "order";
    }
}
