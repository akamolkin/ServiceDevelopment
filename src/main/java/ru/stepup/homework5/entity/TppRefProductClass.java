package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@ToString(exclude = "value")
@Entity
@Table(name = "tpp_ref_product_class")
@AllArgsConstructor
@NoArgsConstructor
public class TppRefProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer internalId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productClassCode")
    Set<TppRefProductRegisterType> value;

    @Column(name = "gbi_code")
    String gbiCode;

    @Column(name = "gbi_name")
    String gbiName;

    @Column(name = "product_row_code")
    String productRowCode;

    @Column(name = "product_row_name")
    String productRowName;

    @Column(name = "subclass_code")
    String subclassCode;

    @Column(name = "subclass_name")
    String subclassName;
}
