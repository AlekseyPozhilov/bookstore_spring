package com.belhard.bookstore.dto.order;

import com.belhard.bookstore.entity.Book;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfoDto {
    private Long id;
    private Long orderId;
    private Book book;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
}
