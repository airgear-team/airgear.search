package com.airgear.search.service;

import com.airgear.search.dto.GoodsSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoodsService {

    Page<GoodsSearchResponse> listGoodsByName(Pageable pageable, String goodsName);
}
