package com.jake.demo.exception.base;

import java.util.List;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public interface BaseGraphQLException extends GraphQLError {
    default ErrorType getErrorType() {
        return null;
    };

    default List<SourceLocation> getLocations() {
        return null;
    };
}
