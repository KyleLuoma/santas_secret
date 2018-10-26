package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.UserGiftPoolAssociation;
import com.kyleluoma.application.repository.UserGiftPoolAssociationRepository;

@Controller
@RequestMapping(path="/user_gift_pool_association")
public class GiftPoolController {
    @Autowired
    private UserGiftPoolAssociationRepository userGiftPoolAssociationRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUserGiftPoolAssociation (@RequestParam Integer userId,
                                                               @RequestParam Integer giftPoolId) {
        User newUserGiftPoolAssociation = new UserGiftPoolAssociation();
        newUserGiftPoolAssociation.setUserId(userId);
        newUserGiftPoolAssociation.setGiftPoolId(giftPoolId);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserGiftPoolAssociation> getAllUserGiftPoolAssociations() {
        return userGiftPoolAssociationRepository.findAll();
    }
}
