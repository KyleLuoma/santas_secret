package com.kyleluoma.application.controller;

import com.kyleluoma.application.model.ItemPoolVisibility;
import com.kyleluoma.application.model.UserGiftPoolRelationship;
import com.kyleluoma.application.repository.ItemPoolVisibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.GiftPool;
import com.kyleluoma.application.repository.GiftPoolRepository;
import com.kyleluoma.application.repository.UserGiftPoolRelationshipRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/gift_pool")
public class GiftPoolController {
    @Autowired
    private GiftPoolRepository giftPoolRepository;
    
    @Autowired
    private UserGiftPoolRelationshipRepository userGiftPoolRelationshipRepository;
    
    @Autowired
    private ItemPoolVisibilityRepository itemPoolVisibilityRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewGiftPool (@RequestParam String poolTitle,
                                                @RequestParam String poolDescription) {
        GiftPool newGiftPool = new GiftPool();
        newGiftPool.setPoolTitle(poolTitle);
        newGiftPool.setPoolDescription(poolDescription);
        giftPoolRepository.save(newGiftPool);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<GiftPool> getAllGiftPools() {
        return giftPoolRepository.findAll();
    }
    
    /**
    * Retrieve all gift pools that a user is subscribed to.
    * @PARAM: userId ID of user for which the pools are to be retrieved
    * @RETURN: Iterable container containing all GiftPool objects associated with the user.
    **/
    @GetMapping(path="/for_user")
    public @ResponseBody Iterable<GiftPool> getUserGiftPools(Integer userId) {
        Iterable<UserGiftPoolRelationship> relationships = userGiftPoolRelationshipRepository.findByUserId(userId);
        ArrayList<Integer> poolNumbers = new ArrayList<>();
        for(UserGiftPoolRelationship relationship : relationships) {
            poolNumbers.add(relationship.getGiftPoolId());
        }
        return giftPoolRepository.findAllById(poolNumbers);
    }

    /*@GetMapping(path="/for_user")
    public @ResponseBody Iterable<GiftPool> getUserGiftPools(
            Integer userId,
            UserGiftPoolRelationshipController ugprc) {
        Iterable<UserGiftPoolRelationship> relationships = ugprc.getAllUserGiftPoolRelationships();
        ArrayList<Integer> poolNumbers = new ArrayList<>();
        for(UserGiftPoolRelationship relationship : relationships) {
            if (relationship.getUserId() == userId) {
                poolNumbers.add(relationship.getGiftPoolId());
            }
        }
        return giftPoolRepository.findAllById(poolNumbers);
    }*/
    
    /**
    * Retrieve all gift pools that a particular item is part of.
    * @PARAM: itemId ID of item for which gift pools should be retrieved
    * @RETURN: Iterable container of gift pools that include a particular item
    **/
    @GetMapping(path="/for_item")
    public @ResponseBody Iterable<GiftPool> getItemGiftPools(Integer itemId) {
        Iterable<ItemPoolVisibility> visibility = itemPoolVisibilityRepository.findByItemIdAndVisible(itemId, true);
        ArrayList<Integer> poolNumbers = new ArrayList<>();
        for(ItemPoolVisibility vis : visibility) {
            poolNumbers.add(vis.getPoolId());
        }
        return giftPoolRepository.findAllById(poolNumbers);
    }

    /*@GetMapping(path="/for_item")
    public @ResponseBody Iterable<GiftPool> getItemGiftPools(
            Integer itemId,
            ItemPoolVisibilityController ipvc) {
        Iterable<ItemPoolVisibility> visibilities = ipvc.getAllItemPoolVisibility();
        ArrayList<Integer> poolNumbers = new ArrayList<>();
        for(ItemPoolVisibility visibility : visibilities) {
            if(visibility.getItemId() == itemId && visibility.getVisible()) {
                poolNumbers.add(visibility.getPoolId());
            }
        }
        return giftPoolRepository.findAllById(poolNumbers);
    }*/
}
