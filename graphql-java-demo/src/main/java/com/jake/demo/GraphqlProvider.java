package com.jake.demo;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.jake.demo.fatcher.BookDataFetchers;
import com.jake.demo.fatcher.Mutation;
import com.jake.demo.fatcher.Query;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;


@Configuration
public class GraphqlProvider {
    
    @Autowired
    private Query query;
    @Autowired
    private Mutation mutation;
    @Autowired
    private BookDataFetchers bookDataFetchers;

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
                        .dataFetcher("allBooks", query.allBooks())
                        .dataFetcher("countBooks", query.countBooks())
                        .dataFetcher("allAuthors", query.allAuthors())
                        .dataFetcher("countAuthors", query.countAuthors())
                        .dataFetcher("book", query.book())
                        .dataFetcher("author", query.author()))
                .type(TypeRuntimeWiring.newTypeWiring("Book")
                        .dataFetcher("author", bookDataFetchers.author()))
                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
                        .dataFetcher("addAuthor", mutation.addAuthor())
                        .dataFetcher("deleteAuthor", mutation.deleteAuthor())
                        .dataFetcher("addBook", mutation.addBook())
                        .dataFetcher("deleteBook", mutation.deleteBook()))
                .build();
    }
}