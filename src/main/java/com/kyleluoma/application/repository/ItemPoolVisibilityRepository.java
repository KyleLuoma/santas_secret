package com.kyleluoma.application.repository;

import com.kyleluoma.application.model.ItemPoolVisibility;

import org.springframework.data.repository.CrudRepository;

public interface ItemPoolVisibilityRepository extends CrudRepository<ItemPoolVisibility, Integer> {
    
    Iterable<ItemPoolVisibility> findByVisId(Integer visId);
    Iterable<ItemPoolVisibility> findByItemId(Integer itemId);
    Iterable<ItemPoolVisibility> findByPoolId(Integer poolId);
    Iterable<ItemPoolVisibility> findByItemIdAndVisible(Integer itemId, boolean visible);
    Iterable<ItemPoolVisibility> findByPoolIdAndVisible(Integer poolId, boolean visible);
    Iterable<ItemPoolVisibility> findByItemIdOrPoolId(Integer itemId, Integer poolId);
    Iterable<ItemPoolVisibility> findByItemIdOrPoolIdAndVisible(Integer itmeId, Integer poolId, boolean visible);

}
