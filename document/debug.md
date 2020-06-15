# Debug

1. 效能追蹤
    - 可自訂 Instrumentation 做效能追蹤
    - TracingInstrumentation 是 GraphQL Java 遵循 [Apollo Tracing](https://github.com/apollographql/apollo-tracing) 的實作類

        ```java
        GraphQL.newGraphQL(graphQLSchema)
                .instrumentation(new TracingInstrumentation())
                .build();
        ```

2. 查詢工具
    - [GraphQL Playground](https://github.com/prisma-labs/graphql-playground)
        - Apollo Tracing support

    - [Altair](https://github.com/imolorhe/altair)
    - [GraphiQL](https://github.com/graphql/graphiql)

4. 圖形化工具
    - [Voyager](https://github.com/APIs-guru/graphql-voyager)


