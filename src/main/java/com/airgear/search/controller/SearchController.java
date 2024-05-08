package com.airgear.search.controller;

import com.airgear.model.*;
import com.airgear.search.dto.GoodsSearchResponse;
import com.airgear.search.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final GoodsService goodsService;

    @GetMapping("/")
    public ResponseEntity<Page<GoodsSearchResponse>> searchGoods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) Price maxPrice,
            @RequestParam(required = false) GoodsVerificationStatus verificationStatus,
            @RequestParam(required = false) String goodsStatus,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startCreatedAt,
            @RequestParam(required = false) String endCreatedAt) {

        return ResponseEntity
                .ok(goodsService.search(page, size, name, minPrice, maxPrice, verificationStatus, goodsStatus,
                                        startCreatedAt, endCreatedAt));
    }
}