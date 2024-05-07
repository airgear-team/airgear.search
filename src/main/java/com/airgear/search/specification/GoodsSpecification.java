package com.airgear.search.specification;

import com.airgear.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSpecification {

    private String name;
    private BigDecimal minPrice;
    private Price maxPrice;
    private GoodsVerificationStatus verificationStatus;
    private String goodsStatus;
    private OffsetDateTime startCreatedAt;
    private String endCreatedAt;

    public Specification<Goods> createSpecification() {
        return Specification.where(nameContains())
                .and(priceGreaterThan())
                .and(priceLesserThan())
                .and(verificationStatusIs())
                .and(statusIs())
                .and(createdAtBetween());
    }

    private Specification<Goods> nameContains() {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private Specification<Goods> priceGreaterThan() {
        return (root, query, criteriaBuilder) ->
                minPrice == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("price").get("priceAmount"), minPrice);
    }

    private Specification<Goods> priceLesserThan() {
        return (root, query, criteriaBuilder) ->
                minPrice == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("price").get("priceAmount"), maxPrice.getPriceAmount());
    }

    private Specification<Goods> verificationStatusIs() {
        return (root, query, criteriaBuilder) ->
                verificationStatus == null ? null : criteriaBuilder.equal(root.get("verificationStatus"), verificationStatus);
    }

    private Specification<Goods> statusIs() {
        return (root, query, criteriaBuilder) ->
                goodsStatus == null ? null : criteriaBuilder.equal(root.get("status"), goodsStatus);
    }

    private Specification<Goods> createdAtBetween() {
        return (root, query, criteriaBuilder) ->
                startCreatedAt == null || endCreatedAt == null ? null : criteriaBuilder.between(root.get("createdAt"), startCreatedAt, OffsetDateTime.parse(endCreatedAt));
    }
}
