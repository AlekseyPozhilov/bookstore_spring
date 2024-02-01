package com.belhard.bookstore.data.dataMapper;

import com.belhard.bookstore.data.dto.book.BookDto;
import com.belhard.bookstore.data.dto.order.OrderDto;
import com.belhard.bookstore.data.dto.order.OrderInfoDto;
import com.belhard.bookstore.data.dto.user.UserDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderInfo;
import com.belhard.bookstore.data.entity.User;

public interface DataMapper {
    BookDto bookReadDto(Book bookEntity);
    Book toEntity(BookDto dto);
    UserDto userReadDto(User userEntity);
    User toEntity(UserDto dto);
    OrderDto orderReadDto(Order orderEntity);
    Order toEntity(OrderDto dto);
    OrderInfoDto orderInfoReadDto(OrderInfo orderInfoEntity);
    OrderInfo toEntity(OrderInfoDto dto);
}
