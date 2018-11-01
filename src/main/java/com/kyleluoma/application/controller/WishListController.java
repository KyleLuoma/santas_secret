package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.WishList;
import com.kyleluoma.application.repository.WishListRepository;

@Controller
@RequestMapping(path="/wish_list")
public class WishListController {
    @Autowired
    private WishListRepository wishListRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewWishList (@RequestParam Integer userId,
                                                @RequestParam String title,
                                                @RequestParam String description) {
        WishList newWishList = new WishList();
        newWishList.setUserId(userId);
        newWishList.setTitle(title);
        newWishList.setDescription(description);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<WishList> getAllWishLists() {
        return wishListRepository.findAll();
    }
    
    @GetMapping(path="/by_user_id")
    public @ResponseBody WishList getWishListByUserId(Integer userId) {
        return wishListRepository.findByUserId(userId);
    }
    
    @GetMapping(path="/by_title")
    public @ResponseBody Iterable<Wishlist> getWishListsByTitle(String title) {
        return wishListRepository.findByTitle(title);
    }    
}
