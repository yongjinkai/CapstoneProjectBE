package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    //Data Persistence: CrudRepository (Basic), JpaRepository (Advanced, extends from CrudRepository)
    //save()
    //findOne()
    //findById()
    //findByEmail()
    //findAll()
    //count()
    //delete()
    //deleteById()
}

