package com.pokemonreview.api.exceptions;

import java.util.concurrent.TimeoutException;

public class ReviewNotFoundException extends RuntimeException {
    private static final long serialVerisionUID = 2;
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
