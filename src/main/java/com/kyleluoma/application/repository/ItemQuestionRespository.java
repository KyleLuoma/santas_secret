package com.kyleluoma.application.repository;

import com.kyleluoma.application.model.ItemQuestion;

import org.springframework.data.repository.CrudRepository;

public interface ItemQuestionRepository extends CrudRepository<ItemQuestion, Integer> {
  
    Iterable<ItemQuestion> findByItemId(Integer itemId);
    Iterable<ItemQuestion> findBySendingUserId(Integer sendingUserId);

}
