package ru.stepup.homework5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stepup.homework5.entity.TppProductRegister;
import ru.stepup.homework5.entity.TppRefProductRegisterType;

import java.util.Optional;

public interface TppRefProductRegisterTypeRepository extends JpaRepository<TppRefProductRegisterType, Integer> {
    @Query(value = "SELECT internal_id, value, register_type_name, product_class_code, register_type_start_date, register_type_end_date, account_type FROM tpp_ref_product_register_type where value = :value", nativeQuery = true)
    public Optional<TppRefProductRegisterType> findByValue(@Param("value") String value);

    @Query(value = "SELECT prt.internal_id, prt.value, prt.register_type_name, prt.product_class_code, prt.register_type_start_date, prt.register_type_end_date, prt.account_type FROM tpp_ref_product_register_type prt, tpp_ref_product_class pc "
            + " where prt.account_type = 'Клиентский' and prt.product_class_code = pc.value and pc.value = :value", nativeQuery = true)
    public Optional<TppRefProductRegisterType> findByProductClassValue(@Param("value") String value);
}
