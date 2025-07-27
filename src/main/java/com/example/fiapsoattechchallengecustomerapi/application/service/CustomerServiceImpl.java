package com.example.fiapsoattechchallengecustomerapi.application.service;

import com.example.fiapsoattechchallengecustomerapi.application.usecase.CustomerUseCase;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerDTO;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerRepository;
import com.example.fiapsoattechchallengecustomerapi.utils.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        var customer = customerRepository.save(mapper.DTOtoDomain(customerDTO));
        return mapper.domainToDTO(customer);
    }

    @Override
    @Cacheable(value = "customer", key = "#id")
    public CustomerDTO findCustomerById(Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        return mapper.domainToDTO(customer);
    }

    @Override
    @Cacheable(value = "customercpf", key = "#cpf")
    public CustomerDTO findCustomerByCpf(String cpf) {
        var customer = customerRepository.findByCpf(cpf).orElse(null);
        return mapper.domainToDTO(customer);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "customer", key = "#id", beforeInvocation = true),
            @CacheEvict(cacheNames = "customercpf", key = "@customerSpelHelper.getCpfById(#id)", beforeInvocation = true)
    })
    public void disableCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}