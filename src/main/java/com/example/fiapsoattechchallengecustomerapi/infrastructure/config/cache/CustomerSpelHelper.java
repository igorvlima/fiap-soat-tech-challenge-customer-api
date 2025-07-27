package com.example.fiapsoattechchallengecustomerapi.infrastructure.config.cache;

import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("customerSpelHelper")
public class CustomerSpelHelper {
    @Autowired
    private CustomerRepository customerRepository;

    public String getCpfById(Long id) {
        return customerRepository.findById(id)
                .map(Customer::getCpf)
                .orElse(null);
    }
}
