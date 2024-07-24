package com.airgear.search.specification;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public SearchCriteria(String key, SearchOperation op, Object value) {
        this.key = key;
        this.operation = op;
        this.value = value;
    }

    public boolean isOrPredicate() {
        return orPredicate;
    }
}

