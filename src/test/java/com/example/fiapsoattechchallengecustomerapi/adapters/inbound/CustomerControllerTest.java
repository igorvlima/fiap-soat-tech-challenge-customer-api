package com.example.fiapsoattechchallengecustomerapi.adapters.inbound;

import com.example.fiapsoattechchallengecustomerapi.application.usecase.CustomerUseCase;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerDTO;
import com.example.fiapsoattechchallengecustomerapi.exceptions.CustomerNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CustomerUseCase customerUseCase;

    @Test
    void createCustomer_ShouldReturnCreatedCustomer_WhenValidInput() throws Exception {
        CustomerDTO inputDTO = new CustomerDTO(null, "12345678900", "John Doe", "teste@teste.com", true, null, null);
        CustomerDTO outputDTO = new CustomerDTO(1L, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(customerUseCase.createCustomer(any(CustomerDTO.class))).thenReturn(outputDTO);

        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(outputDTO.getId()))
                .andExpect(jsonPath("$.cpf").value(outputDTO.getCpf()))
                .andExpect(jsonPath("$.name").value(outputDTO.getName()))
                .andExpect(jsonPath("$.email").value(outputDTO.getEmail()))
                .andExpect(jsonPath("$.active").value(outputDTO.getActive()));

        verify(customerUseCase).createCustomer(any(CustomerDTO.class));
    }

    @Test
    void findCustomerById_ShouldReturnCustomer_WhenCustomerExists() throws Exception {
        Long customerId = 1L;
        CustomerDTO outputDTO = new CustomerDTO(customerId, "12345678900", "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(customerUseCase.findCustomerById(customerId)).thenReturn(outputDTO);

        mockMvc.perform(get("/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(outputDTO.getId()))
                .andExpect(jsonPath("$.cpf").value(outputDTO.getCpf()))
                .andExpect(jsonPath("$.name").value(outputDTO.getName()))
                .andExpect(jsonPath("$.email").value(outputDTO.getEmail()))
                .andExpect(jsonPath("$.active").value(outputDTO.getActive()));

        verify(customerUseCase).findCustomerById(customerId);
    }

    @Test
    void findCustomerById_ShouldReturnNotFound_WhenCustomerDoesNotExist() throws Exception {
        Long customerId = 1L;
        
        when(customerUseCase.findCustomerById(customerId)).thenThrow(new CustomerNotFoundException("Customer not found"));

        mockMvc.perform(get("/customer/{id}", customerId))
                .andExpect(status().isNotFound());

        verify(customerUseCase).findCustomerById(customerId);
    }

    @Test
    void findCustomerByCpf_ShouldReturnCustomer_WhenCustomerExists() throws Exception {
        String cpf = "12345678900";
        CustomerDTO outputDTO = new CustomerDTO(1L, cpf, "John Doe", "teste@teste.com", true, LocalDateTime.now(), null);
        
        when(customerUseCase.findCustomerByCpf(cpf)).thenReturn(outputDTO);

        mockMvc.perform(get("/customer")
                .param("cpf", cpf))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(outputDTO.getId()))
                .andExpect(jsonPath("$.cpf").value(outputDTO.getCpf()))
                .andExpect(jsonPath("$.name").value(outputDTO.getName()))
                .andExpect(jsonPath("$.email").value(outputDTO.getEmail()))
                .andExpect(jsonPath("$.active").value(outputDTO.getActive()));

        verify(customerUseCase).findCustomerByCpf(cpf);
    }

    @Test
    void findCustomerByCpf_ShouldReturnNotFound_WhenCustomerDoesNotExist() throws Exception {
        String cpf = "12345678900";
        
        when(customerUseCase.findCustomerByCpf(cpf)).thenThrow(new CustomerNotFoundException("Customer not found"));

        mockMvc.perform(get("/customer")
                .param("cpf", cpf))
                .andExpect(status().isNotFound());

        verify(customerUseCase).findCustomerByCpf(cpf);
    }

    @Test
    void disableCustomerById_ShouldReturnNoContent_WhenCustomerExists() throws Exception {
        Long customerId = 1L;
        
        doNothing().when(customerUseCase).disableCustomerById(customerId);

        mockMvc.perform(delete("/customer/{id}", customerId))
                .andExpect(status().isOk());

        verify(customerUseCase).disableCustomerById(customerId);
    }

    @Test
    void disableCustomerById_ShouldReturnNotFound_WhenCustomerDoesNotExist() throws Exception {
        Long customerId = 1L;
        
        doThrow(new CustomerNotFoundException("Customer not found")).when(customerUseCase).disableCustomerById(customerId);

        mockMvc.perform(delete("/customer/{id}", customerId))
                .andExpect(status().isNotFound());

        verify(customerUseCase).disableCustomerById(customerId);
    }
}