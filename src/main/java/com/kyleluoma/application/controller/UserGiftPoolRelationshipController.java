package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.UserGiftPoolRelationship;
import com.kyleluoma.application.repository.UserGiftPoolRelationshipRepository;

@Controller
@RequestMapping(path="/user_gift_pool_relationship")
public class UserGiftPoolRelationshipController {
    @Autowired
    private UserGiftPoolRelationshipRepository userGiftPoolRelationshipRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUserGiftPoolRelationship (@RequestParam Integer userId,
                                                                @RequestParam Integer giftPoolId) {
        UserGiftPoolRelationship newUserGiftPoolRelationship = new UserGiftPoolRelationship();
        newUserGiftPoolRelationship.setUserId(userId);
        newUserGiftPoolRelationship.setGiftPoolId(giftPoolId);
        userGiftPoolRelationshipRepository.save(newUserGiftPoolRelationship);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserGiftPoolRelationship> getAllUserGiftPoolRelationships() {
        return userGiftPoolRelationshipRepository.findAll();
    }
    
    @GetMapping(path="/by_user_id")
    public @ResponseBody Iterable<UserGiftPoolRelationship> getByUserId(Integer userId) {
        return userGiftPoolRelationshipRepository.findByUserId(userId);
    }
    
    @GetMapping(path="/by_gift_pool_id")
    public @ResponseBody Iterable<UserGiftPoolRelationship> getByGiftPoolId(Integer giftPoolId) {
        return userGiftPoolRelationshipRepository.findByGiftPoolId(giftPoolId);
    }
    
    @GetMapping(path="/by_user_id_and_gift_pool_id")
    public @ResponseBody Iterable<UserGiftPoolRelationship> getByUserIdAndGiftPoolId(Integer userId, Integer giftPoolId) {
        return userGiftPoolRelationshipRepository.findByUserIdAndGiftPoolId(userId, giftPoolId);
    }
    
    @GetMapping(path="/by_user_id_or_gift_pool_id")
    public @ResponseBody Iterable<UserGiftPoolRelationship> getByUserIdOrGiftPoolId(Integer userId, Integer giftPoolId) {
        return userGiftPoolRelationshipRepository.findByUserIdOrGiftPoolId(userId, giftPoolId);
    }
}
