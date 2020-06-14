# 開發環境

GraphQL API 可以使用任何語言實作，目前主力使用的語言是 Java，所以也選用 Java 來實作，這邊會列出比較活躍的 Open source framework，以及他們的主要差異

1. Java
    - Version : 14
    - Spring Boot : 2.3.0
    - Dev tools
        - Eclipse 2020-03 + Java 14 Support plugin

2. Framework 選擇
    - GraphQL Java
        - https://github.com/graphql-java/graphql-java
        - Schema first
        - 需要自行實作 DataFetchers
    - GraphQL Java Tools
        - https://github.com/graphql-java-kickstart/graphql-java-tools
        - Base on GraphQL Java
        - Schema first，加上 Resolver tool
        - 自動生成 DataFetchers
    - GraphQL SPQR
        - https://github.com/leangen/graphql-spqr
        - Base on GraphQL Java
        - Code first
