package com.airgear.search.mapper;

import com.airgear.model.Goods;
import com.airgear.search.dto.GoodsSearchResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoodsSearchMapper {
    GoodsSearchResponse toDto(Goods goods);
}