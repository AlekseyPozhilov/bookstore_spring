package com.belhard.bookstore.data.dto.order;

import com.belhard.bookstore.data.entity.Book;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderInfoDto {
    private Long orderId;
    private Book book;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
}
