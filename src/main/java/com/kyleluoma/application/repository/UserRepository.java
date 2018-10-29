package com.kyleluoma.application.repository;

import com.kyleluoma.application.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
  
    User findByEmail(String email);
    Iterable<User> findByFirstNameAndLastName(String firstName, String lastName);
    Iterable<User> findByLastName(String lastName);
    Iterable<User> findByFirstName(String firstName);
    Iterable<User> findByUserName(String userName);
    Iterable<User> findByUserNameAndHashedPassword(String userName, String hashedPassword);

}
