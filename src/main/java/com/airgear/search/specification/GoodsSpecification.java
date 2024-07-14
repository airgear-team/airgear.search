package com.airgear.search.specification;

import com.airgear.model.*;
import lombok.AllArgsConstructor;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class GoodsSpecification implements Specification<Goods>{

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(
            Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

            return switch (criteria.getOperation()) {
                case EQUALITY -> builder.equal(getExpression(root), criteria.getValue());
                case NEGATION -> builder.notEqual(getExpression(root), criteria.getValue());
                case GREATER_THAN -> builder.greaterThan(getExpression(root), criteria.getValue().toString());
                case LESS_THAN -> builder.lessThan(getExpression(root), criteria.getValue().toString());
                case LIKE -> builder.like(getExpression(root), criteria.getValue().toString());
                case STARTS_WITH -> builder.like(getExpression(root), criteria.getValue() + "%");
                case ENDS_WITH -> builder.like(getExpression(root), "%" + criteria.getValue());
                case CONTAINS -> builder.like(getExpression(root), "%" + criteria.getValue() + "%");
            };

    }

    private Path<String> getExpression(Root<Goods> root) {
        String name = criteria.getKey();
        if (!name.contains(".")) {
            return root.get(name);
        }
        String[] s= name.split("\\.");
        Path<String> res=root.get(s[0]);
        int i=1;
        while (i<s.length){
            res =res.get(s[i]);
            i++;
        }
        return res;
    }

}
