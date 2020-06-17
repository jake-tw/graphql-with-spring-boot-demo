package com.jake.demo;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.jake.demo.fatcher.BookDataFetchers;
import com.jake.demo.fatcher.Query;

import graphql.GraphQL;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions;
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
    private BookDataFetchers bookDataFetchers;


    @Bean
    public GraphQL graphQL() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        
        DataLoaderDispatcherInstrumentationOptions options = DataLoaderDispatcherInstrumentationOptions
                .newOptions().includeStatistics(true);

        DataLoaderDispatcherInstrumentation dispatcherInstrumentation
                = new DataLoaderDispatcherInstrumentation(options);
        
        return GraphQL.newGraphQL(graphQLSchema)
                .doNotAddDefaultInstrumentations()
                .instrumentation(dispatcherInstrumentation)
                .build();
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
                        .dataFetcher("allBooks", query.allBooks()))
                .type(TypeRuntimeWiring.newTypeWiring("Book")
                        .dataFetcher("author", bookDataFetchers.author()))
                .build();
    }
    
    @Bean
    public DataLoaderRegistry dataLoaderRegistry(List<BatchLoader<?, ?>> loaders) {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        loaders.forEach(loader -> {
            DataLoader<?, ?> dataLoader = DataLoader.newDataLoader(loader);
            registry.register(loader.getClass().getSimpleName(), dataLoader);
        });

        return registry;
    }
}