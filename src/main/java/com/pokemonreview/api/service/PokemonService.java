package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize) throws PokemonNotFoundException;
    PokemonDto getPokemonById (int id) throws PokemonNotFoundException;
    PokemonDto updatePokemonById(PokemonDto pokemonDto , int id) throws PokemonNotFoundException;
    void deletePokemon (int id) throws PokemonNotFoundException;
    List<PokemonDto> findPokemonName(String name);

//    List<PokemonDto> jasdhfkasd (String name);
}
