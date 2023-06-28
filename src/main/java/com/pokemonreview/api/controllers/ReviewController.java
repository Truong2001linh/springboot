package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviewPokemon")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create/{pokemonId}")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) throws PokemonNotFoundException {
        System.out.println("id : " + pokemonId);
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);
    }

    @PostMapping("/find/{id}")
    public List<ReviewDto> findReviewByPokemonId(@PathVariable(value = "id") int id) throws PokemonNotFoundException {
        return reviewService.getReviewByPokemonId(id);
    }

    @PostMapping("/{pokemonId}/reivew/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId) throws PokemonNotFoundException {
        ReviewDto reviewDto = reviewService.getReviewById(pokemonId, reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PostMapping("/{pokemonId}/reivew/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId, @RequestBody ReviewDto reviewDto) {
        ReviewDto review = reviewService.updateReview(pokemonId, reviewId, reviewDto);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/{pokemonId}/reivew/{reviewId}/delete")
    public ResponseEntity<String> updateReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId) {
        reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }
}
