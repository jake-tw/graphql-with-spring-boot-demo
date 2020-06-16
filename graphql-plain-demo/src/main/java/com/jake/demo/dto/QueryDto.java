package com.jake.demo.dto;

import java.util.Map;

import lombok.Data;

@Data
public class QueryDto {
    String query;
    String operationName;
    Map<String, Object> variables;
}
