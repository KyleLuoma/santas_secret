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
import com.kyleluoma.application.model.ItemPoolVisibility;
import com.kyleluoma.application.repository.GiftPoolRepository;
import com.kyleluoma.application.repository.UserGiftPoolRelationshipRepository;
import com.kyleluoma.application.repository.DesiredItemRepository;

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
    
    @Autowired
    private DesiredItemRepository desiredItemRepository;

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
    
    @GetMapping(path="/user_pool_meta_data")
    public @ResponseBody Iterable<PoolMeta> getUserPoolMetaData(Integer userId) {
        private ArrayList<UserGiftPoolRelationship> userPools = userGiftPoolRelationshipRepository.findByUserId(userId);
        private ArrayList<PoolMeta> poolMeta = new ArrayList<>();
        for(pool : userPools) {
            poolMeta.add(new PoolMeta(pool.poolId, userId));
        }
        return poolMeta;
    }
        
    private class PoolMeta(Integer poolIdIn, Integer userIdIn) {
        private Integer poolId;
        private Integer userId;
        private Integer numUsers;
        private Integer numItems;
        private Integer numGiftsPurchased;
        
            public PoolMeta(Integer poolIdIn, Integer userIdIn) {
                this.poolId = poolIdIn;
                this.userId = userIdIn;
                
                //Find number of users in this gift pool.
                this.numUsers = userGiftPoolRelationshipRepository.findByGiftPoolId(poolId).size();
                
                //Find all items owned by user userId and count them.
                this.numItems = 0;             
                private ArrayList<ItemPoolVisibility> itemsInPool = itemPoolVisibilityRepository.findByPoolId(poolId);
                for(item : itemsInPool) {
                    if (item.getUserId == userId) { numItems++; }
                }
                
                //Find all gifts purchased by user in this pool and count them:
                this.numGiftsPurchased = desiredItemRepository.findByPurchasedByUserId(userId).size();
            }
        
            public Integer getPoolId() {
                return this.poolId;
            }
        
            public Integer getUserId() {
                return this.userId;
            }
        
            public Integer getNumUsers() {
                return this.numUsers;
            }
        
            public Integer getNumLists() {
                return this.numLists;
            }
        
            public Integer getNumGiftsPurchased() {
                return this.getNumGiftsPurchased;
            }
    }
}
