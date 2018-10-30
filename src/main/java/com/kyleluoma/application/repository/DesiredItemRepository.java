package com.kyleluoma.application.repository;

import com.kyleluoma.application.model.DesiredItem;

import org.springframework.data.repository.CrudRepository;

public interface DesiredItemRepository extends CrudRepository<DesiredItem, Integer> {
  
    Iterable<DesiredItem> findByWishListID(Integer wishListID);
    Iterable<DesiredItem> findByPurchased(Boolean purchased);
    Iterable<DesiredItem> findByPurchasedByUserId(Integer purchasedByUserId);

}
