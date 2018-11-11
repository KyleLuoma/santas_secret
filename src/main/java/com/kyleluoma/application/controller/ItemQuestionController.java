package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.ItemQuestion;
import com.kyleluoma.application.repository.ItemQuestionRepository;

import java.time.Instant;

@Controller
@RequestMapping(path="/item_question")
public class ItemQuestionController {
    @Autowired
    private ItemQuestionRepository itemQuestionRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam Integer itemId,
                                            @RequestParam Integer sendingUserId,
                                            @RequestParam String questionSubject,
                                            @RequestParam String questionBody,
                                            @RequestParam String responseBody,
                                            @RequestParam Instant dateTimeAsked,
                                            @RequestParam Instant dateTimeResponded) {
        ItemQuestion newQuestion = new ItemQuestion();
        newQuestion.setItemId(itemId);
        newQuestion.setSendingUserId(sendingUserId);
        newQuestion.setQuestionSubject(questionSubject);
        newQuestion.setQuestionBody(questionBody);
        newQuestion.setResponseBody(responseBody);
        newQuestion.setDateTimeAsked(dateTimeAsked);
        newQuestion.setDateTimeResponded(dateTimeResponded);
        itemQuestionRepository.save(newQuestion);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<ItemQuestion> getAllItemQuestions() {
        return itemQuestionRepository.findAll();
    }
    
    @GetMapping(path="/by_id")
    public @ResponseBody ItemQuestion getItemQuestionById(Integer questionId) {
        return itemQuestionRepository.findById(questionId).get();
    }
    
    @GetMapping(path="/by_item_id")
    public @ResponseBody Iterable<ItemQuestion> getItemQuestionByItemId(Integer itemId) {
        return itemQuestionRepository.findByItemId(itemId);
    }
    
    @GetMapping(path="/by_sending_user_id")
    public @ResponseBody Iterable<ItemQuestion> getItemQuestionBySendingUserId(Integer sendingUserId) {
        return itemQuestionRepository.findBySendingUserId(sendingUserId);
    }
}
