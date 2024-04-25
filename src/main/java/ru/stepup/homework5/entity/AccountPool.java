package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "accounts")
@Entity
@Table(name = "account_pool")
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "branch_code")
    String branchCode;

    @Column(name = "currency_code")
    String currencyCode;

    @Column(name = "mdm_code")
    String mdmCode;

    @Column(name = "priority_code")
    String priorityCode;

    @Column(name = "registry_type_code")
    String registryTypeCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_pool_id")
    List<Account> accounts = new ArrayList<>();
}
