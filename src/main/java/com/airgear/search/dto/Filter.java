package com.airgear.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
//@Document
@Data
public class Filter {
    private String value;
    private LocalDateTime createdAt;
}
