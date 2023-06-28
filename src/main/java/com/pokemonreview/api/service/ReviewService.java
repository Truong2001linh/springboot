package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.exceptions.ReviewNotFoundException;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto) throws PokemonNotFoundException;

    List<ReviewDto> getReviewByPokemonId(int id) throws PokemonNotFoundException;

    ReviewDto getReviewById (int reviewId, int pokemonId) throws PokemonNotFoundException, ReviewNotFoundException;

    ReviewDto updateReview (int pokemonId , int reviewId , ReviewDto reviewDto);

    void deleteReview (int pokemonId, int reviewId);
}
