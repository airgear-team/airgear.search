package com.airgear.search.controller;


import com.airgear.search.dto.GoodsSearchResponse;
import com.airgear.search.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final GoodsService goodsService;

    @GetMapping("/")
    public Page<GoodsSearchResponse> searchGoods(@RequestParam(value = "search") String search,
                                                                 @PageableDefault(size = 30) Pageable pageable) {

        return  goodsService.search(search,pageable);
    }
}