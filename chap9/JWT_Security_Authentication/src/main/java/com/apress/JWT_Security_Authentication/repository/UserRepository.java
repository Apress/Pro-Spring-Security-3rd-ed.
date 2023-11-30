package com.apress.JWT_Security_Authentication.repository;

import com.apress.JWT_Security_Authentication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);


}


