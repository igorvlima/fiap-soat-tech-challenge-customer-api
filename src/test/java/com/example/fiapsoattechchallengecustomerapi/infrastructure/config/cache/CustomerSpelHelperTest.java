package com.example.fiapsoattechchallengecustomerapi.infrastructure.config.cache;

import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerSpelHelperTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerSpelHelper customerSpelHelper;

    @Test
    void getCpfById_ShouldReturnCpf_WhenCustomerExists() {
        Long customerId = 1L;
        String expectedCpf = "12345678900";
        Customer customer = new Customer(customerId, expectedCpf, "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        
        String result = customerSpelHelper.getCpfById(customerId);
        
        assertEquals(expectedCpf, result);
        verify(customerRepository).findById(customerId);
    }
    
    @Test
    void getCpfById_ShouldReturnNull_WhenCustomerDoesNotExist() {
        Long customerId = 1L;
        
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        
        String result = customerSpelHelper.getCpfById(customerId);
        
        assertNull(result);
        verify(customerRepository).findById(customerId);
    }
}