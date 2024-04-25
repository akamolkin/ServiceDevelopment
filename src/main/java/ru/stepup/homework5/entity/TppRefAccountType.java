package ru.stepup.homework5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "value")
@Entity
@Table(name = "tpp_ref_account_type")
@AllArgsConstructor
@NoArgsConstructor
public class TppRefAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer internalId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountType")
    List<TppRefProductRegisterType> value;
}
