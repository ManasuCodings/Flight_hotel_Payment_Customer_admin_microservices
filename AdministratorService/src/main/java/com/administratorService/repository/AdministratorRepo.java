package com.administratorService.repository;

import com.administratorService.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepo extends JpaRepository<Administrator,Long> {
    Optional<Administrator> findAdminByEmail(String email);
}
