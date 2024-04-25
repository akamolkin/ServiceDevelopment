package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "productRegisters")
@Entity
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Column(name = "internal_id")
    Integer internalId;

    @Id
    String value;
    @OneToMany(mappedBy = "registerType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TppProductRegister> productRegisters = new ArrayList<>();

    @Column(name = "register_type_name")
    String registerTypeName;

 //   @ManyToOne
 //   @JoinColumn(name = "product_class_code", unique = true, nullable = false)
  //  TppRefProductClass productClassCode;

    @Column(name = "product_class_code")
    String productClassCode;

    @Column(name = "register_type_start_date")
    String registerTypeStartDate;

    @Column(name = "register_type_end_date")
    String registerTypeEndDate;

   // @ManyToOne
   // @JoinColumn(name = "account_type"/*, unique = true, nullable = false*/)
   // TppRefAccountType accountType;

    @Column(name = "account_type")
    String accountType;


}
