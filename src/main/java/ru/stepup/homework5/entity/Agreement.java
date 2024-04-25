package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "agreement")
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", unique = true, nullable = false)
    TppProduct tppProduct;

    @Column(name = "general_agreement_id")
    String generalAgreementId;

    @Column(name = "num")
    String number;

  // убрал поля не влияющие на результат
}
