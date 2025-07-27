package com.example.fiapsoattechchallengecustomerapi.utils.mappers;

import com.example.fiapsoattechchallengecustomerapi.adapters.outbound.entities.JpaCustomerEntity;
import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer jpaToDomain(JpaCustomerEntity jpaCustomerEntity) {
        if (jpaCustomerEntity == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(jpaCustomerEntity.getId());
        customer.setCpf(jpaCustomerEntity.getCpf());
        customer.setName(jpaCustomerEntity.getName());
        customer.setEmail(jpaCustomerEntity.getEmail());
        customer.setActive(jpaCustomerEntity.getActive());
        customer.setCreatedAt(jpaCustomerEntity.getCreatedAt());
        customer.setUpdatedAt(jpaCustomerEntity.getUpdatedAt());
        return customer;
    }

    public Customer DTOtoDomain(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setCpf(dto.getCpf());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setActive(dto.getActive());
        customer.setCreatedAt(dto.getCreatedAt());
        customer.setUpdatedAt(dto.getUpdatedAt());
        return customer;
    }

    public CustomerDTO domainToDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setCpf(customer.getCpf());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setActive(customer.getActive());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        return dto;
    }
}
