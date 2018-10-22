package com.kyleluoma.application.repository;

import com.kyleluoma.application.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
