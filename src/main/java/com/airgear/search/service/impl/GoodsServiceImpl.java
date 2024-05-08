package com.airgear.search.service.impl;

import com.airgear.model.Goods;
import com.airgear.model.GoodsVerificationStatus;
import com.airgear.model.Price;
import com.airgear.search.dto.GoodsSearchResponse;
import com.airgear.search.mapper.GoodsSearchMapper;
import com.airgear.search.repository.GoodsRepository;
import com.airgear.search.service.GoodsService;
import com.airgear.search.specification.GoodsSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Service(value = "goodsService")
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsSearchMapper goodsSearchMapper;

    @Override
    public Page<GoodsSearchResponse> search(int page, int size, String name, BigDecimal minPrice, Price maxPrice,
                                            GoodsVerificationStatus verificationStatus, String goodsStatus,
                                            OffsetDateTime startCreatedAt, String endCreatedAt) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<Goods> spec = GoodsSpecification.builder()
                .name(name)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .verificationStatus(verificationStatus)
                .goodsStatus(goodsStatus)
                .startCreatedAt(startCreatedAt)
                .endCreatedAt(endCreatedAt)
                .build()
                .createSpecification();
        return goodsRepository.findAll(spec, pageRequest).map(goodsSearchMapper::toDto);
    }
}