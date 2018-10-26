package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.DesiredItem;
import com.kyleluoma.application.repository.DesiredItemRepository;

@Controller
@RequestMapping(path="/desired_item")
public class UserController {
    @Autowired
    private DesiredItemRepository desiredItemRepository;

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
        newDesiredItem.setPriorit(priority);
        DesiredItemRepository.save(newDesiredItem);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<DesiredItem> getAllDesiredItems() {
        return desiredItemRepository.findAll();
    }
}