package com.example.fiapsoattechchallengecustomerapi.application.usecase;

import com.example.fiapsoattechchallengecustomerapi.domain.CustomerDTO;

public interface CustomerUseCase {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO findCustomerById(Long id);

    CustomerDTO findCustomerByCpf(String cpf);

    void disableCustomerById(Long id);
}

