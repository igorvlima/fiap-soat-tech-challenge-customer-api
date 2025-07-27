package com.example.fiapsoattechchallengecustomerapi.adapters.outbound.repositories;

import com.example.fiapsoattechchallengecustomerapi.adapters.outbound.entities.JpaCustomerEntity;
import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerRepository;
import com.example.fiapsoattechchallengecustomerapi.exceptions.CustomerNotFoundException;
import com.example.fiapsoattechchallengecustomerapi.utils.mappers.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final CustomerMapper mapper;

    public CustomerRepositoryImpl(JpaCustomerRepository jpaCustomerRepository, CustomerMapper mapper) {
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer customer) {
        customer.setActive(true);
        customer.setCreatedAt(LocalDateTime.now());
        var entity = jpaCustomerRepository.save(new JpaCustomerEntity(customer));
        return mapper.jpaToDomain(entity);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return this.jpaCustomerRepository.findById(id)
                .map(mapper::jpaToDomain)
                .or(() -> {
                    throw new CustomerNotFoundException("Cliente com ID " + id + " não encontrado");
                });
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return this.jpaCustomerRepository.findByCpf(cpf)
                .map(mapper::jpaToDomain)
                .or(() -> {
                    throw new CustomerNotFoundException("Cliente com CPF " + cpf + " não encontrado");
                });
    }

    @Override
    public void deleteById(Long id) {
        this.jpaCustomerRepository.disableCustomerById(id);
    }
}
