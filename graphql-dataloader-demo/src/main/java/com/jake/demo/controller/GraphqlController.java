package com.jake.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jake.demo.dto.QueryDto;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
public class GraphqlController {
    
    @Autowired
    private GraphQL graphql;
    
    @Autowired
    private DataLoaderRegistry dataLoaderRegistry;
    
    @RequestMapping("/graphql")
    public Map<String, Object> graphql(@RequestBody QueryDto queryDto) {
        
        String query = queryDto.getQuery();
        String operationName = queryDto.getOperationName();
        Map<String, Object> variables = queryDto.getVariables();
        
        if (variables == null) {
            variables = new LinkedHashMap<>();
        }
        
        
        ExecutionResult executionResult = graphql.execute(ExecutionInput.newExecutionInput()
                .dataLoaderRegistry(dataLoaderRegistry)
                .query(query)
                .operationName(operationName)
                .variables(variables)
                .build());

        Map<String, Object> result = new LinkedHashMap<>();
        if (executionResult.getErrors().size() > 0) {
            result.put("errors", executionResult.getErrors());
        }
        result.put("data", executionResult.getData());
        return result;
    }
}
