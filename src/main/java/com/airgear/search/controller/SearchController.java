package com.airgear.search.controller;

import com.airgear.search.dto.GoodsSearchResponse;
import com.airgear.search.service.GoodsService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final GoodsService goodsService;

    @GetMapping(value = "/{goodsName}")
    @PageableAsQueryParam
    public Page<GoodsSearchResponse> listGoodsByName(@Parameter(hidden = true) Pageable pageable,
                                                     @PathVariable String goodsName) {
        return goodsService.listGoodsByName(pageable, goodsName);
    }
}
