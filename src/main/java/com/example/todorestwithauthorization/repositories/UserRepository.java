package com.example.todorestwithauthorization.repositories;

import com.example.todorestwithauthorization.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByName(String name);
    public void deleteByName(String name);

}
