package com.example.fiapsoattechchallengecustomerapi.adapters.inbound;

import com.example.fiapsoattechchallengecustomerapi.application.usecase.CustomerUseCase;
import com.example.fiapsoattechchallengecustomerapi.domain.CustomerDTO;
import com.example.fiapsoattechchallengecustomerapi.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    @PostMapping()
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerUseCase.createCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        try {
            CustomerDTO customer = customerUseCase.findCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping()
    public ResponseEntity<CustomerDTO> findCustomerByCpf(@RequestParam String cpf) {
        try {
            CustomerDTO customer = customerUseCase.findCustomerByCpf(cpf);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableCustomerById(@PathVariable Long id) {
        try{
            customerUseCase.disableCustomerById(id);
            return ResponseEntity.ok().build();
        }catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}