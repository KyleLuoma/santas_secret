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
public class GiftPoolController {
    @Autowired
    private UserGiftPoolRelationshipRepository userGiftPoolRelationshipRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUserGiftPoolRelationship (@RequestParam Integer userId,
                                                               @RequestParam Integer giftPoolId) {
        User newUserGiftPoolRelationship = new UserGiftPoolRelationship();
        newUserGiftPoolRelationship.setUserId(userId);
        newUserGiftPoolRelationship.setGiftPoolId(giftPoolId);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserGiftPoolRelationship> getAllUserGiftPoolRelationships() {
        return userGiftPoolRelationshipRepository.findAll();
    }
}
