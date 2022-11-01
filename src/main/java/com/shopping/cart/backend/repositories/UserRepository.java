package com.shopping.cart.backend.repositories;

import com.shopping.cart.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginNameAndPassword(String loginName, String password);
}
