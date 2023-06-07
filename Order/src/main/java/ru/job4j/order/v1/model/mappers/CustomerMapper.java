package ru.job4j.order.v1.model.mappers;

import org.mapstruct.Mapper;
import ru.job4j.order.v1.model.dto.CustomerDto;
import ru.job4j.order.v1.model.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}