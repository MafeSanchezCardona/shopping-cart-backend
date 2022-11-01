package com.shopping.cart.backend.services;

import java.util.List;

import com.shopping.cart.backend.entities.User;
import com.shopping.cart.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Service
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to save the user entity
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(final User user) {
        userRepository.save(user);
    }

    /**
     * Method to list of users
     *
     * @return List of user
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Method to find specific user
     *
     * @param id
     * @return User
     */
    @Transactional(readOnly = true)
    public User findById(final Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Method to find user by login name and password
     *
     * @param loginName
     * @param password
     * @return User
     */
    @Transactional(readOnly = true)
    public User findByLoginNameAndPassword(final String loginName, final String password) {
        return userRepository.findByLoginNameAndPassword(loginName, password);
    }
}
