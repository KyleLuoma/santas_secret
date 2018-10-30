package com.kyleluoma.application.repository;

import com.kyleluoma.application.model.UserGiftPoolRelationship;

import org.springframework.data.repository.CrudRepository;

public interface UserGiftPoolRelationshipRepository extends CrudRepository<UserGiftPoolRelationship, Integer> {
    
    UserGiftPoolRelationship findByRelId(Integer relId);
    Iterable<UserGiftPoolRelationship> findByUserId(Integer userId);
    Iterable<UserGiftPoolRelationship> findByGiftPoolId(Integer giftPoolId);
    Iterable<UserGiftPoolRelationship> findByUserIdAndGiftPoolId(Integer userId, Integer giftPoolId);
    Iterable<UserGiftPoolRelationship> findByUserIdOrGiftPoolId(Integer userId, Integer giftPoolId);

}
