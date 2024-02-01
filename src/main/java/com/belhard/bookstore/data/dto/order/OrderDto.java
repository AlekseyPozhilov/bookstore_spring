package com.belhard.bookstore.data.dto.order;

import com.belhard.bookstore.data.entity.OrderInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private BigDecimal totalCost;
    private Status status;
    private List<OrderInfo> items;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED;
    }
}
