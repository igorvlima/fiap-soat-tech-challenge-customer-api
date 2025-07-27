package com.example.fiapsoattechchallengecustomerapi.adapters.outbound.entities;

import com.example.fiapsoattechchallengecustomerapi.domain.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "customer")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_id_seq", allocationSize = 1)
public class JpaCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    private Long id;
    private String cpf;
    private String name;
    private String email;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JpaCustomerEntity(Customer customer) {
        this.id = customer.getId();
        this.cpf = customer.getCpf();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.active = customer.getActive();
        this.createdAt = customer.getCreatedAt();
        this.updatedAt = customer.getUpdatedAt();
    }
}

