package com.airgear.search.repository;

import com.airgear.search.specification.SearchCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchCriteriaRepository extends MongoRepository<SearchCriteria, String> {
}
