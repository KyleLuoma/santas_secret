package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.ItemPoolVisibility;
import com.kyleluoma.application.repository.ItemPoolVisibilityRepository;

@Controller
@RequestMapping(path="/item_pool_visibility")
public class ItemPoolVisibilityController {
    @Autowired
    private ItemPoolVisibilityRepository itemPoolVisibilityRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewItemPoolVisibility (@RequestParam Integer itemId,
                                                          @RequestParam Integer poolId,
                                                          @RequestParam boolean visible) {
        User newItemPoolVisibility = new ItemPoolVisibility();
        newItemPoolVisibility.setItemId(itemId);
        newItemPoolVisibility.setPoolId(poolId);
        newItemPoolVisibility.setVisible(visible);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<ItemPoolVisibility> getAllItemPoolVisibility() {
        return itemPoolVisibilityRepository.findAll();
    }
}
