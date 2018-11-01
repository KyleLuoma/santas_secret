package com.kyleluoma.application.controller;

import com.kyleluoma.application.model.ItemPoolVisibility;
import com.kyleluoma.application.repository.ItemPoolVisibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.DesiredItem;
import com.kyleluoma.application.repository.DesiredItemRepository;

import java.util.ArrayList;

@Controller
@RequestMapping(path="/desired_item")
public class DesiredItemController {
    @Autowired
    private DesiredItemRepository desiredItemRepository;

    @Autowired
    private ItemPoolVisibilityRepository itemPoolVisibilityRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewDesiredItem (@RequestParam Integer wishListID,
                                                   @RequestParam String title,
                                                   @RequestParam String description,
                                                   @RequestParam String URL,
                                                   @RequestParam Boolean purchased,
                                                   @RequestParam Integer purchasedByUserId,
                                                   @RequestParam Enum priority) {
        DesiredItem newDesiredItem = new DesiredItem();
        newDesiredItem.setWishListID(wishListID);
        newDesiredItem.setTitle(title);
        newDesiredItem.setDescription(description);
        newDesiredItem.setURL(URL);
        newDesiredItem.setPurchased(purchased);
        newDesiredItem.setPurchasedByUserId(purchasedByUserId);
        newDesiredItem.setPriority(priority);
        desiredItemRepository.save(newDesiredItem);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<DesiredItem> getAllDesiredItems() {
        return desiredItemRepository.findAll();
    }

    @GetMapping(path="/wish_list_items")
    public @ResponseBody Iterable<DesiredItem> getWishListDesiredItems(Integer wishListId) {
        return desiredItemRepository.findByWishListID(wishListId);
    }

    @GetMapping(path="/item_pool_items")
    public @ResponseBody Iterable<DesiredItem> getItemPoolDesiredItems(Integer giftPoolId) {
        ArrayList<Integer> itemIds = new ArrayList<>();
        for(ItemPoolVisibility itemPoolVis : itemPoolVisibilityRepository.findByPoolId(giftPoolId)) {
            if (itemPoolVis.getPoolId() == giftPoolId) {
                itemIds.add(itemPoolVis.getItemId());
            }
        }
        return desiredItemRepository.findAllById(itemIds);
    }
}
