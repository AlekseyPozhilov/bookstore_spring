package com.belhard.bookstore.dto.order;

import com.belhard.bookstore.entity.Book;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfoDto {
    private Long id;
    private Long orderId;
    private Long bookId;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
}
