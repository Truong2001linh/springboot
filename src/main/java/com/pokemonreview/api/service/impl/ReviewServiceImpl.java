package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.Repositorys.PokemonRepository;
import com.pokemonreview.api.Repositorys.ReviewRepository;
import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.exceptions.ReviewNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final PokemonRepository pokemonRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(PokemonRepository pokemonRepository, ReviewRepository reviewRepository) {
        this.pokemonRepository = pokemonRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) throws PokemonNotFoundException {
        Review review = mapToDoEntity(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        review.setPokemon(pokemon);
        Review newReview = reviewRepository.save(review);
        return mapToDo(newReview);

    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(int id) throws PokemonNotFoundException {
        List<Review> reviewList = reviewRepository.findByPokemonId(id);
        return reviewList.stream().map(review -> mapToDo(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) throws PokemonNotFoundException, ReviewNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found."));

        Review review1 = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found."));

        if (review1.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to pokemon.");
        }

        return mapToDo(review1);
    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()->new PokemonNotFoundException("Not found pokemon id to update."));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("Not found pokemon id to update."));
        if (review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("Update not success");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStart(reviewDto.getStart());
        Review reviewD = reviewRepository.save(review);
        return mapToDo(reviewD);
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()->new PokemonNotFoundException("Not found pokemon id to delete."));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("Not found pokemon id to delete."));
        if (review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("Delete not success");
        }
        reviewRepository.delete(review);

    }

    private ReviewDto mapToDo(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStart(review.getStart());
        return reviewDto;
    }

    private Review mapToDoEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStart(reviewDto.getStart());
        return review;
    }


}