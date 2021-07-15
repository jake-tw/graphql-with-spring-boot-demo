## GraphQL Java

1. Dependency
    - Guava 非必要，但對獲得 schema 很有幫助

        ```xml
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java</artifactId>
            <version>11.0</version>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java-spring-boot-starter-webmvc</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>26.0-jre</version>
        </dependency>
        ```

2. Add schema

    ```txt
    type Query {
        book(id: ID!): Book
        author(id: ID!): Author
    }
    ...
    ```

3. GraphQL configuration

    ```java
    ...

    @Bean
    public GraphQL graphQL() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }
    
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("book", query.book())
                        .dataFetcher("author", query.author()))
                .build();
    }
    ```

4. Build DataFetcher

    ```java
    ...

    public DataFetcher<Book> book() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookRepository.findById(Integer.valueOf(bookId));
        };
    }

    public DataFetcher<Author> author() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return authorRepository.findById(Integer.valueOf(authorId));
        };
    }
    ```