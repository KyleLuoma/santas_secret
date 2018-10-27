package com.kyleluoma.application.controller;

import com.kyleluoma.application.model.ItemPoolVisibility;
import com.kyleluoma.application.model.UserGiftPoolRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.GiftPool;
import com.kyleluoma.application.repository.GiftPoolRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/gift_pool")
public class GiftPoolController {
    @Autowired
    private GiftPoolRepository giftPoolRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewGiftPool (@RequestParam String poolTitle,
                                                @RequestParam String poolDescription) {
        GiftPool newGiftPool = new GiftPool();
        newGiftPool.setPoolTitle(poolTitle);
        newGiftPool.setPoolDescription(poolDescription);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<GiftPool> getAllGiftPools() {
        return giftPoolRepository.findAll();
    }

    @GetMapping(path="/for_user")
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
    }

    @GetMapping(path="/for_item")
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
    }
}
