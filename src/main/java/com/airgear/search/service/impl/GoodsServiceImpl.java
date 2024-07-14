package com.airgear.search.service.impl;

import com.airgear.model.Goods;
import com.airgear.model.GoodsVerificationStatus;
import com.airgear.model.Price;
import com.airgear.search.dto.GoodsSearchResponse;
import com.airgear.search.mapper.GoodsSearchMapper;
import com.airgear.search.repository.GoodsRepository;
import com.airgear.search.service.GoodsService;
import com.airgear.search.specification.GoodsSpecification;
import com.airgear.search.specification.GoodsSpecificationsBuilder;
import com.airgear.search.specification.SearchOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service(value = "goodsService")
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsSearchMapper goodsSearchMapper;

    @Override
    public Page<GoodsSearchResponse> search(String search, Pageable pageable){
        GoodsSpecificationsBuilder builder = new GoodsSpecificationsBuilder();
        String operationSetExp = String.join("|", SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\w+?.\\w+?)(" + operationSetExp + ")(\\p{Punct}?)([\\w\\s]+?)(\\p{Punct}?),",Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        Specification<Goods> spec = builder.build();

        return goodsRepository.findAll(spec, pageable).map(goodsSearchMapper::toDto);
    }
}