package com.checkotovsky.SimpleUserCRUD.repositories;

import com.checkotovsky.SimpleUserCRUD.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
