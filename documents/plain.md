## Plain

如果想自行實作 GraphQL controller，需要以下物件

1. DTO : 標準的 GraphQL server 至少要能接收到 query 與 variables 參數

    ```java
    @Data
    public class QueryDto {
        String query;
        String operationName;
        Map<String, Object> variables;
    }
    ```

2. Controller : 負責接收 DTO、調用 Resolver 並回傳結果

    ```java
    @Autowired
    private GraphQL graphql;

    @RequestMapping("/graphql")
    public Map<String, Object> graphql(@RequestBody QueryDto queryDto) {

        String query = queryDto.getQuery();
        String operationName = queryDto.getOperationName();
        Map<String, Object> variables = queryDto.getVariables();

        if (variables == null) {
            variables = new LinkedHashMap<>();
        }

        ExecutionResult executionResult = graphql.execute(ExecutionInput.newExecutionInput()
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
    ```