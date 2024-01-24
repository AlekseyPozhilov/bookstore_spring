package com.belhard.bookstore.dataMapper;

import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.dto.order.OrderInfoDto;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.entity.Order;
import com.belhard.bookstore.entity.OrderInfo;
import com.belhard.bookstore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DataMapperImpl implements DataMapper{
    @Override
    public BookDto bookReadDto(Book bookEntity) {
        BookDto dto = new BookDto();
        dto.setId(bookEntity.getId());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setNumberOfPages(bookEntity.getNumberOfPages());
        dto.setPrice(bookEntity.getPrice());
        dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
        dto.setTitle(bookEntity.getTitle());

        return dto;
    }
    @Override
    public Book toEntity(BookDto dto) {
        Book bookEntity = new Book();
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setIsbn(dto.getIsbn());
        bookEntity.setNumberOfPages(dto.getNumberOfPages());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
        bookEntity.setTitle(dto.getTitle());
        return bookEntity;
    }
    @Override
    public UserDto userReadDto(User userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setEmail(userEntity.getEmail());
        dto.setDateOfBirth(userEntity.getDateOfBirth());
        dto.setGender(userEntity.getGender());
        dto.setPhoneNumber(userEntity.getPhoneNumber());
        dto.setPassword(userEntity.getPassword());
        return dto;
    }
    @Override
    public User toEntity(UserDto dto) {
        User userEntity = new User();
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setDateOfBirth(dto.getDateOfBirth());
        userEntity.setGender(dto.getGender());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setPassword(dto.getPassword());
        return userEntity;
    }

    @Override
    public OrderDto orderReadDto(Order orderEntity) {
        OrderDto dto = new OrderDto();
        dto.setId(orderEntity.getId());
        dto.setUserId(orderEntity.getUser().getId());
        dto.setTotalCost(orderEntity.getTotalCost());
        Order.Status status = orderEntity.getStatus();
        if (status != null) {
            dto.setStatus(OrderDto.Status.valueOf(status.toString()));
        }
        return dto;
    }

    @Override
    public Order toEntity(OrderDto dto) {
        Order orderEntity = new Order();
        orderEntity.setId(dto.getId());
        orderEntity.setTotalCost(dto.getTotalCost());
        OrderDto.Status status = dto.getStatus();
        if (status != null){
            orderEntity.setStatus(Order.Status.valueOf(status.toString()));
        }
        return orderEntity;
    }

    @Override
    public OrderInfoDto orderInfoReadDto(OrderInfo orderInfoEntity) {
        OrderInfoDto dto = new OrderInfoDto();
        dto.setId(dto.getId());
        dto.setBookPrice(dto.getBookPrice());
        dto.setBookQuantity(dto.getBookQuantity());
        dto.setOrderId(dto.getOrderId());
        return null;
    }

    @Override
    public OrderInfo toEntity(OrderInfoDto dto) {
        OrderInfo orderInfoEntity = new OrderInfo();
        orderInfoEntity.setId(dto.getId());
        orderInfoEntity.setBookPrice(dto.getBookPrice());
        orderInfoEntity.setBookQuantity(dto.getBookQuantity());
        orderInfoEntity.setOrderId(dto.getOrderId());
        return orderInfoEntity;
    }
}
