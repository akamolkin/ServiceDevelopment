package ru.stepup.homework5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stepup.homework5.entity.Agreement;

import java.util.Optional;

public interface AgreesmentRepository extends JpaRepository<Agreement, Integer> {
    @Query(value = "SELECT id, product_id, general_agreement_id, num FROM agreement where num = :num", nativeQuery = true)
    public Optional<Agreement> findByNum(@Param("num") String num);
}
