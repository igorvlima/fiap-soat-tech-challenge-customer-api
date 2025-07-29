package com.example.fiapsoattechchallengecustomerapi.utils.mappers;

import com.example.fiapsoattechchallengecustomerapi.adapters.outbound.entities.JpaCustomerEntity;
import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private CustomerMapper mapper;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        mapper = new CustomerMapper();
        now = LocalDateTime.now();
    }

    @Test
    void jpaToDomain_ShouldMapAllFields_WhenEntityIsNotNull() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1L, "12345678900", "John Doe", "teste@teste.com", true, now, null);

        Customer result = mapper.jpaToDomain(entity);

        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getCpf(), result.getCpf());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getEmail(), result.getEmail());
        assertEquals(entity.getActive(), result.getActive());
        assertEquals(entity.getCreatedAt(), result.getCreatedAt());
        assertEquals(entity.getUpdatedAt(), result.getUpdatedAt());
    }

    @Test
    void jpaToDomain_ShouldReturnNull_WhenEntityIsNull() {
        Customer result = mapper.jpaToDomain(null);
        assertNull(result);
    }

    @Test
    void DTOtoDomain_ShouldMapAllFields_WhenDTOIsNotNull() {
        CustomerDTO dto = new CustomerDTO(1L, "12345678900", "John Doe", "teste@teste.com", true, now, null);

        Customer result = mapper.DTOtoDomain(dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getCpf(), result.getCpf());
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getActive(), result.getActive());
        assertEquals(dto.getCreatedAt(), result.getCreatedAt());
        assertEquals(dto.getUpdatedAt(), result.getUpdatedAt());
    }

    @Test
    void DTOtoDomain_ShouldReturnNull_WhenDTOIsNull() {
        Customer result = mapper.DTOtoDomain(null);
        assertNull(result);
    }

    @Test
    void domainToDTO_ShouldMapAllFields_WhenCustomerIsNotNull() {
        Customer customer = new Customer(1L, "12345678900", "John Doe", "teste@teste.com", true, now, null);

        CustomerDTO result = mapper.domainToDTO(customer);

        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
        assertEquals(customer.getCpf(), result.getCpf());
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getEmail(), result.getEmail());
        assertEquals(customer.getActive(), result.getActive());
        assertEquals(customer.getCreatedAt(), result.getCreatedAt());
        assertEquals(customer.getUpdatedAt(), result.getUpdatedAt());
    }

    @Test
    void domainToDTO_ShouldReturnNull_WhenCustomerIsNull() {
        CustomerDTO result = mapper.domainToDTO(null);
        assertNull(result);
    }
}