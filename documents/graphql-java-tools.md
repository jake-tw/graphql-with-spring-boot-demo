# GraphQL Java Tools

1. Dependency

    ```xml
    <dependency>
        <groupId>com.graphql-java-kickstart</groupId>
        <artifactId>graphql-spring-boot-starter</artifactId>
        <version>7.0.1</version>
    </dependency>
    <dependency>
        <groupId>com.graphql-java-kickstart</groupId>
        <artifactId>graphql-java-tools</artifactId>
        <version>6.0.2</version>
    </dependency>
    ```

2. Add schema
    - 預設搜尋 *.graphqls

        ```txt
        type Query {
            book(id: ID!): Book
            author(id: ID!): Author
        }
        ...
        ```

3. Implements resolver
    - Resolver type
        - GraphQLQueryResolver
        - GraphQLMutationResolver
        - GraphQLResolver
    - implementation

        ```java
        ...

        public Book book(int id) {
            return bookRepository.findById(id);
        }

        public Author author(int id) {
            return authorRepository.findById(id);
        }
        ```