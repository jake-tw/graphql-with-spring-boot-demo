# GraphQL SPQR

1. Dependency

    ```xml
    <dependency>
        <groupId>io.leangen.graphql</groupId>
        <artifactId>graphql-spqr-spring-boot-starter</artifactId>
        <version>0.0.4</version>
    </dependency>
    ```

2. Add annotation
    - Root annotation

        ```java
        @Service
        @GraphQLApi
        public class Query {
            ...
            @GraphQLQuery(name = "book")
            public Book book(@GraphQLArgument(name = "id") int id) {
                return bookRepository.findById(id);
            }

            @GraphQLQuery(name = "author")
            public Author author(@GraphQLArgument(name = "id") int id) {
                return authorRepository.findById(id);
            }
        }
        ```

        ```java
        @Service
        @GraphQLApi
        public class Mutation {
            ...
            @GraphQLMutation(name = "deleteBook")
            public boolean deleteBook(int id) {
                return bookRepository.delete(id);
            }
        }
        ```

    - Nested query

        ```java
        @Service
        @GraphQLApi
        public class BookResolver {
            ...
            @GraphQLQuery(name = "author")
            public Author author(@GraphQLContext Book book) {
                return authorRepository.findById(book.getAuthor().getId());
            }
        }
        ```