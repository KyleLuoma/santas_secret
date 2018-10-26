package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.GiftPool;
import com.kyleluoma.application.repository.GiftPoolRepository;

@Controller
@RequestMapping(path="/gift_pool")
public class GiftPoolController {
    @Autowired
    private GiftPoolRepository giftPoolRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewGiftPool (@RequestParam String poolTitle,
                                                @RequestParam String poolDescription) {
        User newGiftPool = new GiftPool();
        newGiftPool.setPoolTitle(poolTitle);
        newGiftPool.setpoolDescription(poolDescription);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<GiftPool> getAllGiftPools() {
        return giftPoolRepository.findAll();
    }
}
