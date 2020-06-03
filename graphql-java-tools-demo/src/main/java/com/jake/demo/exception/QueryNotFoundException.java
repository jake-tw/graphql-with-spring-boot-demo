package com.jake.demo.exception;

import com.jake.demo.exception.base.BaseGraphQLException;

public class QueryNotFoundException extends RuntimeException implements BaseGraphQLException {

    private static final long serialVersionUID = 1L;

    public QueryNotFoundException(String message) {
        super(message);
    }
}
