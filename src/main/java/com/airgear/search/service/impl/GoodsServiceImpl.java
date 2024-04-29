package com.airgear.search.service.impl;

import com.airgear.search.dto.GoodsSearchResponse;
import com.airgear.search.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service(value = "goodsService")
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    @Override
    public Page<GoodsSearchResponse> listGoodsByName(Pageable pageable, String goodsName) {
        return null;
    }
}