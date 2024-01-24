package com.belhard.bookstore.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private BigDecimal totalCost;
    private Status status;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED;
    }
}
