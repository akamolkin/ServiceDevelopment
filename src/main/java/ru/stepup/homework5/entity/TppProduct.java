package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "agreements")
@Entity
@Table(name = "tpp_product")
@AllArgsConstructor
@NoArgsConstructor
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "product_code_id")
    Integer productCodeId;

    @Column(name = "client_id")
    Integer client_id;

    @Column(name = "type")
    String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    List<Agreement> agreements = new ArrayList<>();

    @Column(name = "num")
    String number;

}
