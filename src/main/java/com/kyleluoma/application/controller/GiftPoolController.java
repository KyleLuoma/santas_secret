package com.kyleluoma.application.controller;

import com.kyleluoma.application.model.*;
import com.kyleluoma.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.ItemPoolVisibility;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/gift_pool")
public class GiftPoolController {
    @Autowired
    private GiftPoolRepository giftPoolRepository;

    @Autowired
    private WishListRepository wishListRepository;
    
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
        Iterable<UserGiftPoolRelationship> userPools = userGiftPoolRelationshipRepository.findByUserId(userId);
        ArrayList<PoolMeta> poolMeta = new ArrayList<>();
        for(UserGiftPoolRelationship pool : userPools) {
            poolMeta.add(new PoolMeta(pool.getGiftPoolId(), userId));
        }
        return poolMeta;
    }
        
    private class PoolMeta {
        Integer poolId;
        Integer userId;
        Integer numUsers;
        Integer numItems;
        Integer numGiftsPurchased;
        
            public PoolMeta(Integer poolIdIn, Integer userIdIn) {
                this.poolId = poolIdIn;
                this.userId = userIdIn;

                //Find number of users in this gift pool.
                this.numUsers = (int) userGiftPoolRelationshipRepository
                        .findByGiftPoolId(poolId)
                        .spliterator()
                        .getExactSizeIfKnown();
                //Find all items owned by user userId and count them.
                this.numItems = 0;             
                Iterable<ItemPoolVisibility> itemsInPool = itemPoolVisibilityRepository.findByPoolId(poolId);
                Iterable<WishList> userWishLists = wishListRepository.findByUserId(userId);
                Iterable<DesiredItem> userItems;
                for(WishList userWishList : userWishLists) {
                    if (userWishList != null) {
                        userItems = desiredItemRepository.findByWishListID(userWishList.getId());
                        for(DesiredItem item : userItems) {
                            for(ItemPoolVisibility poolItem : itemsInPool) {
                                if (item.getId() == poolItem.getItemId()) {
                                    numItems++;
                                }
                            }
                        }
                    }
                }
                
                
                //Find all gifts purchased by user in this pool and count them:
                this.numGiftsPurchased = (int) desiredItemRepository
                        .findByPurchasedByUserId(userId)
                        .spliterator()
                        .getExactSizeIfKnown();
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
        
            public Integer getNumItems() {
                return this.numItems;
            }
        
            public Integer getNumGiftsPurchased() {
                return this.numGiftsPurchased;
            }
    }
}
