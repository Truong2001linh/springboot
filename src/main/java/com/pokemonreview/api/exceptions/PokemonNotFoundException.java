package com.pokemonreview.api.exceptions;

import java.util.concurrent.TimeoutException;

public class PokemonNotFoundException extends RuntimeException {
    private static final long serialVerisionUID = 1;
    public PokemonNotFoundException(String message){
        super(message);
    }

}
