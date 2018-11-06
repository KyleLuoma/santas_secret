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
        ItemPoolVisibility newItemPoolVisibility = new ItemPoolVisibility();
        newItemPoolVisibility.setItemId(itemId);
        newItemPoolVisibility.setPoolId(poolId);
        newItemPoolVisibility.setVisible(visible);
        itemPoolVisibilityRepository.save(newItemPoolVisibility);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<ItemPoolVisibility> getAllItemPoolVisibility() {
        return itemPoolVisibilityRepository.findAll();
    }
    
    @GetMapping(path="/by_item_id")
    public @ResponseBody Iterable<ItemPoolVisibility> getByItemId(Integer itemId) {
        return itemPoolVisibilityRepository.findByItemId(itemId);
    }
    
    @GetMapping(path="/by_pool_id")
    public @ResponseBody Iterable<ItemPoolVisibility> getByPoolId(Integer poolId) {
        return itemPoolVisibilityRepository.findByPoolId(poolId);
    }
    
    @GetMapping(path="/by_pool_id_and_visible")
    public @ResponseBody Iterable<ItemPoolVisibility> getByPoolIdAndVisible(Integer poolId, boolean visible) {
        return itemPoolVisibilityRepository.findByPoolIdAndVisible(poolId, visible);
    }
    
    @GetMapping(path="/by_item_id_and_visible")
    public @ResponseBody Iterable<ItemPoolVisibility> getByItemIdAndVisible(Integer itemId, boolean visible) {
        return itemPoolVisibilityRepository.findByItemIdAndVisible(itemId, visible);
    }
    
    @GetMapping(path="/by_item_id_or_pool_id")
    Public @ResponseBody Iterable<ItemPoolVisibility> getByItemIdOrPoolId(Integer itemId, Integer poolId) {
        return itemPoolVisibilityRepository.findByItemIdOrPoolId(itemId, poolId);
    }
    
    @GetMapping(path="/by_item_id_or_pool_id_and_visible")
    Public @ResponseBody Iterable<ItemPoolVisibility> getByItemIdOrPoolIdAndVisible(Integer itemId, Integer poolId, boolean visible) {
        return itemPoolVisibilityRepository.findByItemIdOrPoolIdAndVisible(itemId, poolId, visible);
    }
}
