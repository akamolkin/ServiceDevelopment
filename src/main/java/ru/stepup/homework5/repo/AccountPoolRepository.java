package ru.stepup.homework5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stepup.homework5.entity.AccountPool;
import ru.stepup.homework5.entity.TppRefProductRegisterType;

import java.util.Optional;

public interface AccountPoolRepository extends JpaRepository<AccountPool, Integer>{
}
