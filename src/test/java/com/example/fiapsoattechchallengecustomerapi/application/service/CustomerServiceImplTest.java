package com.example.fiapsoattechchallengecustomerapi.application.service;

import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerDTO;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerRepository;
import com.example.fiapsoattechchallengecustomerapi.utils.mappers.CustomerMapper;
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
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void createCustomer_ShouldReturnCustomerDTO_WhenValidInput() {
        CustomerDTO inputDTO = new CustomerDTO(1L, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        Customer domainCustomer = new Customer(1L, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        Customer savedCustomer = new Customer(1L, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        CustomerDTO expectedDTO = new CustomerDTO(1L, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);

        when(mapper.DTOtoDomain(inputDTO)).thenReturn(domainCustomer);
        when(customerRepository.save(domainCustomer)).thenReturn(savedCustomer);
        when(mapper.domainToDTO(savedCustomer)).thenReturn(expectedDTO);

        CustomerDTO result = customerService.createCustomer(inputDTO);

        assertEquals(expectedDTO, result);
        verify(mapper).DTOtoDomain(inputDTO);
        verify(customerRepository).save(domainCustomer);
        verify(mapper).domainToDTO(savedCustomer);
    }

    @Test
    void findCustomerById_ShouldReturnCustomerDTO_WhenCustomerExists() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        CustomerDTO expectedDTO = new CustomerDTO(customerId, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(mapper.domainToDTO(customer)).thenReturn(expectedDTO);
        
        CustomerDTO result = customerService.findCustomerById(customerId);
        
        assertEquals(expectedDTO, result);
        verify(customerRepository).findById(customerId);
        verify(mapper).domainToDTO(customer);
    }
    
    @Test
    void findCustomerById_ShouldReturnNull_WhenCustomerDoesNotExist() {
        Long customerId = 1L;
        
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        when(mapper.domainToDTO(null)).thenReturn(null);
        
        CustomerDTO result = customerService.findCustomerById(customerId);
        
        assertNull(result);
        verify(customerRepository).findById(customerId);
        verify(mapper).domainToDTO(null);
    }
    
    @Test
    void findCustomerByCpf_ShouldReturnCustomerDTO_WhenCustomerExists() {
        String cpf = "12345678900";
        Customer customer = new Customer(1L, cpf, "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        CustomerDTO expectedDTO = new CustomerDTO(1L, cpf, "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(customerRepository.findByCpf(cpf)).thenReturn(Optional.of(customer));
        when(mapper.domainToDTO(customer)).thenReturn(expectedDTO);
        
        CustomerDTO result = customerService.findCustomerByCpf(cpf);
        
        assertEquals(expectedDTO, result);
        verify(customerRepository).findByCpf(cpf);
        verify(mapper).domainToDTO(customer);
    }
    
    @Test
    void findCustomerByCpf_ShouldReturnNull_WhenCustomerDoesNotExist() {
        String cpf = "12345678900";
        
        when(customerRepository.findByCpf(cpf)).thenReturn(Optional.empty());
        when(mapper.domainToDTO(null)).thenReturn(null);
        
        CustomerDTO result = customerService.findCustomerByCpf(cpf);
        
        assertNull(result);
        verify(customerRepository).findByCpf(cpf);
        verify(mapper).domainToDTO(null);
    }
    
    @Test
    void disableCustomerById_ShouldCallRepositoryDeleteById() {
        Long customerId = 1L;
        
        customerService.disableCustomerById(customerId);
        
        verify(customerRepository).deleteById(customerId);
    }
}