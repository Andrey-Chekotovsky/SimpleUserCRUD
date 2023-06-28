package com.checkotovsky.SimpleUserCRUD.repositories;

import com.checkotovsky.SimpleUserCRUD.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
