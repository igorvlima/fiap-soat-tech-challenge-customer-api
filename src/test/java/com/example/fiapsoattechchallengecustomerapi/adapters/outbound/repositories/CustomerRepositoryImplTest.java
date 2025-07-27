package com.example.fiapsoattechchallengecustomerapi.adapters.outbound.repositories;

import com.example.fiapsoattechchallengecustomerapi.adapters.outbound.entities.JpaCustomerEntity;
import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import com.example.fiapsoattechchallengecustomerapi.exceptions.CustomerNotFoundException;
import com.example.fiapsoattechchallengecustomerapi.utils.mappers.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

    @Mock
    private JpaCustomerRepository jpaCustomerRepository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerRepositoryImpl customerRepository;

    @Test
    void save_ShouldSetActiveAndCreatedAtAndReturnMappedCustomer() {
        Customer inputCustomer = new Customer(null, "12345678900", "John Doe", "teste@teste.com", null, null, null);
        JpaCustomerEntity savedEntity = new JpaCustomerEntity(1L, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        Customer expectedCustomer = new Customer(1L, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(jpaCustomerRepository.save(any(JpaCustomerEntity.class))).thenReturn(savedEntity);
        when(mapper.jpaToDomain(savedEntity)).thenReturn(expectedCustomer);
        
        Customer result = customerRepository.save(inputCustomer);
        
        assertNotNull(result);
        assertEquals(expectedCustomer, result);
        
        verify(jpaCustomerRepository).save(any(JpaCustomerEntity.class));
        verify(mapper).jpaToDomain(savedEntity);
    }
    
    @Test
    void findById_ShouldReturnCustomer_WhenCustomerExists() {
        Long customerId = 1L;
        JpaCustomerEntity entity = new JpaCustomerEntity(customerId, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        Customer expectedCustomer = new Customer(customerId, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(jpaCustomerRepository.findById(customerId)).thenReturn(Optional.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(expectedCustomer);
        
        Optional<Customer> result = customerRepository.findById(customerId);
        
        assertTrue(result.isPresent());
        assertEquals(expectedCustomer, result.get());
        verify(jpaCustomerRepository).findById(customerId);
        verify(mapper).jpaToDomain(entity);
    }
    
    @Test
    void findById_ShouldThrowException_WhenCustomerDoesNotExist() {
        Long customerId = 1L;
        
        when(jpaCustomerRepository.findById(customerId)).thenReturn(Optional.empty());
        
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
                () -> customerRepository.findById(customerId));
        
        assertEquals("Cliente com ID " + customerId + " não encontrado", exception.getMessage());
        verify(jpaCustomerRepository).findById(customerId);
        verify(mapper, never()).jpaToDomain(any());
    }
    
    @Test
    void findByCpf_ShouldReturnCustomer_WhenCustomerExists() {
        String cpf = "12345678900";
        JpaCustomerEntity entity = new JpaCustomerEntity(1L, cpf, "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        Customer expectedCustomer = new Customer(1L, cpf, "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(jpaCustomerRepository.findByCpf(cpf)).thenReturn(Optional.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(expectedCustomer);
        
        Optional<Customer> result = customerRepository.findByCpf(cpf);
        
        assertTrue(result.isPresent());
        assertEquals(expectedCustomer, result.get());
        verify(jpaCustomerRepository).findByCpf(cpf);
        verify(mapper).jpaToDomain(entity);
    }
    
    @Test
    void findByCpf_ShouldThrowException_WhenCustomerDoesNotExist() {
        String cpf = "12345678900";
        
        when(jpaCustomerRepository.findByCpf(cpf)).thenReturn(Optional.empty());
        
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
                () -> customerRepository.findByCpf(cpf));
        
        assertEquals("Cliente com CPF " + cpf + " não encontrado", exception.getMessage());
        verify(jpaCustomerRepository).findByCpf(cpf);
        verify(mapper, never()).jpaToDomain(any());
    }
    
    @Test
    void deleteById_ShouldCallJpaRepositoryDisableCustomerById() {
        Long customerId = 1L;
        
        customerRepository.deleteById(customerId);
        
        verify(jpaCustomerRepository).disableCustomerById(customerId);
    }
}