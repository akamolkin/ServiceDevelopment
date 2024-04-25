package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "account_pool_id")
    AccountPool accountPool;

    @Column(name = "account_number")
    String accountNumber;

    @Column(name = "bussy")
    Boolean bussy;
}
