package ru.stepup.homework5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stepup.homework5.entity.TppProduct;
import ru.stepup.homework5.entity.TppProductRegister;

import java.util.Optional;

public interface TppProductRepository extends JpaRepository<TppProduct, Integer> {
    @Query(value = "SELECT id, product_code_id, client_id, type, num FROM tpp_product where num = :num", nativeQuery = true)
    public Optional<TppProduct> findByNum(@Param("num") String num);
}
