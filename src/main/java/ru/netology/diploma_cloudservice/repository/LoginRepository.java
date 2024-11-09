package ru.netology.diploma_cloudservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.diploma_cloudservice.entity.User;

@Repository
@Transactional
public interface LoginRepository extends JpaRepository <User, String> {
    User findByUsername(String username);
}
