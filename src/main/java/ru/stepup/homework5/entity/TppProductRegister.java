package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.stepup.homework5.enums.States;

@Data
@Entity
@Table(name = "tpp_product_register")
@AllArgsConstructor
@NoArgsConstructor
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "product_id")
    Integer productId;

    @ManyToOne
    @JoinColumn(name = "type"/*, referencedColumnName = "value"*/)
    TppRefProductRegisterType registerType;

    @Column(name = "account")
    Integer account;

    @Column(name = "currency_code")
    String currencyCode;

    @Column(name = "state")
    States state;

    @Column(name = "account_number")
    String accountNumber;
}
