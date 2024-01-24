package com.belhard.bookstore.dataMapper;

import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.book.CreateBookDto;
import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.dto.order.OrderInfoDto;
import com.belhard.bookstore.dto.user.CreateUserDto;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.entity.Order;
import com.belhard.bookstore.entity.OrderInfo;
import com.belhard.bookstore.entity.User;

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
