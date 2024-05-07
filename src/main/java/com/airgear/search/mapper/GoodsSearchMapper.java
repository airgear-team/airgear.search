package com.airgear.search.mapper;

import com.airgear.model.Goods;
import com.airgear.search.dto.GoodsSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsSearchMapper {
    GoodsSearchResponse toDto(Goods goods);

    List<GoodsSearchResponse> toDtoList(List<Goods> users);

}
