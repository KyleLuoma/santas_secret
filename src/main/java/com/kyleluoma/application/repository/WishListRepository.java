package com.kyleluoma.application.repository;

import com.kyleluoma.application.model.WishList;

import org.springframework.data.repository.CrudRepository;

public interface WishListRepository extends CrudRepository<WishList, Integer> {
    
    Iterable<WishList> findByUserId(Integer userId);
    Iterable<WishList> findByTitle(String title);

}
