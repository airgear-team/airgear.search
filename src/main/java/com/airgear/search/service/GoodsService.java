package com.airgear.search.service;

import com.airgear.model.GoodsVerificationStatus;
import com.airgear.model.Price;
import com.airgear.search.dto.GoodsSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface GoodsService {

    Page<GoodsSearchResponse> search(int page, int size, String name, BigDecimal minPrice, Price maxPrice, GoodsVerificationStatus verificationStatus, String goodsStatus, OffsetDateTime startCreatedAt, String endCreatedAt);
}
